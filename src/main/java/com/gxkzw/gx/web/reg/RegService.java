package com.gxkzw.gx.web.reg;

import java.util.Date;

import com.gxkzw.gx.common.authcode.AuthCodeService;
import com.gxkzw.gx.common.kit.EmailKit;
import com.gxkzw.gx.common.model.Account;
import com.gxkzw.gx.common.model.AuthCode;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;

public class RegService {

	public static final RegService me = new RegService();
	private static Account dao = new Account().dao();
	
	/**
	 * 用户名是否被注册
	 * @param userName
	 * @return
	 */
	public boolean isUserNameExists(String userName) {
		userName = userName.toLowerCase().trim();
		return Db.queryInt("select id from account where userName=? limit 1",userName) != null;
	}
	
	/**
	 * 身份证号是否被注册
	 * @param idNumber
	 * @return
	 */
	public boolean isIdNumberExists(String idNumber) {
		idNumber = idNumber.trim();
		return Db.queryInt("select id from account where idNumber=? limit 1",idNumber) != null;
	}
	
	
	public Ret reg(String userName,String password,String idNumber,String realName,String ip) {
		if(StrKit.isBlank(userName) || StrKit.isBlank(password)
				|| StrKit.isBlank(idNumber) || StrKit.isBlank(realName)) {
			return Ret.fail("msg", "邮箱,身份证号,姓名或密码不可以为空");
		}
		
		userName = userName.toLowerCase().trim();
		password = password.trim();
		idNumber = idNumber.trim();
		realName = realName.trim();
		
		if(isIdNumberExists(idNumber)) {
			return Ret.fail("msg","该身份证号已被注册,请换一个");
		}
		if(isUserNameExists(userName)) {
			return Ret.fail("msg", "该邮箱已被注册,请换一个");
		}
		
		// 密码加盐
		String salt = HashKit.generateSaltForSha256();
		password = HashKit.sha256(salt+password);
				
		// 创建账户
		Account account = new Account();
		account.setIdNumber(idNumber);
		account.setRealName(realName);
		account.setUserName(userName);
		account.setIp(ip);
		account.setSalt(salt);
		account.setAvatar(Account.DEFAULT_AVATAR);
		account.setPassword(password);
		account.setCreatedAt(new Date());
		account.setStatus(Account.STATUS_REG);
		
		if(account.save()) {
			String authCode = AuthCodeService.me.createRegActivateAuthCode(account.getInt("id"));
			if(sendRegActivateAuthEmail(authCode, account)) {
				return Ret.ok("msg", "注册成功，激活邮件已发送，请查收并激活账号：" + userName);
			} else {
				return Ret.fail("msg", "注册成功，但是激活邮件发送失败，可能是邮件服务器出现故障！");
			}
		}else {
			return Ret.fail("msg","账户保存失败,请联系管理员");
		}
	}
	
	/**
	 * 发送账号激活授权邮件
	 */
	private boolean sendRegActivateAuthEmail(String authCode, Account reg) {
		String baseUrl = PropKit.get("baseUrl");
		String title = "[广西考证网]激活邮件";
		String content = "在浏览器地址栏里输入并访问下面激活链接即可完成账户激活：\n\n"
				+ " "+baseUrl+"/reg/activate?authCode="
				+ authCode;

		String emailServer = PropKit.get("emailServer");
		String fromEmail = PropKit.get("fromEmail");
		String emailPass = PropKit.get("emailPass");
		String toEmail = reg.getStr("userName");
		try {
//			EmailKit.sendEmail(emailServer, fromEmail, emailPass, toEmail, title, content);
			EmailKit.sendSSLEmail(emailServer, fromEmail, emailPass, toEmail, title, content);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 激活账号，返回 false 表示激活码已过期或者不存在
	 * 激活账号不要去自动登录，激活邮件如果发错到了别人的邮箱，会有别人冒用的可能
	 * 并且登录功能还有额外有选择过期时间的功能
	 * @param authCodeId
	 * @return
	 */
	public Ret activate(String authCodeId) {
		AuthCode authCode = AuthCodeService.me.getAuthCode(authCodeId);
		if(authCode != null && authCode.isValidRegActivateAuthCode()) {
			// 更新账户状态为激活,激活的是注册的用户,锁定的用户不激活
			int n = Db.update("update account set status=? where id = ? and status = ?",Account.STATUS_OK,authCode.get("accountId"),Account.STATUS_REG);
			if(n > 0) {
				return Ret.ok("msg", "账号激活成功，欢迎加入 ！");
			} else {
				return Ret.fail("msg", "未找到需要激活的账号，可能是账号已经激活或已经被锁定，请联系管理员");
			}
		}else {
			return Ret.fail("msg", "authCode 不存在或已经失效，可以尝试在登录页再次发送激活邮件");
		}
	}
	
	/**
	 * 重新发送激活邮件
	 * @param userName
	 * @return
	 */
	public Ret reSendActivateEmail(String userName) {
		if (StrKit.isBlank(userName) || userName.indexOf('@') == -1) {
			return Ret.fail("msg", "email 格式不正确，请重新输入");
		}
		userName = userName.toLowerCase().trim();   // email 转成小写
		if ( ! isUserNameExists(userName)) {
			return Ret.fail("msg", "email 没有被注册，无法收取激活邮件，请先去注册");
		}
		
		Account account = dao.findFirst("select * from account where userName=? and status=? limit 1",userName,Account.STATUS_REG);
		if (account == null) {
			return Ret.fail("msg", "该账户已经激活，可以直接登录");
		}
		String authCode = AuthCodeService.me.createRegActivateAuthCode(account.getId());
		if (sendRegActivateAuthEmail(authCode, account)) {
			return Ret.ok("msg", "激活码已发送至邮箱，请收取激活邮件并进行激活");
		} else {
			return Ret.fail("msg", "激活邮件发送失败，可能是邮件服务器出现故障！");
		}
	}
}

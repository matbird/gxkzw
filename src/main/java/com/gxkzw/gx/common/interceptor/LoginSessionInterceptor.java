package com.gxkzw.gx.common.interceptor;

import com.gxkzw.gx.common.kit.IpKit;
import com.gxkzw.gx.common.model.Account;
import com.gxkzw.gx.web.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginSessionInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// 1.从cookie中获取sessionId
		Account loginAccount = null;
		Controller c = inv.getController();
		String sessionId = c.getCookie(LoginService.sessionIdName);
		// 2.如果获取到sessionId,则使用该Id得到登录的Account对象
		if(sessionId != null) {
			loginAccount = LoginService.me.getLoginAccountWithSessionId(sessionId);
			// cache中获取不到,通过sessionId在数据库中获取,并进行session是否过期的判断
			if(loginAccount == null) {
				String loginIp = IpKit.getRealIp(c.getRequest());
				loginAccount = LoginService.me.loginWithSessionId(sessionId, loginIp);
			}
			if(loginAccount != null) {
				// 用户已经登录上,且会话未过期
				c.setAttr(LoginService.loginAccountCacheName, loginAccount);
			}else {
				c.removeCookie(LoginService.sessionIdName); // cookie登录未成功,删除cookie
			}
		}
		
		inv.invoke();
		
		// TODO
	}

}

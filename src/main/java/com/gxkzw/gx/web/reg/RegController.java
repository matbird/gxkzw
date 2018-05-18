package com.gxkzw.gx.web.reg;

import com.gxkzw.gx.common.kit.IpKit;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;

public class RegController extends Controller{

	private static final RegService srv = RegService.me;
	
	public void index() {
		render("index.html");
	}
	
	@Before(RegValidator.class)
	public void save() {
		String ip = IpKit.getRealIp(getRequest());
		Ret ret = srv.reg(getPara("userName"), getPara("password"), getPara("idNumber"), getPara("realName"), ip);
		if(ret.isOk()) {
			ret.set("regEmail", getPara("userName"));
		}
		renderJson(ret);
	}
	
	/**
	 * 重发激活邮件
	 */
	public void reSendActivateEmail() {
		Ret ret = srv.reSendActivateEmail(getPara("email"));
		renderJson(ret);
	}
	
	/**
	 * 激活
	 */
	public void activate() {
		Ret ret = srv.activate(getPara("authCode"));
		setAttr("ret", ret);
		render("activate.html");
	}
	
	/**
	 * 显示还未激活页面
	 */
	public void notActivated() {
		render("not_activated.html");
	}
	
	public void captcha() {
		renderCaptcha();
	}
}

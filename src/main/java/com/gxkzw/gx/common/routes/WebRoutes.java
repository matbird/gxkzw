package com.gxkzw.gx.common.routes;

import com.gxkzw.gx.web.certi.CertiController;
import com.gxkzw.gx.web.index.IndexController;
import com.gxkzw.gx.web.login.LoginController;
import com.gxkzw.gx.web.my.message.MessageController;
import com.gxkzw.gx.web.my.order.OrderController;
import com.gxkzw.gx.web.reg.RegController;
import com.jfinal.config.Routes;

public class WebRoutes extends Routes{

	@Override
	public void config() {
		setBaseViewPath("/_view");
		
		add("/", IndexController.class, "/index");
		add("/login",LoginController.class);
		add("/reg",RegController.class);
		
		// 证书报名
		add("/certi",CertiController.class);
		
		// 我的后台
		add("/my",OrderController.class,"/my/order");
		add("/my/message",MessageController.class);
	}

}

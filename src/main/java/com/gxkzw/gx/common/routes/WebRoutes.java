package com.gxkzw.gx.common.routes;

import com.gxkzw.gx.web.index.IndexController;
import com.jfinal.config.Routes;

public class WebRoutes extends Routes{

	@Override
	public void config() {
		setBaseViewPath("/_view");
		
		add("/", IndexController.class, "/index");
	}

}

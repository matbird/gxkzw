package com.gxkzw.gx.common;

import com.alibaba.druid.wall.WallFilter;
import com.gxkzw.gx.common.routes.WebRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.json.MixedJsonFactory;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;

public class GxConfig extends JFinalConfig{

	private static Prop p = loadConfig();

	/**
	 * 加载配置文件
	 * @return
	 */
	private static Prop loadConfig() {
		try {
			return PropKit.use("gxkzw_config_pro.txt");
		}catch (Exception e) {
			return PropKit.use("gxkzw_config_dev.txt");
		}
	}
	
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(p.getBoolean("devMode", false));
		me.setJsonFactory(MixedJsonFactory.me());
		me.setEncoding("UTF-8");		
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new WebRoutes());
	}

	@Override
	public void configEngine(Engine me) {
		me.addSharedFunction("/_view/common/__layout.html");
	}

	@Override
	public void configPlugin(Plugins me) {
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/");
	}

}

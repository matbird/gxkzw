package com.gxkzw.gx.common;

import java.sql.Connection;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.gxkzw.gx.common.interceptor.LoginSessionInterceptor;
import com.gxkzw.gx.common.model._MappingKit;
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
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;

public class GxConfig extends JFinalConfig{

	private static Prop p = loadConfig();
	private WallFilter wallFilter;

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

	public static DruidPlugin getDruidPlugin() {
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
	}
	
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = getDruidPlugin();
		wallFilter = new WallFilter();
		wallFilter.setDbType("mysql");
		druidPlugin.addFilter(wallFilter);
		druidPlugin.addFilter(new StatFilter()); // 数据统计相关
		me.add(druidPlugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 说明读取未提交的数据是不允许的。这个级别仍然允许不可重复的读和虚读产生。
		arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
		me.add(arp);
		_MappingKit.mapping(arp);
		
		if (p.getBoolean("devMode", false)) {
			arp.setShowSql(true);
		}
		
		me.add(new EhCachePlugin());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginSessionInterceptor());
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	@Override
	public void afterJFinalStart() {
		// 让 druid 允许在 sql 中使用 union
		wallFilter.getConfig().setSelectUnionCheck(false);
	}
	
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/");
	}

}

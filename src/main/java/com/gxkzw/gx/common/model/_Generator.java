package com.gxkzw.gx.common.model;

import javax.sql.DataSource;

import com.gxkzw.gx.common.GxConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

public class _Generator {

	
	private static DataSource getDataSource() {
		DruidPlugin druidPlugin = GxConfig.getDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model使用的包名
		String baseModelPackageName = "com.gxkzw.gx.common.model.base";
		// base model生成文件的保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() +
				"/src/main/java/com/gxkzw/gx/common/model/base";
		// model 使用的包名
		String modelPackageName = "com.gxkzw.gx.common.model";
		// model 生成文件的保存路径
		String modelOutputDir = baseModelOutputDir+"/..";
		
		// 创建生成器
		Generator generator = new Generator(getDataSource(), baseModelPackageName,
				baseModelOutputDir,modelPackageName,modelOutputDir);
		generator.setDialect(new MysqlDialect());
		generator.setGenerateDaoInModel(false);
		generator.setGenerateDataDictionary(false);
		generator.generate();
	}
}

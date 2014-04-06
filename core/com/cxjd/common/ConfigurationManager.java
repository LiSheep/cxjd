package com.cxjd.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;


/**
 * 获取配置文件
 * @author 李腾超
 * @since 2013-05-10
 *
 */
public class ConfigurationManager {

	private static Properties properties;
	
	public static Properties getProperties(String path) throws IOException {
		
		//ServletContext context =  ServletActionContext.getServletContext();
		InputStream in=ConfigurationManager.class.getResourceAsStream("../../../../classes" + path);
		properties = new Properties();
		properties.load(in);
		return properties;
	}
	
}

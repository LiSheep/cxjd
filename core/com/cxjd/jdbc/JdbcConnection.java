package com.cxjd.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cxjd.common.ConfigurationManager;

/**
 * 连接管理
 * @author 李腾超
 * 
 *@since 2013-05-20
 */
public class JdbcConnection {

	private static String path  = "/db.properties";
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Properties properties;
	
	private static Connection connection;
	
	public static String getDriver() throws IOException {
		if(driver == null){
			driver = getPropertities().getProperty("driver");
		}
		return driver;
	}

	public static String getUrl() throws IOException {
		if(url == null){
			url = getPropertities().getProperty("url");
		}
		return url;
	}

	public static String getuser() throws IOException {
		if(user == null){
			user = getPropertities().getProperty("user");
		}
		return user;
	}

	public static String getPassword() throws IOException {
		if(password == null){
			password = getPropertities().getProperty("password");
		}
		return password;
	}

	public static Properties getPropertities() {
		if(properties == null){
			try {
				properties = ConfigurationManager.getProperties(path);
			} catch (IOException e) {
				System.err.println("数据库配置文件加载失败！");
				return null;
			}
		}
		return properties;
	}
	
	public static Connection getConnection() {
		
		try {
			 driver = getDriver();
			 url = getUrl();
			 user= getuser();
			 password = getPassword();
			 
			 if(driver == null || url == null || user == null || password == null){
				 throw new IOException();
			 }
			 
		} catch (IOException e1) {
			System.err.println("获取配置 error");
			return null;
		}
		
		if(connection == null){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.err.println("Connection 包加载失败");
				return null;
			} 
			// 连续数据库
			try {
				connection = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				System.err.println("连接初始化失败！");
				return null;
			}
		}
		return connection;
	}
	
}

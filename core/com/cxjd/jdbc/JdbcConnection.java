package com.cxjd.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cxjd.common.ConfigurationManager;

/**
 * ���ӹ���
 * @author ���ڳ�
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
				System.err.println("���ݿ������ļ�����ʧ�ܣ�");
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
			System.err.println("��ȡ���� error");
			return null;
		}
		
		if(connection == null){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.err.println("Connection ������ʧ��");
				return null;
			} 
			// �������ݿ�
			try {
				connection = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				System.err.println("���ӳ�ʼ��ʧ�ܣ�");
				return null;
			}
		}
		return connection;
	}
	
}

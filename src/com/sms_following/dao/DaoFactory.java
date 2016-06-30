package com.sms_following.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.ConfigurationException;

import com.sms_following.beans.DaoUser;

public class DaoFactory {
	private static final String DAO_PROPERTIES_PATH = "/com/sms_following/dao/dao.properties";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_USER = "user";
	private static final String PROPERTY_PASS = "pass";
	private String url, user, pass;
	public DaoFactory(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public static DaoFactory getInstance(){
		Properties properties = new Properties();
		String driver = null, url = null, user=null, pass=null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = null;
		try {
			in = classLoader.getResourceAsStream(DAO_PROPERTIES_PATH);
			properties.load(in);
			driver = properties.getProperty(PROPERTY_DRIVER);
			url = properties.getProperty(PROPERTY_URL);
			user = properties.getProperty(PROPERTY_USER);
			pass = properties.getProperty(PROPERTY_PASS);
		}catch(Exception e ){
			new DaoConfigurationException("Properties path not found : "+DAO_PROPERTIES_PATH);
		}
		
		try {
			Class.forName(driver);
		}catch(Exception e){
			new ConfigurationException("Impossible to loag driver : "+driver);
		}
		DaoFactory daoFactory = new DaoFactory(url,user,pass);
		return daoFactory;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, pass);
	}
	
	public DaoUser getDaoUser (){
		return new DaoImplementation(this);
	}
}

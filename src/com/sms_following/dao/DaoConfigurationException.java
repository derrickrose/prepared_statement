package com.sms_following.dao;

public class DaoConfigurationException extends Exception {
	
	DaoConfigurationException(String message){
//		super(message);
		super(message);
	}
	DaoConfigurationException(String message, Throwable clause){
//		super(message);
		super(message,clause);
	}
	DaoConfigurationException(Throwable clause){
//		super(message);
		super(clause);
	}

}

package com.sms_following.dao;

public class DaoException extends Exception {
	
	DaoException(String message){
//		super(message);
		super(message);
	}
	DaoException(String message, Throwable clause){
//		super(message);
		super(message,clause);
	}
	DaoException(Throwable clause){
//		super(message);
		super(clause);
	}

}

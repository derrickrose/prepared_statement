package com.sms_following.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sms_following.beans.DaoUser;
import com.sms_following.beans.User;

public class DaoImplementation implements DaoUser {
	
	private DaoFactory daofactory;
	public DaoImplementation(DaoFactory daoFactory){
		this.daofactory = daoFactory;
	}
	
	
	@Override
	public User searchForUser() {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = daofactory.getConnection();
			PreparedStatement preparedStatement = createStatement(connection, "select * from animal where id=1;", null);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()){
				return userMapper(res);
			}
		} catch (SQLException e) {
			new DaoException ("Impossible to get connection!");
		}
		
		return null;
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub

	}
	
	private PreparedStatement createStatement(Connection connection, String query, Object...objects ) throws SQLException{
		PreparedStatement statement = connection.prepareStatement(query);
		int i = 1;
		if(objects !=null){
			for(Object object : objects){
				statement.setObject(i,object);
				i++;
			}
		}
		return statement;
	}
	
	private User userMapper(ResultSet res) throws SQLException{
		User user = null;
		user.setId(res.getInt("id"));
		user.setName(res.getString("name"));
		return user;
	}

}

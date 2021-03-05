package com.service;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.bean.User;
import com.dao.UserDao;

public class UserService 
{
	private UserDao dao = new UserDao();
	public User getUser(String userid, String password) throws SQLException
	{
		
		return dao.getUser(userid, password);
		
		
	}
	
	public void setLastLogin(User user) throws SQLException {
		if(user!=null)
		{
		Timestamp lastLogin=new Timestamp(System.currentTimeMillis());
		user.setLastLogin(lastLogin);
		dao.setLastLogin(user);
			
		}
		
	}

}

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.User;
import com.utils.DbConnection;

public class UserDao 
{
	public User getUser(String userid,String password) throws SQLException 
	{
		Connection con = DbConnection.getConnection();
		PreparedStatement query=con.prepareStatement("select ws_username,ws_last_login from userstore where ws_username=? and ws_password=?");
				
		query.setString(1, userid);
		query.setString(2, password);
	    ResultSet result=query.executeQuery();
	    User user=null;
	    
	    if(result.next()) 
	    {   
		   user=new User();
		   user.setUserId(result.getString("ws_username"));
		   user.setLastLogin(result.getTimestamp("ws_last_login"));
	    }
	    DbConnection.closeConnection(con);
		 DbConnection.closeStatement(query);
	    return user;
	    
		   
	   
	}
	public void setLastLogin(User user) throws SQLException
	{
		Connection con = DbConnection.getConnection();
		PreparedStatement query=con.prepareStatement("update userstore set ws_last_login = ? where ws_username = ?");
	
		query.setTimestamp(1, user.getLastLogin());
		query.setString(2, user.getUserId());
		query.executeUpdate();
		con.commit();
		DbConnection.closeConnection(con);
		DbConnection.closeStatement(query);
	}
		
	
}

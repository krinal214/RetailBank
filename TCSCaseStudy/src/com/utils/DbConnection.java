package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static final String username="root";
	private static final String password="root";
	private static final String url="jdbc:mysql://localhost:3306/retail_bank_management";
	
	public static Connection getConnection() throws SQLException {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			con=DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
		
		return con;
		
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con!=null) 
		{	
			con.close();
		}
	}
	
	public static void closeStatement(Statement s) throws SQLException {
		if(s!=null)
			s.close();
		
	}
	public static void commitConnection(Connection con) throws SQLException{
		if(con!=null)
			con.commit();
	}

}

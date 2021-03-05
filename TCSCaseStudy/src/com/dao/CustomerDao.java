package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Customer;
import com.utils.DbConnection;

public class CustomerDao 


{
	public List<Customer> getCustomersByPageNumber(int limit1,int limit2) throws SQLException
	{
		List<Customer> customers = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from customer limit ?,?");
		
		query.setInt(1, limit1);
		query.setInt(2, limit2);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Customer customer=new Customer();
			customer.setCustId(result.getInt("ws_cust_id"));
			customer.setAddress(result.getString("ws_adrs"));
			customer.setAge(result.getInt("ws_age"));
			customer.setCity(result.getString("ws_city"));
			customer.setState(result.getString("ws_state"));
			customer.setLast_update(result.getTimestamp("ws_last_update"));
			customer.setMsg(result.getString("ws_msg"));
			customer.setName(result.getString("ws_name"));
			customer.setStatus(result.getString("ws_status").charAt(0));
			customer.setSsn(result.getInt("ws_ssn"));
			
			customers.add(customer);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return customers;		
	}
	
	public Customer getCustomerBySSN(int ssn) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		PreparedStatement query=con.prepareStatement("select * from customer where ws_ssn=?");
		query.setInt(1, ssn);
		Customer customer=null;
		ResultSet result=query.executeQuery();
		if(result.next()) {
			customer=new Customer();
			customer.setCustId(result.getInt("ws_cust_id"));
			customer.setAddress(result.getString("ws_adrs"));
			customer.setAge(result.getInt("ws_age"));
			customer.setCity(result.getString("ws_city"));
			customer.setState(result.getString("ws_state"));
			customer.setLast_update(result.getTimestamp("ws_last_update"));
			customer.setMsg(result.getString("ws_msg"));
			customer.setName(result.getString("ws_name"));
			customer.setStatus(result.getString("ws_status").charAt(0));
			customer.setSsn(ssn);
		}
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		return customer;
	}
	public Customer getCustomerById(int id) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		PreparedStatement query=con.prepareStatement("select * from customer where ws_cust_id=?");
		query.setInt(1, id);
		Customer customer=null;
		ResultSet result=query.executeQuery();
		if(result.next()) {
			
			customer=new Customer();
			customer.setCustId(result.getInt("ws_cust_id"));
			customer.setAddress(result.getString("ws_adrs"));
			customer.setAge(result.getInt("ws_age"));
			customer.setCity(result.getString("ws_city"));
			customer.setState(result.getString("ws_state"));
			customer.setLast_update(result.getTimestamp("ws_last_update"));
			customer.setMsg(result.getString("ws_msg"));
			customer.setName(result.getString("ws_name"));
			customer.setStatus(result.getString("ws_status").charAt(0));
			customer.setSsn(result.getInt("ws_ssn"));
		}
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		return customer;
	}
	
	public boolean deleteCustomer(Customer customer) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		PreparedStatement query=con.prepareStatement("update customer set ws_status='I',ws_msg=?,ws_last_update=? where ws_cust_id=? and ws_status='A' ");
		query.setString(1,customer.getMsg());
		query.setTimestamp(2, customer.getLast_update());
		query.setInt(3, customer.getCustId());
		int rows=query.executeUpdate();
		boolean ans=false;
		if(rows==0)
			ans= false;
		else
			ans=true;
		con.commit();
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		return ans;
	}
	
	public boolean updateCustomer(Customer customer) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		boolean success=false;
		PreparedStatement query=con.prepareStatement("update customer set ws_name=?,ws_age=?,ws_adrs=?,ws_city=?,ws_state=?,ws_msg=?,ws_last_update=? where ws_cust_id=?");
		query.setString(1,customer.getName());
		query.setInt(2,customer.getAge());
		query.setString(3,customer.getAddress());
		query.setString(4,customer.getCity());
		query.setString(5,customer.getState());
		query.setString(6,customer.getMsg());
		query.setTimestamp(7, customer.getLast_update());
		query.setInt(8, customer.getCustId());
		int rows=query.executeUpdate();
		if(rows>0)success=true;
		con.commit();
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		return success;
	}
	
	public boolean createCustomer(Customer customer) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		boolean success=false;
	    long l=System.currentTimeMillis();
	    System.out.println("before query");
		PreparedStatement query=con.prepareStatement("insert into customer(ws_ssn,ws_status,ws_msg,ws_last_update,ws_name,ws_adrs,ws_age,ws_city,ws_state) values (?,?,?,?,?,?,?,?,?)");
		query.setInt(1, customer.getSsn());
		query.setString(2, customer.getStatus()+"");
		query.setString(3, customer.getMsg());
		query.setTimestamp(4, customer.getLast_update());
		query.setString(5, customer.getName());
		query.setString(6, customer.getAddress());
		query.setInt(7, customer.getAge());
		query.setString(8, customer.getCity());
		query.setString(9, customer.getState());
		System.out.println("check");
	 int rows = query.executeUpdate();
	 System.out.println("outside ");
	 long l1=System.currentTimeMillis();
	 System.out.println(" time "+(l1-l));
	if(rows>0)success=true;	
	 con.commit();
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		return success;
	}
	

}

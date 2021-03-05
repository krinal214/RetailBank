package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bean.Account;
import com.utils.DbConnection;


public class AccountDao 
{
	public List<Account> getAccountsByPageNumber(int limit1,int limit2) throws SQLException
	{
		List<Account> accounts = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from account limit ?,?");
		
		query.setInt(1, limit1);
		query.setInt(2, limit2);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{
			Account account=new Account();
		account.setCustId(result.getInt("ws_cust_id"));
		account.setAccountId(result.getInt("ws_acct_id"));
		account.setAccountType(result.getString("ws_acct_type").charAt(0));
		account.setBalance(result.getLong("ws_acct_balance"));
		account.setCreationDate(result.getDate("ws_acct_crdate"));
		account.setLastUpdate(result.getTimestamp("ws_last_update"));
		account.setMsg(result.getString("ws_msg"));
		account.setStatus(result.getString("ws_status").charAt(0));			
			
			accounts.add(account);
		}
		return accounts;
	}

	public Account getAccountById(int id) throws SQLException
	{
		Account account=null;
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from account where ws_acct_id=?");
		query.setInt(1, id);
		
		ResultSet result=query.executeQuery();
		if(result.next()) 
		{
			account=new Account();
			
			account.setCustId(result.getInt("ws_cust_id"));
			account.setAccountId(result.getInt("ws_acct_id"));
			account.setAccountType(result.getString("ws_acct_type").charAt(0));
			account.setBalance(result.getLong("ws_acct_balance"));
			account.setCreationDate(result.getDate("ws_acct_crdate"));
			account.setLastUpdate(result.getTimestamp("ws_last_update"));
			account.setMsg(result.getString("ws_msg"));
			account.setStatus(result.getString("ws_status").charAt(0));			
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return account;
	}
	
	public List<Account> getAccountByCustId(int id) throws SQLException
	{
		List<Account> accounts=new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from account where ws_cust_id=?");
		query.setInt(1, id);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Account account=new Account();
			
			account.setCustId(result.getInt("ws_cust_id"));
			account.setAccountId(result.getInt("ws_acct_id"));
			account.setAccountType(result.getString("ws_acct_type").charAt(0));
			account.setBalance(result.getLong("ws_acct_balance"));
			account.setCreationDate(result.getDate("ws_acct_crdate"));
			account.setLastUpdate(result.getTimestamp("ws_last_update"));
			account.setMsg(result.getString("ws_msg"));
			account.setStatus(result.getString("ws_status").charAt(0));
			
			accounts.add(account);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return accounts;
	}
	
	public boolean createAccount(Account account) throws SQLException
	{
		boolean status=false; 
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("insert into account(ws_cust_id,ws_acct_type,ws_acct_balance,ws_acct_crdate,ws_status,ws_msg,ws_last_update) values(?,?,?,?,?,?,?)");
		query.setInt(1,account.getCustId());
		query.setString(2, account.getAccountType()+"");
		query.setLong(3, account.getBalance());
		query.setDate(4,account.getCreationDate());
		query.setString(5, account.getStatus()+"");
		query.setString(6, account.getMsg());
		query.setTimestamp(7, account.getLastUpdate());
		
		int rows=query.executeUpdate();
        if(rows>0)status=true;
        con.commit();
          
        DbConnection.closeStatement(query);
  		DbConnection.closeConnection(con);
  		
  		return status;
	}
	
	public boolean deleteAccount(Account account) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("update account set ws_status='I',ws_msg=?,ws_last_update=? where ws_acct_id=? and ws_status='A' ");
		query.setString(1,account.getMsg());
		query.setTimestamp(2, account.getLastUpdate());
		query.setInt(3, account.getAccountId());
		
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
	
	public boolean deleteAccountByCustId(int custId,String msg,Timestamp timestamp) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("update account set ws_status='I',ws_msg=?,ws_last_update=? where ws_cust_id=? and ws_status='A' ");
		query.setString(1,msg);
		query.setTimestamp(2,timestamp);
		query.setInt(3, custId);
		
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
	
	public boolean setAmount(int accountId,long amount) throws SQLException
	{
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("update account set ws_acct_balance=? where ws_acct_id=?");
		query.setLong(1,amount);
		query.setInt(2, accountId);
		
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


}

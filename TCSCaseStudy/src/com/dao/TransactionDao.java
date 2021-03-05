package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Transaction;
import com.utils.DbConnection;

public class TransactionDao 
{
	public boolean createTransaction(Transaction transaction) throws SQLException
	{
		boolean status=false; 
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("insert into transactions(ws_src_acct_id,ws_dest_acct_id,ws_type,ws_amt,ws_trxn_ts) values(?,?,?,?,?)");
		query.setInt(1,transaction.getSrcAcctId());
		query.setInt(2, transaction.getDestAcctId());
		query.setString(3, transaction.getType()+"");
		query.setLong(4, transaction.getAmount());
		query.setTimestamp(5,transaction.getTimestamp());
		int rows=query.executeUpdate();
        if(rows>0)status=true;
        con.commit();
          
        DbConnection.closeStatement(query);
  		DbConnection.closeConnection(con);
  		
  		return status;
	}

	public List<Transaction> getTransactionPageByDate(int accId,Date start,Date end,int limit,int pageSize) throws SQLException
	{
		List<Transaction> transactions = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from transactions where ws_src_acct_id=? or ws_dest_acct_id=? and DATE(ws_trxn_ts) between ? and ? limit ?,?");
		query.setInt(1,accId);
		query.setInt(2,accId);
		query.setDate(3, start);
		query.setDate(4, end);
		query.setInt(5, limit);
		query.setInt(6, pageSize+1);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Transaction transaction = new Transaction();
			transaction.setTrxnId(result.getInt("ws_trxn_id"));
			transaction.setSrcAcctId(result.getInt("ws_src_acct_id"));
			transaction.setDestAcctId(result.getInt("ws_dest_acct_id"));
			transaction.setAmount(result.getLong("ws_amt"));
			transaction.setType(result.getString("ws_type").charAt(0));
			transaction.setTimestamp(result.getTimestamp("ws_trxn_ts"));
			
			transactions.add(transaction);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return transactions;
	}

	public List<Transaction> getTransactionByPageNumber(int accId, int limit1,int limit2) throws SQLException
	{
		List<Transaction> transactions = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from transactions where ws_src_acct_id=? or ws_dest_acct_id=? order by ws_trxn_ts desc limit ?,?");
		query.setInt(1,accId);
		query.setInt(2,accId);
		query.setInt(3, limit1);
		query.setInt(4, limit2);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Transaction transaction = new Transaction();
			transaction.setTrxnId(result.getInt("ws_trxn_id"));
			transaction.setSrcAcctId(result.getInt("ws_src_acct_id"));
			transaction.setDestAcctId(result.getInt("ws_dest_acct_id"));
			transaction.setAmount(result.getLong("ws_amt"));
			transaction.setType(result.getString("ws_type").charAt(0));
			transaction.setTimestamp(result.getTimestamp("ws_trxn_ts"));
			
			transactions.add(transaction);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return transactions;		
	
	
	}

	public List<Transaction> getTransactionByDate(int accId, Date start, Date end) throws SQLException 
	{
		List<Transaction> transactions = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from transactions where ws_src_acct_id=? or ws_dest_acct_id=? and DATE(ws_trxn_ts) between ? and ?");
		query.setInt(1,accId);
		query.setInt(2,accId);
		query.setDate(3, start);
		query.setDate(4, end);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Transaction transaction = new Transaction();
			transaction.setTrxnId(result.getInt("ws_trxn_id"));
			transaction.setSrcAcctId(result.getInt("ws_src_acct_id"));
			transaction.setDestAcctId(result.getInt("ws_dest_acct_id"));
			transaction.setAmount(result.getLong("ws_amt"));
			transaction.setType(result.getString("ws_type").charAt(0));
			transaction.setTimestamp(result.getTimestamp("ws_trxn_ts"));
			
			transactions.add(transaction);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return transactions;
	}
	
	public List<Transaction> getTransactionByNumber(int accId, int number) throws SQLException 
	{
		List<Transaction> transactions = new ArrayList<>();
		Connection con=DbConnection.getConnection();
		
		PreparedStatement query=con.prepareStatement("select * from transactions where ws_src_acct_id=? or ws_dest_acct_id=? order by ws_trxn_ts desc limit ?,?");
		query.setInt(1,accId);
		query.setInt(2,accId);
		query.setInt(3, 0);
		query.setInt(4, number);
		
		ResultSet result=query.executeQuery();
		while(result.next()) 
		{	
			
			Transaction transaction = new Transaction();
			transaction.setTrxnId(result.getInt("ws_trxn_id"));
			transaction.setSrcAcctId(result.getInt("ws_src_acct_id"));
			transaction.setDestAcctId(result.getInt("ws_dest_acct_id"));
			transaction.setAmount(result.getLong("ws_amt"));
			transaction.setType(result.getString("ws_type").charAt(0));
			transaction.setTimestamp(result.getTimestamp("ws_trxn_ts"));
			
			transactions.add(transaction);
		}
		
		DbConnection.closeStatement(query);
		DbConnection.closeConnection(con);
		
		return transactions;		
	

		
	}
}

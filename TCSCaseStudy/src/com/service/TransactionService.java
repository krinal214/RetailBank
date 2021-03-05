package com.service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.bean.Account;
import com.bean.Transaction;
import com.dao.TransactionDao;
import com.utils.CustomException;

public class TransactionService 
{
	private TransactionDao dao=new TransactionDao();
	AccountService accountService=new AccountService();
	public boolean withdraw(int accId,long amt) throws SQLException, CustomException
	{
		
		Account account=accountService.getAccountById(accId);
		if(account==null)
			throw new CustomException("No Account exists");
		else if(account.getStatus()=='I') 
		{
			throw new CustomException("Account is inactive for transaction");
		}	
		else if(account.getBalance()<amt) 
		{
			throw new CustomException("Insufficient Balance!");
		}
		long newAmt=account.getBalance()-amt;
		boolean status=accountService.setAmount(accId,newAmt);
		if(!status) 
		{
			throw new CustomException("Error in duducting amount from account");
		}
		Transaction transaction = new Transaction();
		transaction.setDestAcctId(accId);
		transaction.setSrcAcctId(accId);
		transaction.setAmount(amt);
		transaction.setType('W');
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return dao.createTransaction(transaction);
	}
	
	public boolean deposit(int accId,long amt) throws SQLException, CustomException
	{
		Account account=accountService.getAccountById(accId);
		if(account==null)
			throw new CustomException("No Account exists");
		else if(account.getStatus()=='I') 
		{
			throw new CustomException("Account is inactive for transaction");
		}	
		
		long newAmt=account.getBalance()+amt;
		boolean status=accountService.setAmount(accId,newAmt);
		if(!status) 
		{
			throw new CustomException("Error in depositing amount from account");
		}
		Transaction transaction = new Transaction();
		transaction.setDestAcctId(accId);
		transaction.setSrcAcctId(accId);
		transaction.setAmount(amt);
		transaction.setType('D');
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return dao.createTransaction(transaction);
	}
	
	public boolean transfer(int srcAccId,int destAccId,long amt) throws SQLException, CustomException
	{
		
		Account srcAccount=accountService.getAccountById(srcAccId);
		Account destAccount=accountService.getAccountById(destAccId);
		if(srcAccount==null || destAccount==null)
			throw new CustomException("Source or Destination account not exists");
		else if(srcAccount.getStatus()=='I' || destAccount.getStatus()=='I') 
		{
			throw new CustomException("Source or Destination account is invalid");
		}	
		else if(srcAccount.getBalance()<amt) 
		{
			throw new CustomException("Insufficient Balance in Source account!");
		}
		long newSrcAmt=srcAccount.getBalance()-amt;
		long newDestAmt=destAccount.getBalance()+amt;
		boolean srcStatus=accountService.setAmount(srcAccId,newSrcAmt);
		boolean destStatus=accountService.setAmount(destAccId,newDestAmt);
		if(!(srcStatus && destStatus)) 
		{
			throw new CustomException("Error in transfering the amount");
		}
		Transaction transaction = new Transaction();
		transaction.setDestAcctId(destAccId);
		transaction.setSrcAcctId(srcAccId);
		transaction.setAmount(amt);
		transaction.setType('T');
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return dao.createTransaction(transaction);
	}
	
	public List<Transaction> getTransactionPageByDate(int accId,Date start,Date end,int page,int pageSize) throws SQLException, CustomException
	{
		Account account=accountService.getAccountById(accId);
		if(account==null)
			throw new CustomException("No Account Exists");
		if(start.compareTo(end)>0)
			throw new CustomException("Start date should be less than end Date");;
		int limit=(page-1)*pageSize;
		return dao.getTransactionPageByDate(accId,start,end,limit,pageSize);
	}
	
	public List<Transaction> getTransactionPageByNumber(int accId,int page,int number,int pageSize) throws SQLException, CustomException
	{
		Account account=accountService.getAccountById(accId);
		int rest=number-(page-1)*pageSize;
		int limit1=0;
		if(rest>pageSize) {limit1=pageSize+1;}
		else { limit1=rest;}
		if(account==null)
			throw new CustomException("No Account Exists");
		
		int limit=(page-1)*pageSize;
		return dao.getTransactionByPageNumber(accId,limit,limit1);
	}

	public List<Transaction> getTransactionByDate(int accId,Date start,Date end) throws SQLException, CustomException
	{
		Account account=accountService.getAccountById(accId);
		if(account==null)
			throw new CustomException("No Account Exists");
		if(start.compareTo(end)>0)
			throw new CustomException("Start date should be less than end Date");;
		return dao.getTransactionByDate(accId,start,end);
	}
	
	public List<Transaction> getTransactionByNumber(int accId,int number) throws SQLException, CustomException
	{
		Account account=accountService.getAccountById(accId);
		if(account==null)
			throw new CustomException("No Account Exists");
		
		return dao.getTransactionByNumber(accId,number);
	}

}


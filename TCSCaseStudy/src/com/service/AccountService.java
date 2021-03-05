package com.service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.bean.Account;
import com.bean.Customer;
import com.dao.AccountDao;
import com.utils.CustomException;

public class AccountService 
{
	private AccountDao dao = new AccountDao();
	
	public List<Account> getAccountsByPageNumber(int page,int pageSize) throws SQLException
	{
		int limit=(page-1)*pageSize;
		return dao.getAccountsByPageNumber(limit,pageSize+1);
	}
	
	public Account getAccountById(int id) throws SQLException
	{
		return dao.getAccountById(id);
	}
	
	public List<Account> getAccountByCustId(int id) throws SQLException
	{
		return dao.getAccountByCustId(id);
	}
	
	public boolean createAccount(Account account) throws SQLException,CustomException
	{
		CustomerService customerService= new CustomerService();
		
		Customer customer=customerService.getCustomerById(account.getCustId());
		
		if(customer==null) throw new CustomException("No such customer exist");
		else if(customer.getStatus()=='I') throw new CustomException("Customer is inactive");
        
		account.setBalance(0);
        account.setStatus('A');
        
        Timestamp lastUpdate=new Timestamp(System.currentTimeMillis());
        Date creationDate=new Date(lastUpdate.getTime());
        
        account.setCreationDate(creationDate);
        account.setLastUpdate(lastUpdate);
        account.setMsg("Account created Successfully");
		
        boolean status=dao.createAccount(account);
		return status;
		
	}
	
	public boolean deleteAccountByCustId(int custId) throws CustomException, SQLException
	{
		CustomerService customerService=new CustomerService();
		
		Customer customer= customerService.getCustomerById(custId);
		
		if(customer==null) throw new CustomException("No such customer exist");
		else if(customer.getStatus()=='I') throw new CustomException("Customer is inactive");
		
		String msg ="Account deleted Successfully";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return dao.deleteAccountByCustId(custId,msg,timestamp);
	}
	
	public boolean deleteAccountByAccountId(int accountId) throws SQLException, CustomException
	{
		Account account= dao.getAccountById(accountId);
		
		if(account.getStatus()=='I')
			throw new CustomException("Account is already inactive");
		
		account.setMsg("Account deleted Successfully");
		account.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		
		boolean status = dao.deleteAccount(account);
		if(!status) {
			throw new CustomException("Delete operation not possible..either account not exist or already inactive");
		}
		return status; 
		
	}
	
	public boolean setAmount(int accountId,long amount) throws SQLException
	{
		return dao.setAmount(accountId,amount);
	}
	

}

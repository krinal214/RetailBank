package com.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.bean.Customer;
import com.dao.CustomerDao;
import com.utils.CustomException;

public class CustomerService 
{
	private CustomerDao dao = new CustomerDao();
	
	public List<Customer> getCustomersByPageNumber(int page,int pageSize) throws SQLException
	{
		int limit=(page-1)*pageSize;
		return dao.getCustomersByPageNumber(limit,pageSize+1);
	}
	
	public Customer getCustomerBySSN(int ssn) throws SQLException
	{
		return dao.getCustomerBySSN(ssn);
	}
	
	public Customer getCustomerById(int custId) throws SQLException
	{
		return dao.getCustomerById(custId);
	}

	public boolean deleteCustomerById(int custId ) throws SQLException, CustomException {
        
		Customer customer= dao.getCustomerById(custId);
		System.out.println("delete 1");
		if(customer.getStatus()=='I')
			throw new CustomException("Customer is already inactive !!!!!!!!!!!");
		System.out.println("delete 2");
		
		AccountService accountService = new AccountService();
		boolean status=accountService.deleteAccountByCustId(custId);
        customer.setMsg("Customer deleted Successfully");
		customer.setLast_update(new Timestamp(System.currentTimeMillis()));
        dao.deleteCustomer(customer);
		return status;
	}
	
	public boolean updateCustomer(Customer customer) throws SQLException, CustomException
	{
		Customer oldCustomer =dao.getCustomerById(customer.getCustId());
		if(compareCustomer(customer, oldCustomer)) {
			throw new CustomException("no new information to update");
		}
		customer.setMsg("Customer Update Successfully");
		customer.setLast_update(new Timestamp(System.currentTimeMillis()));
		boolean status=dao.updateCustomer(customer);
		if(!status) {
			throw new CustomException("Update failed");
		}
	    return status;
	}
	public boolean createCustomer(Customer customer) throws SQLException
	{
		return dao.createCustomer(customer); 
	}
	public boolean compareCustomer(Customer a,Customer b)
	{
		return (a.getCustId()==b.getCustId() &&
				a.getSsn()==b.getSsn() &&
				a.getName().equals(b.getName()) &&
				a.getAddress().equals(b.getAddress()) &&
				a.getAge()==b.getAge() &&
				a.getCity().equals(b.getCity()) &&
				a.getState().equals(b.getState())
				);
	}

}

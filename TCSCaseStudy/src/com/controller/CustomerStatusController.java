package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Customer;
import com.service.CustomerService;



@WebServlet("/customerStatusController")
public class CustomerStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerStatusController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String param=request.getServletContext().getInitParameter("pageSize");
		int pageSize=Integer.parseInt(param);
		int page=Integer.parseInt(request.getParameter("page"));
		RequestDispatcher dispatcher = null;
		String statusMsg =null;
		List<Customer> customers = new ArrayList<>();
		CustomerService service = new CustomerService();
		try 
		{
			
			customers = service.getCustomersByPageNumber(page,pageSize);
			
			int prev = page<=1?-1:page-1;
			int next=customers.size()==pageSize+1?page+1:-1;
			if(customers.size()==pageSize+1)
				customers.remove(customers.size()-1);
			
			request.setAttribute("prev", prev);
			request.setAttribute("next", next);
			request.setAttribute("customers", customers);
		    dispatcher=request.getRequestDispatcher("customerstatus.jsp");
		    
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			statusMsg="Something went wrong";
			dispatcher=request.getRequestDispatcher("status.jsp");
			request.setAttribute("statusMsg", statusMsg);
			
		}
		
		dispatcher.forward(request, response);
	}

}

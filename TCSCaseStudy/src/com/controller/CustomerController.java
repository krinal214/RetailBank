package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Customer;
import com.google.gson.Gson;
import com.service.CustomerService;
import com.utils.CustomException;


@WebServlet("/customerController")
public class CustomerController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private CustomerService customerService = new CustomerService();   
    
    public CustomerController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		HttpSession session=request.getSession(false);
		if(session==null || session.getAttribute("user")==null) 
		{
			response.sendRedirect("loginController");
		}
		else 
		{
			String mode=request.getParameter("mode");
			String confirm=request.getParameter("confirm");
			String cust=request.getParameter("custId");
			RequestDispatcher dispatcher=null;
			if(mode!=null && cust!=null && (mode.equals("fetch")||mode.equals("profile")) ) 
			{ 
				int custId=Integer.parseInt(cust);
				Customer customer=null;
				try {
					customer = customerService.getCustomerById(custId);
					Gson gson=new Gson();
					
					if(customer!=null) {
						if(mode.equals("fetch")) {
							PrintWriter writer=response.getWriter();
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");

							String msg=gson.toJson(customer);
					writer.print(msg);
					writer.flush();
					writer.close();
					}
						else {
							request.setAttribute("customer", customer);
							dispatcher=request.getRequestDispatcher("customer.jsp");
							dispatcher.forward(request,response);
						}
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			if(mode!=null && mode.equals("create"))
			{
				dispatcher=request.getRequestDispatcher("createcustomer.jsp");
				dispatcher.forward(request, response);
			}
			else if(mode!=null && (mode.equals("update")||mode.equals("delete")||mode.equals("search"))) 
			{
				request.setAttribute("operation",mode);
				request.setAttribute("confirm", confirm);
				dispatcher=request.getRequestDispatcher("searchcustomer.jsp");
				dispatcher.forward(request, response);
			    
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 	HttpSession session=request.getSession(false);
		if(session==null || session.getAttribute("user")==null) 
		{
			response.sendRedirect("loginController");
		}
		else 
		{
			String mode=request.getParameter("mode");
			String confirm=request.getParameter("confirm");
			String statusMsg=null;
			RequestDispatcher dispatcher=null;
			response.setContentType("text/html");
			Customer customer=null;
			
			if(confirm!=null && mode!=null && confirm.equals("false") && ( (mode.equals("update") || mode.equals("delete") || mode.equals("search")) ) )
			{
				String searchBy = request.getParameter("searchBy");
				int searchId = Integer.parseInt(request.getParameter(searchBy));		
				try 
				{
					if(searchBy.equals("ssn"))
						customer = customerService.getCustomerBySSN(searchId);
					else if(searchBy.equals("id"))
						customer=customerService.getCustomerById(searchId);
					if(customer==null) 
					{
						request.setAttribute("statusMsg", "No Customer exists");
						
						dispatcher=request.getRequestDispatcher("status.jsp");
						dispatcher.forward(request, response);	
					}
					else 
					{	  
						request.setAttribute("customer", customer);	
						if(mode.equals("search"))
						    dispatcher=request.getRequestDispatcher("customer.jsp");
						else if(mode.equals("update"))
							dispatcher=request.getRequestDispatcher("updatecustomer.jsp");
						else if(mode.equals("delete"))
						    dispatcher=request.getRequestDispatcher("deletecustomer.jsp");
						dispatcher.forward(request, response);	
					}
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
					e.getMessage();
				}
			}
			else if(confirm!=null && mode!=null && confirm.equals("true") && mode.equals("update") )
			{
				int custId = Integer.parseInt(request.getParameter("custId"));
				int age = Integer.parseInt(request.getParameter("age"));
				dispatcher = request.getRequestDispatcher("status.jsp");
				
				try 
				{
					customer = customerService.getCustomerById(custId);
					customer.setName(request.getParameter("name"));
					customer.setCity(request.getParameter("city"));
					customer.setAddress(request.getParameter("address"));
					customer.setState(request.getParameter("state"));
					customer.setAge(age);
					customerService.updateCustomer(customer);
					
					statusMsg = "Customer Updated successfully";
					request.setAttribute("statusMsg", statusMsg);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
					request.setAttribute("statusMsg", "Something went wrong");
				}
				catch (CustomException e) 
				{
					e.printStackTrace();
				
					request.setAttribute("statusMsg", e.getMessage());
				}
				
					dispatcher.forward(request, response);
				
			}
			else if(confirm!=null && mode!=null && confirm.equals("true") && mode.equals("delete") )
			{
				int custId=Integer.parseInt(request.getParameter("custId"));
				dispatcher = request.getRequestDispatcher("status.jsp");
				try 
				{
					customerService.deleteCustomerById(custId);
					statusMsg = "Customer deleted successfully";
					request.setAttribute("statusMsg", statusMsg);
					System.out.println("controller 1");
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				   request.setAttribute("statusMsg", "Something went wrong");
				}
				catch (CustomException e) {
					e.printStackTrace();
					request.setAttribute("statusMsg", e.getMessage());
				}
				dispatcher.forward(request, response);
				
			}
			else if(mode!=null && mode.equals("create") )
			{
				customer = new Customer();
				dispatcher = request.getRequestDispatcher("status.jsp");
				customer.setSsn(Integer.parseInt(request.getParameter("ssn")));
				customer.setName(request.getParameter("name"));
				customer.setAge(Integer.parseInt(request.getParameter("age")));
				customer.setAddress(request.getParameter("address"));
				customer.setCity(request.getParameter("city"));
				customer.setState(request.getParameter("state"));
				customer.setMsg("Customer created successfully");
				customer.setStatus('A');
				customer.setLast_update(new Timestamp(System.currentTimeMillis()));
				
				try 
				{
					customerService.createCustomer(customer);
					statusMsg = "Customer Created successfully";
					request.setAttribute("statusMsg", statusMsg);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
					request.setAttribute("statusMsg", "Something went wrong");	
				}
				dispatcher.forward(request, response);
			}
		}
	}
}

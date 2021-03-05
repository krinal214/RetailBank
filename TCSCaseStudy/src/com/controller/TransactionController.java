package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Account;
import com.service.AccountService;
import com.service.TransactionService;
import com.utils.CustomException;


@WebServlet("/transactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransactionController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			String mode=request.getParameter("mode");
			RequestDispatcher dispatcher=null;
			AccountService accountService = new AccountService();
			Account account=null;
			String statusMsg = null;
			if(mode!=null && (mode.equals("withdraw") ||mode.equals("transfer") ||mode.equals("deposit")))
			{
				int accId=Integer.parseInt(request.getParameter("accountId"));
				try
				{
					account = accountService.getAccountById(accId);
					if(account==null)
						throw new CustomException("No Customer exists");
					request.setAttribute("account", account);
					dispatcher=request.getRequestDispatcher(mode+".jsp");
					dispatcher.forward(request, response);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
					statusMsg="Something went wrong";
					request.setAttribute("statusMsg", statusMsg);
					
				}
				catch (CustomException e) 
				{	
					e.printStackTrace();
					statusMsg=e.getMessage();
					request.setAttribute("statusMsg", statusMsg);
				}
				if(statusMsg!=null)
				{
					dispatcher=request.getRequestDispatcher("status.jsp");
					dispatcher.forward(request, response);
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mode=request.getParameter("mode");
		RequestDispatcher dispatcher=null;
		boolean status=false;
		String statusMsg=null;
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		TransactionService transactionService = new TransactionService();
		AccountService accountService = new AccountService();
		if(mode!=null && ( mode.equals("withdraw") ||mode.equals("deposit") ||mode.equals("transfer") ))
		{
			int srcAccId = Integer.parseInt(request.getParameter("account"));
			int amount =Integer.parseInt(request.getParameter("amount"));
			System.out.println("Parsing success");
			try
			{
				if(mode.equals("withdraw"))
				{
					status=transactionService.withdraw(srcAccId, amount);
					System.out.println("withdrawed");
				}
				else if(mode.equals("deposit"))
				{
					status=transactionService.deposit(srcAccId, amount);
					System.out.println("Depsited");
				}
				else
				{
					int destAccId=Integer.parseInt(request.getParameter("destAccId"));
					status=transactionService.transfer(srcAccId,destAccId, amount);
					System.out.println("Transferred");
				}
				if(status)
				{
					Account account = accountService.getAccountById(srcAccId);
					request.setAttribute("account", account);
					dispatcher=request.getRequestDispatcher("account.jsp");
					
					dispatcher.include(request, response);
					writer.println("<h2 style='text-align:center;'>" + (mode.charAt(0)+"").toUpperCase()+ mode.substring(1) +" Performed Successfully </h2>");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				statusMsg = "Something went wrong";
			}
			catch (CustomException e) 
			{
				e.printStackTrace();
				statusMsg=e.getMessage();
			}
			if(!status)
			{
				dispatcher=request.getRequestDispatcher("status.jsp");
				request.setAttribute("statusMsg", statusMsg);
				dispatcher.forward(request, response);
			}
		}
	}

}

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Account;
import com.google.gson.Gson;
import com.service.AccountService;
import com.utils.CustomException;


@WebServlet("/accountController")
public class AccountController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private AccountService accountService=new AccountService();
	public AccountController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mode=request.getParameter("mode");
		RequestDispatcher dispatcher=null;
		String confirm=request.getParameter("confirm");
		String view=request.getParameter("view");
		String acct=request.getParameter("accId");
		if(mode!=null && acct!=null && mode.equals("fetch") ) 
		{ 
			int accId=Integer.parseInt(acct);
			Account account=null;
			try {
				account = accountService.getAccountById(accId);
				Gson gson=new Gson();
				
				PrintWriter writer=response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				if(account!=null) {
				String msg=gson.toJson(account);
				writer.print(msg);
				writer.flush();
				
				}
				writer.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(mode!=null && mode.equals("create")) 
		{
			dispatcher=request.getRequestDispatcher("createaccount.jsp");
			dispatcher.forward(request, response);
		}
		else if(mode!=null && view!=null && (mode.equals("delete")||mode.equals("search")) && view.equals("query")) 
		{
			request.setAttribute("operation", mode);
			dispatcher=request.getRequestDispatcher("searchaccount.jsp");
			dispatcher.forward(request, response);
		}
		else if(mode!=null && view!=null && (mode.equals("search")||mode.equals("delete")) && view.equals("list")) 
		{
			request.setAttribute("confirm", confirm);
			response.setContentType("text/html");
			
			String searchBy = request.getParameter("searchBy");
			int searchId = Integer.parseInt(request.getParameter(searchBy));	
			try 
			{
				List<Account> accounts=null;
				if(searchBy.equals("custId")) 
				{
					accounts =accountService.getAccountByCustId(searchId);
				}
				else if(searchBy.equals("accId")) 
				{
					accounts=new ArrayList<>();
					Account acc=accountService.getAccountById(searchId);
					if(acc!=null)
						accounts.add(acc);
				}
				if(accounts.size()==0) 
				{
					request.setAttribute("statusMsg","No Account exists");
					dispatcher=request.getRequestDispatcher("status.jsp");
					dispatcher.forward(request, response);	
				}
				else 
				{	  
					request.setAttribute("accounts", accounts);
					request.setAttribute("operation", mode);
					dispatcher=request.getRequestDispatcher("accountlist.jsp");
					dispatcher.forward(request, response);	
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				e.getMessage();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mode=request.getParameter("mode");
		String confirm=request.getParameter("confirm");
		RequestDispatcher dispatcher = null;
		
		response.setContentType("text/html");
		if(mode!=null && mode.equals("create")) 
		{
			int custId=Integer.parseInt(request.getParameter("custId"));
			char accType=request.getParameter("type").charAt(0);
			Account account =new Account();
			account.setCustId(custId);
			account.setAccountType(accType);
			dispatcher=request.getRequestDispatcher("status.jsp");
			try 
			{
				accountService.createAccount(account);
				request.setAttribute("statusMsg", "Account created Successfully!");
        
			} catch (SQLException e) 
			{
				e.printStackTrace();
				request.setAttribute("statusMsg", "Something went Wrong");
			}
			catch (CustomException e) 
			{
				e.printStackTrace();
				request.setAttribute("statusMsg", e.getMessage());
			}
			dispatcher.forward(request, response);
		}
		
		else if(mode!=null && confirm!=null && confirm.equals("false") &&(mode.equals("delete")||mode.equals("search")))
		{
			
			int searchId = Integer.parseInt(request.getParameter("account"));		
			Account account;
			try 
			{
				account = accountService.getAccountById(searchId);
				if(account!=null)
				{
					  request.setAttribute("account", account);
					  if(mode.equals("search"))
			            	dispatcher=request.getRequestDispatcher("account.jsp");
					  else
			            	dispatcher=request.getRequestDispatcher("deleteaccount.jsp");
				}
	            else 
	            {
	            	request.setAttribute("statusMsg","No Account exists");
					dispatcher=request.getRequestDispatcher("status.jsp");
	            }
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				request.setAttribute("statusMsg", "Something went wrong");
				dispatcher=request.getRequestDispatcher("status.jsp");
			}
			dispatcher.forward(request, response);
		}
		else if(confirm!=null && mode!=null && confirm.equals("true") && mode.equals("delete") )
		{
			int accountId=Integer.parseInt(request.getParameter("accountId"));
			dispatcher = request.getRequestDispatcher("status.jsp");
			try 
			{
				accountService.deleteAccountByAccountId(accountId);
				String statusMsg = "Account deleted successfully";
				request.setAttribute("statusMsg", statusMsg);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			   request.setAttribute("statusMsg", e.getMessage());
			}
			catch (CustomException e) {
				e.printStackTrace();
				request.setAttribute("statusMsg", e.getMessage());
			}
			dispatcher.forward(request, response);
			
		}

	}

}

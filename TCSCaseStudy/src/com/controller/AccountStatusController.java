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

import com.bean.Account;
import com.service.AccountService;

@WebServlet("/accountStatusController")
public class AccountStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountStatusController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 	{
 		String param=request.getServletContext().getInitParameter("pageSize");
 		int pageSize=Integer.parseInt(param);
 		int page=Integer.parseInt(request.getParameter("page"));
 		RequestDispatcher dispatcher = null;
 		String statusMsg =null;
 		List<Account> accounts = new ArrayList<>();
 		AccountService service = new AccountService();
 		try 
 		{
 			
 			accounts = service.getAccountsByPageNumber(page,pageSize);
 			
 			int prev = page<=1?-1:page-1;
 			int next=accounts.size()==pageSize+1?page+1:-1;
 			if(accounts.size()==pageSize+1)
 				accounts.remove(accounts.size()-1);
 			
 			request.setAttribute("prev", prev);
 			request.setAttribute("next", next);
 			request.setAttribute("accounts", accounts);
 		    dispatcher=request.getRequestDispatcher("accountstatus.jsp");
 		    
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

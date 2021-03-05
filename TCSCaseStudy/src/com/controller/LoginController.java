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
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private UserService userService=new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession(false);
	if(session!=null && session.getAttribute("user")!=null) {
	response.sendRedirect("/TCSCaseStudy");	
	}else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String username=request.getParameter("username");
	String password=request.getParameter("password");
	System.out.println(request.getServletContext().getContextPath());
	response.setContentType("text/html");  
	try {
		User user = userService.getUser(username, password);
		RequestDispatcher dispatcher = null;
		HttpSession session=request.getSession();
		
		if(user==null) {
			
			PrintWriter writer=response.getWriter();
			
			dispatcher=request.getRequestDispatcher("status.jsp");
		   dispatcher.include(request, response);
		   writer.println("<h3 style='text-align:center;'>invalid credentials</h3>");
		}
		else {
			session.setAttribute("user", user);
			userService.setLastLogin(user);
		   response.sendRedirect(request.getContextPath());
		}
		
	} catch (SQLException e) 
	{
		e.printStackTrace();
	}
	}

}

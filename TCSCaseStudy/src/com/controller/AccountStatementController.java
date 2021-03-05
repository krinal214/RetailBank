package com.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.Transaction;
import com.service.TransactionService;
import com.utils.CustomException;
import com.utils.DownloadFile;

@WebServlet("/accountStatementController")
public class AccountStatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountStatementController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher=request.getRequestDispatcher("querystatement.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String param=request.getServletContext().getInitParameter("pageSize");
		int pageSize=Integer.parseInt(param);
		TransactionService service  = new TransactionService(); 
		String searchBy=request.getParameter("searchBy");
		int accId=Integer.parseInt(request.getParameter("accId"));
		
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("accId", accId);
		List<Transaction> transactions=new ArrayList<>();
		RequestDispatcher dispatcher=null;
		String statusMsg =null;
		String mode=request.getParameter("mode");
		if(searchBy!=null &&mode!=null && mode.equals("download")) 
		{
			if(searchBy.equals("date")) {
				Date start=Date.valueOf(request.getParameter("start"));
				Date end=Date.valueOf(request.getParameter("end"));
				try {
					transactions=service.getTransactionByDate(accId, start, end);
				} catch (SQLException e) {
					request.setAttribute("statusMsg", "Something went wrong");
					dispatcher = request.getRequestDispatcher("status.jsp");
					e.printStackTrace();
					dispatcher.forward(request, response);
				} catch (CustomException e) {
					request.setAttribute("statusMsg", e.getMessage());
					dispatcher = request.getRequestDispatcher("status.jsp");
					e.printStackTrace();
					dispatcher.forward(request, response);
				}
			}
			else
			{
				int number=Integer.parseInt(request.getParameter("number"));

				try {
					transactions=service.getTransactionByNumber(accId, number);
				} catch (SQLException e) {
					request.setAttribute("statusMsg", "Something went wrong");
					dispatcher = request.getRequestDispatcher("status.jsp");
					e.printStackTrace();
					dispatcher.forward(request, response);
				} catch (CustomException e) {
					e.printStackTrace();
					request.setAttribute("statusMsg", e.getMessage());
					dispatcher = request.getRequestDispatcher("status.jsp");
					dispatcher.forward(request, response);
				}
			}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=transactions.xlsx");
			XSSFWorkbook workbook = DownloadFile.getExcel(transactions);
			OutputStream os=response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();

		}
		else if(searchBy!=null && searchBy.equals("date")) 
		{
			int page=Integer.parseInt(request.getParameter("page"));
			System.out.println("Serch by date");
			Date start=Date.valueOf(request.getParameter("start"));
			Date end=Date.valueOf(request.getParameter("end"));
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			try 
			{
				transactions = service.getTransactionPageByDate(accId, start, end, page,pageSize);
				System.out.println("got the transactions by date");
				int prev=page<=1?-1:page-1;
				int next=transactions.size()==pageSize+1?page+1:-1;
				if(transactions.size()==pageSize+1)
					transactions.remove(transactions.size()-1);
				request.setAttribute("prev", prev);
				request.setAttribute("next", next);
				request.setAttribute("transactions", transactions);
			    dispatcher=request.getRequestDispatcher("accountstatement.jsp");
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				statusMsg="Something went wrong";
				dispatcher=request.getRequestDispatcher("status.jsp");
			}
			catch (CustomException e) 
			{
				e.printStackTrace();
				statusMsg=e.getMessage();
				dispatcher=request.getRequestDispatcher("status.jsp");
			}
			request.setAttribute("statusMsg", statusMsg);
			dispatcher.forward(request, response);
		}
		else if(searchBy!=null && searchBy.equals("number")) 
		{
			int page=Integer.parseInt(request.getParameter("page"));
			int number=Integer.parseInt(request.getParameter("number"));
			request.setAttribute("number", number);
			try 
			{
				
				transactions = service.getTransactionPageByNumber(accId, page,number,pageSize);
				
				int prev= page<=1?-1:page-1;
				int next=transactions.size()==pageSize+1?page+1:-1;
				if ( next>(number/pageSize)+1)next=-1;
				if(transactions.size()==pageSize+1)
					transactions.remove(transactions.size()-1);
				
				request.setAttribute("prev", prev);
				request.setAttribute("next", next);
				request.setAttribute("transactions", transactions);
			    dispatcher=request.getRequestDispatcher("accountstatement.jsp");
			    
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				statusMsg="Something went wrong";
				dispatcher=request.getRequestDispatcher("status.jsp");
				
			}
			catch (CustomException e) 
			{
				e.printStackTrace();
				statusMsg=e.getMessage();
				dispatcher=request.getRequestDispatcher("status.jsp");
			}
			request.setAttribute("statusMsg", statusMsg);
			dispatcher.forward(request, response);
			
		}
		
	}

}

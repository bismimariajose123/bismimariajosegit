package com.churchmanagementsystem.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.churchmanagementsystem.dao.BalanceDao;

/**
 * Servlet implementation class ConductNewwEventServlet
 */
@WebServlet("/ConductNewwEventServlet")
public class ConductNewwEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConductNewwEventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		String action=request.getParameter("action");

		if(action.equals("addDate"))
		{
			String eventName=request.getParameter("eventName");
			int eventid=Integer.parseInt(request.getParameter("eventid"));
			request.setAttribute("eventName", eventName);
			request.setAttribute("eventid", eventid);
			RequestDispatcher view = request.getRequestDispatcher("AddEventDate.jsp");
			view.forward(request, response);

		}
		else if(action.equals("ADD"))
		{
			String TBdate=request.getParameter("TBdate");  //date is in string formate dd/mm/yyyy
			String TBExpectedincome=request.getParameter("TBincome");
			String eventid=request.getParameter("hidden");
			BalanceDao objBalanceDao=new BalanceDao();
			try {
				int result=objBalanceDao.addEventDetails(eventid,TBExpectedincome,TBdate);
				if(result==1)
				{
					response.sendRedirect("ViewEventServlet?action=viewevent");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if(action.equals("Cancel"))
		{
			response.sendRedirect("ViewEventServlet?action=viewevent");
		}

	}

}

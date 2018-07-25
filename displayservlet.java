package com.churchmanagementsystem.servlet;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chruchmanagementsystem.bean.BalanceBean;

import com.churchmanagementsystem.dao.BalanceDao;


/**
 * Servlet implementation class displayservlet
 */
@WebServlet("/displayservlet")
public class displayservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displayservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		String action=request.getParameter("action");

		if(action.equals("search"))
		{
			try {

				String event_id=request.getParameter("id");

				int eventid=Integer.parseInt(event_id);
				// display selected  eventlist with date


				loadEventInDiv(request,response,eventid);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void loadEventInDiv(HttpServletRequest request, HttpServletResponse response, int eventid) throws SQLException, ServletException, IOException {
		//		List<EventBean> EventBeanlist=new ArrayList<EventBean>();
		//		EventDao objEventDao=new EventDao();
		//		EventBeanlist=objEventDao.getEventNameaandDate(eventid);
		List<BalanceBean> EventBeanlist=(List<BalanceBean>)ddlListEventName(request, response,eventid);


		request.setAttribute("EventBeanlist", EventBeanlist);	
		RequestDispatcher view = request.getRequestDispatcher("displayeventname.jsp");
		view.forward(request, response);

	}

	private List<BalanceBean> ddlListEventName(HttpServletRequest request, HttpServletResponse response, int eventid) throws SQLException {
		BalanceDao objBalanceDao=new BalanceDao();
		List<BalanceBean> eventlist=objBalanceDao.getEventNameforIncome(eventid);		
		return eventlist;
	}

}

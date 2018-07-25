package com.churchmanagementsystem.servlet;

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
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.BalanceBean;
import com.chruchmanagementsystem.bean.EventBean;
import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.dao.BalanceDao;
import com.churchmanagementsystem.dao.EventDao;
import com.churchmanagementsystem.dao.WardDao;

/**
 * Servlet implementation class ViewWardServlet
 */
@WebServlet("/ViewEventServlet")
public class ViewEventServlet extends HttpServlet {
	WardDao objWardDao=new WardDao();   

	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewEventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);


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
		try {
			if(action.equals("viewevent"))  //link from homepage
			{
				viewEvent(request,response);	

			}


			else if(action.equals("edit"))  //link from viewevent page
			{
				EventDao objEventDao=new EventDao();
				int rowid=Integer.parseInt(request.getParameter("eventid"));

				List<EventBean> eventlist=objEventDao.editEventName(rowid);
				request.setAttribute("eventlist", eventlist);
				RequestDispatcher view1 = request.getRequestDispatcher("EditEvent.jsp"); //forward eventlist to edit jsp 
				view1.forward(request, response);
			}
			else if(action.equals("viewEvent"))
			{
				List<BalanceBean> BalanceBeanlist=new ArrayList<BalanceBean>();
				String eventid=request.getParameter("eventid");
				int event_id=Integer.parseInt(eventid);
				String eventname=request.getParameter("eventname");
				BalanceDao objBalanceDao= new BalanceDao();
				BalanceBeanlist=objBalanceDao.getEventDetails(event_id);
				if(BalanceBeanlist.isEmpty())
				{
					request.setAttribute("errormsg", " expected amount not set for "+eventname);
					RequestDispatcher view1 = request.getRequestDispatcher("ViewEventDetails.jsp"); //forward eventlist to edit jsp 
					view1.forward(request, response);
				}
				request.setAttribute("BalanceBeanlist", BalanceBeanlist);
				RequestDispatcher view1 = request.getRequestDispatcher("ViewEventDetails.jsp"); //forward eventlist to edit jsp 
				view1.forward(request, response);

			}
			else if(action.equals("cancel"))
			{
				viewEvent(request,response);//return to view event
			}
			else if(action.equals("update"))  //update
			{
				int eventid=Integer.parseInt(request.getParameter("eventid"));   //get eventid from editevent page
				String TBeventname = request.getParameter("TBeventname");

				int result=updateEvent(request,response,eventid,TBeventname);
				if(result==1)
				{
					viewEvent(request,response);

				}
				else
				{
					System.out.println("not updated");
				}
				//				request.setAttribute("editward", eventlist);
				//				RequestDispatcher view1 = request.getRequestDispatcher("EditWard.jsp");
				//				view1.forward(request, response);

			}
			else if (action.equals("delete"))  //edit link
			{
				int eventid=Integer.parseInt(request.getParameter("eventid"));

				int result=deleteEvent(request,response,eventid);

				if(result==1)
				{

					PrintWriter out = response.getWriter(); 
					out.println("<script type=\"text/javascript\">");
					out.println("alert('deleted ward');");
					out.println("location='ViewEventServlet?action=viewevent';");
					out.println("</script>");



				}

			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int deleteEvent(HttpServletRequest request, HttpServletResponse response, int eventid) throws SQLException {
		EventDao objEventDao=new EventDao();
		int result=objEventDao.deleteEvent(eventid);
		return result;
	}

	private int updateEvent(HttpServletRequest request, HttpServletResponse response, int eventid, String tBeventname) {

		EventBean objEventBean=new EventBean();
		EventDao objEventDao=new EventDao();

		objEventBean.setEventid(eventid);
		objEventBean.setEventName(tBeventname);
		int result=0;
		try {
			result = objEventDao.updateEvent(objEventBean);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	private void viewEvent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		EventDao objEventDao=new EventDao();

		List<EventBean> eventlist=objEventDao.getEventName();
		request.setAttribute("eventlist", eventlist);	
		RequestDispatcher view = request.getRequestDispatcher("ViewEvent.jsp");
		view.forward(request, response);

	}

}

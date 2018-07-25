package com.churchmanagementsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.EventBean;
import com.churchmanagementsystem.dao.EventDao;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEventServlet() {
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
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");

		String action = request.getParameter("action");

		if(action.equals("ADD"))
		{

			EventBean objEventBean=new EventBean();
			EventDao objEventDao=new EventDao();

			String eventname = request.getParameter("TBeventname");
			if(eventname!=null)
			{

				int result=0;
				objEventBean.setEventName(eventname);
				try {

					result=objEventDao.addEvent(objEventBean);
					if(result==1)
					{
						PrintWriter out = response.getWriter(); 
						out.println("<script type=\"text/javascript\">");
						out.println("alert('added event');");
						out.println("location='AddEvent.jsp';");
						out.println("</script>");

					}
					else
					{
						PrintWriter out = response.getWriter(); 
						out.println("<script type=\"text/javascript\">");
						out.println("alert('value alresdy exists');");
						out.println("location='AddEvent.jsp';");
						out.println("</script>");
					}
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{

			}
		}
	}

}

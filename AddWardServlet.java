package com.churchmanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.WardBean;

import com.churchmanagementsystem.dao.WardDao;

/**
 * Servlet implementation class AddWardServlet
 */
@WebServlet("/AddWardServlet")
public class AddWardServlet extends HttpServlet {

	WardBean objAddWardBean=new WardBean();
	WardDao objAddWardDao=new WardDao();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddWardServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		// TODO Auto-generated method stub
		String wardname = request.getParameter("TBwardname");
		if(wardname!=null)
		{
			int result=0;
			objAddWardBean.setWardName(wardname);
			try {

				result=objAddWardDao.addWard(objAddWardBean);
				if(result==1)
				{
					request.setAttribute("success", "inserted successfully");
					RequestDispatcher view1 = request.getRequestDispatcher("AddWard.jsp"); //forward eventlist to edit jsp 
					view1.forward(request, response);
				}

				else{
					request.setAttribute("success", "value exists");
					RequestDispatcher view1 = request.getRequestDispatcher("AddWard.jsp"); //forward eventlist to edit jsp 
					view1.forward(request, response);
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

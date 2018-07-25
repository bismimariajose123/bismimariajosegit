package com.churchmanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.LoginBean;
import com.churchmanagementsystem.dao.LoginDao;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
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
		// TODO Auto-generated method stub
		LoginBean objLoginBean=new LoginBean();
		LoginDao objLoginDao=new LoginDao();
		objLoginBean.setUsername(request.getParameter("TBusername"));
		objLoginBean.setPassword(request.getParameter("TBpassword"));
		objLoginBean.setUsertype(request.getParameter("TBusertype"));
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		int result=0;

		try {
			result=objLoginDao.addUser(objLoginBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==1)
		{
			response.sendRedirect("AddUser.jsp");
		}
	}

}

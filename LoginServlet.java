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

import com.chruchmanagementsystem.bean.LoginBean;
import com.churchmanagementsystem.dao.LoginDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginBean objLoginBean=new LoginBean();
	LoginDao objLoginDao=new LoginDao();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String action=request.getParameter("action");
		String userName = request.getParameter("TBusername");
		String password = request.getParameter("TBpassword");
		objLoginBean.setUsername(userName);
		objLoginBean.setPassword(password);
		session.setAttribute("uname",userName);  
		session.setAttribute("pwd",password);  
		if(action.equals("logout"))
		{

			request.getRequestDispatcher("Login.jsp").include(request, response);  

			session.setAttribute("uname",userName); 
			session.invalidate();  
		}
		else if(action.equals("Login"))
		{


			// System.out.println("UserName "+userName);
			///	 System.out.println("password "+password);
			try {
				String result=(String) objLoginDao.validateUserLogin(objLoginBean);

				if(result.equals("Admin"))
				{
					response.sendRedirect("AdminHome.jsp");
				}
				else if(result.equals("Priest"))
				{

					response.sendRedirect("PriestHome.jsp");
				}
				else if(result.equals("Accountant"))
				{
					response.sendRedirect("AccountantHome.jsp");
				}
				else
				{
					PrintWriter out = response.getWriter(); 
					//  out.println("<script>alert('invalid login');</script>");
					//				  response.sendRedirect("Login.jsp");

					out.println("<script type=\"text/javascript\">");
					out.println("alert('User or password incorrect');");
					out.println("location='Login.jsp';");
					out.println("</script>");


				}

			}
			catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}

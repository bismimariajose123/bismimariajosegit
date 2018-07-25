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


import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.dao.FamilyDao;
import com.churchmanagementsystem.dao.WardDao;

/**
 * Servlet implementation class AddFamilyServlet
 */
@WebServlet("/AddFamilyServlet")
public class AddFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFamilyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		String action=request.getParameter("action");
		if(action==null)
		{
			System.out.println("submit not working");
		}
		else if(action.equals("linkaddfamily"))
		{

			ddlListWardName(request,response);  //load wardname to drop down list in addfamily.jsp


		}
	}

	private void ddlListWardName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WardDao objWardDao=new WardDao();
		List<WardBean> wardlist=new ArrayList<WardBean>();
		try 
		{
			wardlist=objWardDao.viewWard();
			request.setAttribute("wardlist", wardlist);
			RequestDispatcher view = request.getRequestDispatcher("AddFamily.jsp");
			view.forward(request, response);

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try
		{
			String action=request.getParameter("action");

			if(action==null)
			{
				System.out.println("submit not working");
			}
			if(action.equals("ADD"))   //from addfamily.jsp
			{
				int seletedWardId=Integer.parseInt(request.getParameter("DDLWardName"));
				String familyname=request.getParameter("TBFamilyName");

				int result=addFamily(request,response,seletedWardId,familyname);
				if(result==1)
				{
					ddlListWardName(request,response);
				}
				else
				{
					PrintWriter out = response.getWriter(); 
					out.println("<script type=\"text/javascript\">");
					out.println("alert('value alresdy exists');");
					out.println("location='AddFamilyServlet?action=linkaddfamily';");
					out.println("</script>");
				}
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private int addFamily(HttpServletRequest request, HttpServletResponse response, int seletedWardId,String familyname) throws SQLException {

		WardBean objWardBean=new WardBean();
		FamilyDao objFamilyDao=new FamilyDao();
		objWardBean.setWardid(seletedWardId);
		int result=objFamilyDao.addFamily(objWardBean,familyname);
		return result;
	}

}

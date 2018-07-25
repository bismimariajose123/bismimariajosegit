package com.churchmanagementsystem.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.churchmanagementsystem.dao.FamilyDao;

/**
 * Servlet implementation class ViewFamilyServlet
 */
@WebServlet("/ViewFamilyServlet")
public class ViewFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewFamilyServlet() {
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
		else if(action.equals("linkviewfamily"))
		{			
			viewfamily(request,response); 

		}
		else if(action.equals("search"))
		{
			String TBSearch=request.getParameter("TBSearch");
			searchFamily(request,response,TBSearch);
		}


	}

	private void searchFamily(HttpServletRequest request, HttpServletResponse response, String tBSearch) {
		FamilyDao objFamilyDao=new FamilyDao();

		List<FamilyMemberDetailsBean> list=new ArrayList<FamilyMemberDetailsBean>();
		try
		{
			list=objFamilyDao.viewFamilybySearch(tBSearch);
			if(!list.isEmpty())
			{	

				request.setAttribute("familyDetalsList", list);
				RequestDispatcher view = request.getRequestDispatcher("ViewFamily.jsp");
				view.forward(request, response);
			}
			else
			{
				request.setAttribute("errormsg", "invalid name");
				viewfamily(request,response); 
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void viewfamily(HttpServletRequest request, HttpServletResponse response) {
		FamilyDao objFamilyDao=new FamilyDao();

		List<FamilyMemberDetailsBean> list=new ArrayList<FamilyMemberDetailsBean>();
		try
		{
			list=objFamilyDao.viewFamily();
			request.setAttribute("familyDetalsList", list);
			RequestDispatcher view = request.getRequestDispatcher("ViewFamily.jsp");
			view.forward(request, response);

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

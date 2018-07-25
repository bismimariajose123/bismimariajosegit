package com.churchmanagementsystem.servlet;

import java.io.IOException;
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

import com.chruchmanagementsystem.bean.FamilyBean;
import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.dao.FamilyDao;
import com.churchmanagementsystem.dao.WardDao;

/**
 * Servlet implementation class AddFamilyMemberServlet
 */
@WebServlet("/AddFamilyMemberServlet")
public class AddFamilyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFamilyMemberServlet() {
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
		else if(action.equals("linkaddfamilymembers"))
		{		
			addFamilyMembers(request, response);


		}
		else if(action.equals("ADD"))  //insert details of family members into table
		{
			FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();
			objFamilyMemberDetailsBean.setFamilyId(Integer.parseInt(request.getParameter("DDLFamilyName")));
			objFamilyMemberDetailsBean.setWardid(Integer.parseInt(request.getParameter("DDLWardName")));
			objFamilyMemberDetailsBean.setContact(request.getParameter("TBContactNumber"));
			objFamilyMemberDetailsBean.setEmail(request.getParameter("TBEmail"));
			objFamilyMemberDetailsBean.setGender(request.getParameter("DDLGender"));
			objFamilyMemberDetailsBean.setOfficialName(request.getParameter("TBOfficialName"));

			int result=0;
			try {
				result = addFamilyMemberDetails(request,response,objFamilyMemberDetailsBean);   //call private method
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result==1)
			{
				addFamilyMembers(request, response);//return to add family members.jsp
			}
		}

	}

	private int addFamilyMemberDetails(HttpServletRequest request, HttpServletResponse response,
			FamilyMemberDetailsBean objFamilyMemberDetailsBean) throws SQLException {

		FamilyDao objFamilyDao=new FamilyDao();
		int result=objFamilyDao.insertFamilyMemberDetails(objFamilyMemberDetailsBean);  //call method in dao

		return result;
	}

	private void addFamilyMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 
		List<FamilyBean> familylist=(List<FamilyBean>)	ddlListFamilyName(request,response); //load wardname and familyname to drop down list in addfamilymembers.jsp			

		request.setAttribute("wardlist", wardlist);
		request.setAttribute("familylist", familylist);
		RequestDispatcher view = request.getRequestDispatcher("AddFamilyMember.jsp");
		view.forward(request, response);

	}

	//ddl family name
	private List<FamilyBean> ddlListFamilyName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FamilyDao objFamilyDao=new FamilyDao();
		List<FamilyBean> familylist=new ArrayList<FamilyBean>();
		try 
		{
			familylist=objFamilyDao.viewFamilyName();

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return familylist;

	}

	//ddl ward name
	private List<WardBean> ddlListWardName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WardDao objWardDao=new WardDao();
		List<WardBean> wardlist=new ArrayList<WardBean>();
		try 
		{
			wardlist=objWardDao.viewWard();

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wardlist;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

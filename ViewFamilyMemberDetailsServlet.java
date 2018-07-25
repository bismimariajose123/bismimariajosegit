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

import com.churchmanagementsystem.dao.FamilyMemberDetailsDao;
import com.churchmanagementsystem.dao.IncomeDao;
import com.churchmanagementsystem.dao.WardDao;
import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.chruchmanagementsystem.bean.WardBean;
/**
 * Servlet implementation class ViewFamilyMemberDetails
 */
@WebServlet("/ViewFamilyMemberDetailsServlet")
public class ViewFamilyMemberDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewFamilyMemberDetailsServlet() {
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

		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		String action=request.getParameter("action");//link
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		try {
			if(action==null)
			{
				System.out.println("submit not working");
			}


			else if(action.equals("viewfamilymember"))// link 
			{	
				loadWardDetails(request, response);

			}
			else if(action.equals("select"))
			{
				loadWardDetails(request, response);
			}
			else if(action.equals("Viewmoredetails"))
			{
				String ddlwardname=request.getParameter("DDLWardName");
				String familyid=request.getParameter("familyid");
				int wardid=Integer.parseInt(ddlwardname);
				int family_id=Integer.parseInt(familyid);	
				session.setAttribute("wardid", wardid);
				session.setAttribute("family_id", family_id);
				viewfamilyMember(request,response,wardid,family_id);

			}
			else if(action.equals("Search"))
			{
				String ddlwardname=request.getParameter("DDLWardName");
				if(ddlwardname.equals(null)||ddlwardname.equals("select") )
				{
					request.setAttribute("errormsg", "select ward name");
					loadWardDetails(request, response);
				}
				else
				{
					int wardid=Integer.parseInt(ddlwardname);


					viewfamilyMemberDetails(request,response,wardid);
				}
			}
			else if(action.equals("edit"))
			{
				String memberid=request.getParameter("memberid");
				int id=Integer.parseInt(memberid);
				List<FamilyMemberDetailsBean> familymemberlist=new ArrayList<FamilyMemberDetailsBean>();
				familymemberlist=editMemberDetails(request,response,id);

				request.setAttribute("familymemberlist", familymemberlist);
				RequestDispatcher view1 = request.getRequestDispatcher("EditMemberDetails.jsp");
				view1.forward(request, response);
			}

			else if(action.equals("update"))
			{
				FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();
				String memberid = request.getParameter("memberid");		
				objFamilyMemberDetailsBean.setMemberid(Integer.parseInt(memberid));
				objFamilyMemberDetailsBean.setOfficialName(request.getParameter("TBMembername"));
				objFamilyMemberDetailsBean.setContact(request.getParameter("TBContact"));

				objFamilyMemberDetailsBean.setGender(request.getParameter("TBGender"));
				objFamilyMemberDetailsBean.setEmail(request.getParameter("TBEmail"));

				int result=FamilyMemberDetailsDao.updateWard(objFamilyMemberDetailsBean);
				if(result==1)
				{
					//loadWardDetails(request, response);
					int wardid=(int) session.getAttribute("wardid");
					int family_id=(int) session.getAttribute("family_id");
					viewfamilyMember(request,response,wardid,family_id);
				}
				else
				{
					System.out.println("not updated");
				}

			}
			else if(action.equals("cancel"))
			{
				//loadWardDetails(request, response);
				int wardid=(int) session.getAttribute("wardid");
				int family_id=(int) session.getAttribute("family_id");
				viewfamilyMember(request,response,wardid,family_id);
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private void viewfamilyMemberDetails(HttpServletRequest request, HttpServletResponse response, int wardid) throws SQLException, ServletException, IOException {
		FamilyMemberDetailsDao objFamilyMemberDetailsDao=new FamilyMemberDetailsDao();

		List<FamilyMemberDetailsBean> familymemberlist=new ArrayList<FamilyMemberDetailsBean>(); 
		familymemberlist=objFamilyMemberDetailsDao.viewfamilyMemberDetails(wardid);  //load family member details based on id

		if(!familymemberlist.isEmpty())
		{

			String wardname=getWardname(request,response,wardid);  //backup wardname
			request.setAttribute("wardname", wardname);	

			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 	
			request.setAttribute("wardlist", wardlist);	

			request.setAttribute("familymemberlist", familymemberlist);
			RequestDispatcher view = request.getRequestDispatcher("ViewFamilyMember.jsp");
			view.forward(request, response);
		}
		else
		{
			String wardname=getWardname(request,response,wardid);  //backup wardname
			request.setAttribute("wardname", wardname);	
			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 	
			request.setAttribute("errormsg", "empty list , add members");	
			request.setAttribute("wardlist", wardlist);	
			RequestDispatcher view = request.getRequestDispatcher("ViewFamilyMember.jsp");
			view.forward(request, response);
		}

	}

	private void loadWardDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 	
		request.setAttribute("wardlist", wardlist);		
		RequestDispatcher view = request.getRequestDispatcher("ViewFamilyMember.jsp");  
		view.forward(request, response);

	}

	private List<WardBean> ddlListWardName(HttpServletRequest request, HttpServletResponse response) {
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

	private List<FamilyMemberDetailsBean> editMemberDetails(HttpServletRequest request, HttpServletResponse response,
			int id) throws SQLException {


		FamilyMemberDetailsDao objFamilyMemberDetailsDao=new FamilyMemberDetailsDao();
		List<FamilyMemberDetailsBean>  memberlist=new ArrayList<FamilyMemberDetailsBean>();

		memberlist=objFamilyMemberDetailsDao.editMemberList(id);
		return memberlist;		
	}

	private void viewfamilyMember(HttpServletRequest request, HttpServletResponse response, int wardid, int familyid) throws SQLException, ServletException, IOException {


		FamilyMemberDetailsDao objFamilyMemberDetailsDao=new FamilyMemberDetailsDao();

		List<FamilyMemberDetailsBean> familymemberlist=new ArrayList<FamilyMemberDetailsBean>(); 
		familymemberlist=objFamilyMemberDetailsDao.viewfamilyMember(wardid,familyid);  //load family member details based on id

		if(familymemberlist.isEmpty())
		{
			request.setAttribute("errormsg", "no details in searched string");
			RequestDispatcher view = request.getRequestDispatcher("ViewFamilyServlet?action=linkviewfamily");
			view.forward(request, response);
			//response.sendRedirect("ViewFamilyServlet?action=linkviewfamily");
		}
		else
		{


			String wardname=getWardname(request,response,wardid);  //backup wardname
			request.setAttribute("wardname", wardname);	

			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 	
			request.setAttribute("wardlist", wardlist);	

			request.setAttribute("familymemberlist", familymemberlist);
			RequestDispatcher view = request.getRequestDispatcher("ViewFamilyMember.jsp");
			view.forward(request, response);
		}
	}

	private String getWardname(HttpServletRequest request, HttpServletResponse response, int wardid) throws SQLException {
		IncomeDao objIncomeDao=new IncomeDao();
		String wardname=objIncomeDao.getWardName(wardid);
		return wardname; 
	}

}

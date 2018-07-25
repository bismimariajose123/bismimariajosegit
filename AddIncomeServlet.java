package com.churchmanagementsystem.servlet;

import java.io.IOException;

import java.sql.SQLException;

import java.text.ParseException;

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

import com.chruchmanagementsystem.bean.FamilyBean;
import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.chruchmanagementsystem.bean.IncomeBean;
import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.dao.BalanceDao;

import com.churchmanagementsystem.dao.FamilyDao;
import com.churchmanagementsystem.dao.FamilyMemberDetailsDao;
import com.churchmanagementsystem.dao.IncomeDao;
import com.churchmanagementsystem.dao.WardDao;



/**
 * Servlet implementation class AddIncomeServlet
 */
@WebServlet("/AddIncomeServlet")
public class AddIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddIncomeServlet() {
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
		String action=request.getParameter("action");
		try {


			if(action.equals("Addincome"))  //addincomelink
			{

				addIncome(request, response);
				//load ward names in ddl
			}

			else if (action.equals("Search")) // load family names in ddl
			{


				String ddlwardname=request.getParameter("DDLWardName");
				int wardid=Integer.parseInt(ddlwardname);

				String ddlfamilyname=request.getParameter("DDLFamilyName");
				int familyid=Integer.parseInt(ddlfamilyname);

				String ddlofficialname=request.getParameter("DDLOfficialName");
				int memberid=Integer.parseInt(ddlofficialname);



				if(wardid==0 && familyid==0 && memberid==0)  //add income link
				{
					request.setAttribute("errormsg", "select ward");
					List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 		
					request.setAttribute("wardlist", wardlist);		
					RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
					view.forward(request, response);

				}

				else if(wardid >0 && familyid==0 && memberid==0) //check wardid not 0
				{

					getFamilyInWard(request, response,wardid);//load family name based on wardid

				}
				else if(wardid>0 && familyid>=0 && memberid==0)//check familyid not 0
				{
					String account="";
					String date="";

					getMemberbasedFamily(request, response,wardid,familyid,memberid,account,date);//load member name based on familyid

				}

			} 




			else if(action.equals("ADD"))
			{


				String ddlwardname=request.getParameter("DDLWardName");
				String ddlfamilyname=request.getParameter("DDLFamilyName");
				String ddlofficialname=request.getParameter("DDLOfficialName");
				String TBAmount=request.getParameter("TBAmount");
				String TBdate=request.getParameter("TBdate");
				String ddleventname=request.getParameter("DDLEventName");

				//validation
				if(ddlwardname.equals("0") || ddlfamilyname.equals("0")|| ddlofficialname.equals("0"))
				{

					List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 		
					request.setAttribute("wardlist", wardlist);	
					request.setAttribute("errormsgall", "select the fields");
					RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
					view.forward(request, response);
				}
				else if(TBAmount.equals("")){  //amount is empty
					int ddlwardname1=Integer.parseInt(ddlwardname);
					int ddlfamilyname2=Integer.parseInt(ddlfamilyname);
					int  ddlofficialname2=Integer.parseInt(ddlofficialname);
					//session.setAttribute("ddlofficialname2", ddlofficialname2);
					getMemberbasedFamily(request, response,ddlwardname1,ddlfamilyname2,ddlofficialname2,TBAmount,TBdate);//load member name based on familyid
				}
				else if(TBdate.equals(""))
				{
					int ddlwardname1=Integer.parseInt(ddlwardname);
					int ddlfamilyname2=Integer.parseInt(ddlfamilyname);
					int  ddlofficialname2=Integer.parseInt(ddlofficialname);
					getMemberbasedFamily(request, response,ddlwardname1,ddlfamilyname2,ddlofficialname2,TBAmount,TBdate);//load member name based on familyid
				}
// /- ----- validation
				else
				{


					int memberid=Integer.parseInt(ddlofficialname);
					try {
						int balanceid=Integer.parseInt(ddleventname);
						addIncomeDetails(request,response,memberid,balanceid);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			else if(action.equals("RESET"))
			{
				addIncome(request, response);
			}


			else if(action.equals(null))
			{
				System.out.println("null");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addIncomeDetails(HttpServletRequest request, HttpServletResponse response, int memberid, int balanceid2) throws ParseException, SQLException, IOException, ServletException {
		IncomeBean objIncomeBean=new IncomeBean();
		objIncomeBean.setAMOUNT(request.getParameter("TBAmount"));
		String date=request.getParameter("TBdate");
		objIncomeBean.setDATERECEIVED(date);
		objIncomeBean.setMEMBER_ID(memberid);
		IncomeDao objIncomeDao=new IncomeDao();
		int result=objIncomeDao.addIncome(objIncomeBean,balanceid2);
		if(result==1)
		{

			addIncome(request, response);
		}
	}
	private void getMemberbasedFamily(HttpServletRequest request, HttpServletResponse response, int wardId, int familyid,int ddlofficialname2, String account,String date) throws ServletException, IOException, SQLException {


		if(account.equals(""))
		{
			List<FamilyMemberDetailsBean> familyMemberList=(List<FamilyMemberDetailsBean>)ddlFamilyMember(request,response,familyid); // get official name list
			request.setAttribute("familyMemberList", familyMemberList);

			List<BalanceBean> eventlist=(List<BalanceBean>)ddlListEventName(request, response);  //get eventlist
			request.setAttribute("eventlist", eventlist);


			List<FamilyBean> familylist=(List<FamilyBean>)ddlListFamilyName(request, response); //backup familylist
			request.setAttribute("familylist", familylist);

			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); //backup wardlist
			request.setAttribute("wardlist", wardlist);	


			String wardname=getWardname(request,response,wardId);  //backup wardname
			request.setAttribute("wardname", wardname);	

			String officialname=getOfficialName(request,response,ddlofficialname2); //backup official name
			request.setAttribute("Officialname", officialname);


			String familyname=getFamilyName(request,response,familyid);//backup familyname
			// request.setAttribute("amount", account);
			request.setAttribute("familyname", familyname);
			request.setAttribute("errormsgaccount", "enter amount");
			RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
			view.forward(request, response);
		}
		else if(date.equals(""))
		{
			List<FamilyMemberDetailsBean> familyMemberList=(List<FamilyMemberDetailsBean>)ddlFamilyMember(request,response,familyid); // get official name list
			request.setAttribute("familyMemberList", familyMemberList);

			List<BalanceBean> eventlist=(List<BalanceBean>)ddlListEventName(request, response);  //get eventlist
			request.setAttribute("eventlist", eventlist);


			List<FamilyBean> familylist=(List<FamilyBean>)ddlListFamilyName(request, response); //backup familylist
			request.setAttribute("familylist", familylist);

			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); //backup wardlist
			request.setAttribute("wardlist", wardlist);	

			String officialname=getOfficialName(request,response,ddlofficialname2); //backup official name
			request.setAttribute("Officialname", officialname);


			String wardname=getWardname(request,response,wardId);  //backup wardname
			request.setAttribute("wardname", wardname);	

			String familyname=getFamilyName(request,response,familyid);//backup familyname
			request.setAttribute("familyname", familyname);
			request.setAttribute("errormsgdate", "enter date");
			RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
			view.forward(request, response);

		}
		else
		{



			List<FamilyMemberDetailsBean> familyMemberList=(List<FamilyMemberDetailsBean>)ddlFamilyMember(request,response,familyid); // get official name list
			request.setAttribute("familyMemberList", familyMemberList);

			List<BalanceBean> eventlist=(List<BalanceBean>)ddlListEventName(request, response);  //get eventlist
			request.setAttribute("eventlist", eventlist);


			List<FamilyBean> familylist=(List<FamilyBean>)ddlListFamilyName(request, response); //backup familylist
			request.setAttribute("familylist", familylist);

			List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); //backup wardlist
			request.setAttribute("wardlist", wardlist);	

			String officialname=getOfficialName(request,response,ddlofficialname2); //backup official name
			request.setAttribute("Officialname", officialname);

			String wardname=getWardname(request,response,wardId);  //backup wardname
			request.setAttribute("wardname", wardname);	

			String familyname=getFamilyName(request,response,familyid);//backup familyname
			request.setAttribute("familyname", familyname);

			RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
			view.forward(request, response);


		}

	}

	private String getOfficialName(HttpServletRequest request, HttpServletResponse response, int ddlofficialname2) throws SQLException {
		FamilyDao objFamilyDao=new FamilyDao();
		String officialname=objFamilyDao.getOfficialName(ddlofficialname2);

		return officialname;
	}

	private String getFamilyName(HttpServletRequest request, HttpServletResponse response, int familyid) throws SQLException {

		IncomeDao objIncomeDao=new IncomeDao();
		String familyname=objIncomeDao.getFamilyName(familyid);
		return familyname; 
	}

	private List<BalanceBean> ddlListEventName(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		BalanceDao objBalanceDao=new BalanceDao();
		List<BalanceBean> eventlist=objBalanceDao.getEventNameforIncome();		
		return eventlist;

	}



	private List<FamilyMemberDetailsBean> ddlFamilyMember(HttpServletRequest request, HttpServletResponse response, int familyid2) {

		List<FamilyMemberDetailsBean> memberlistlist=null;
		try {
			memberlistlist = searchMemberwithid(familyid2);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberlistlist;
	}

	private List<FamilyMemberDetailsBean> searchMemberwithid(int familyid2) throws SQLException {
		FamilyMemberDetailsDao objFamilyMemberDetailsDao=new FamilyMemberDetailsDao();	

		List<FamilyMemberDetailsBean> memberlist=objFamilyMemberDetailsDao.getMemberNameList(familyid2);
		return memberlist;
	}

	private void getFamilyInWard(HttpServletRequest request, HttpServletResponse response, int wardid) throws ServletException, IOException, SQLException {


		List<FamilyBean> familylist=(List<FamilyBean>)ddlListFamilyName(request, response);//assign familylist
		request.setAttribute("familylist", familylist);

		List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response);  //backup wardlist
		request.setAttribute("wardlist", wardlist);		

		String wardname=getWardname(request,response,wardid);  //backup wardname
		request.setAttribute("wardname", wardname);	



		RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
		view.forward(request, response);

	}

	private String getWardname(HttpServletRequest request, HttpServletResponse response, int wardid) throws SQLException {
		IncomeDao objIncomeDao=new IncomeDao();
		String wardname=objIncomeDao.getWardName(wardid);
		return wardname; 
	}

	// load family names in ddl
	private List<FamilyBean> ddlListFamilyName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wardid=request.getParameter("DDLWardName");
		List<FamilyBean> familylist=null;
		try {
			familylist = searchFamilywithid(wardid);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return familylist;

	}

	private List<FamilyBean> searchFamilywithid(String wardid) throws SQLException {
		FamilyDao objFamilyDao=new FamilyDao();	
		int id=Integer.parseInt(wardid);
		List<FamilyBean> familylist=objFamilyDao.getFamilyNameList(id);
		return familylist;

	}


	//load ward names in ddl
	private void addIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<BalanceBean> eventlist=(List<BalanceBean>)ddlListEventName(request, response);  //get eventlist
		List<WardBean> wardlist=(List<WardBean>)ddlListWardName(request,response); 		
		request.setAttribute("wardlist", wardlist);	
		request.setAttribute("eventlist", eventlist);
		RequestDispatcher view = request.getRequestDispatcher("AddIncome.jsp");
		view.forward(request, response);

	}

	//	private List<FamilyBean> ddlListFamilyName(HttpServletRequest request, HttpServletResponse response) {
	//		FamilyDao objFamilyDao=new FamilyDao();
	//		List<FamilyBean> familylist=new ArrayList<FamilyBean>();
	//		try 
	//		{
	//			familylist=objFamilyDao.viewFamilyName();
	//			
	//		} 
	//		catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}	
	//		return familylist;
	//	}

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

}

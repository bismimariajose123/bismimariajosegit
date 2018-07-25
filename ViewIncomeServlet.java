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

import com.chruchmanagementsystem.bean.EventBean;

import com.chruchmanagementsystem.bean.IncomeDetailsBean;
import com.churchmanagementsystem.dao.BalanceDao;
import com.churchmanagementsystem.dao.EventDao;
import com.churchmanagementsystem.dao.IncomeDao;


/**
 * Servlet implementation class ViewIncomeServlet
 */
@WebServlet("/ViewIncomeServlet")
public class ViewIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewIncomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		String action=request.getParameter("action");

		if(action.equals("Viewincome")) //view income link
		{
			List<EventBean> eventlist=null;
			try {
				eventlist = (List<EventBean>)ddlListEventName(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("eventlist", eventlist);	
			RequestDispatcher view = request.getRequestDispatcher("ViewIncome.jsp");
			view.forward(request, response);
		}





		else if(action.equals("viewevent"))  //link from displayevent.jsp
		{

			try {
				String balanceid=request.getParameter("balanceid");
				//String datesearch1=request.getParameter("eventdate");

				int balance_id=Integer.parseInt(balanceid);


				viewincomedetails(request,response,balance_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


	// view income details from div
	private void viewincomedetails(HttpServletRequest request, HttpServletResponse response, int balanceid) throws SQLException, ServletException, IOException {
		IncomeDao objIncomeDao=new IncomeDao();
		List<IncomeDetailsBean> incomeList=null;
		String totalamount=null; 
		String eventname=null;
		List<String> t_amount=null;

		BalanceDao objBalanceDao=new BalanceDao();
		
		incomeList=objIncomeDao.getIncomeListByBalanceid(balanceid);  //get inome details based on selected event from displayeventname.jsp(div)
		totalamount=objIncomeDao.getTotalAmount(balanceid);
		eventname=objIncomeDao.getEventnamefromBalanceid(balanceid);  //eventname for backup
		String expectedincome=objBalanceDao.getExpectedIncomeForEvent(balanceid);

		if(incomeList.isEmpty() && totalamount==null)
		{

			List<EventBean> eventlist=null;

			eventlist = (List<EventBean>)ddlListEventName(request, response);
			request.setAttribute("errormsg","no values, empty list");
			request.setAttribute("eventlist", eventlist);	

			RequestDispatcher view = request.getRequestDispatcher("ViewIncome.jsp");
			view.forward(request, response);
		}
		else  
		{

			Double percent_covered= (double) ((Integer.parseInt(totalamount)*100*1.0)/Integer.parseInt(expectedincome));
			System.out.println(percent_covered);
			String Percent_covered=String.valueOf(percent_covered);

			t_amount=new ArrayList<String>();
			List<String> exp_amount=new ArrayList<String>();
			List<String> per_covered=new ArrayList<String>();
			t_amount.add(totalamount);
			exp_amount.add(expectedincome);
			per_covered.add(Percent_covered);

			request.setAttribute("incomeList", incomeList);	
			request.setAttribute("totalamount", t_amount);
			request.setAttribute("eventname", eventname);
			request.setAttribute("expectedamount", exp_amount);
			request.setAttribute("percent_covered", per_covered);

			List<EventBean> eventlist=null;
			eventlist = (List<EventBean>)ddlListEventName(request, response);

			request.setAttribute("eventlist", eventlist);	

			RequestDispatcher view = request.getRequestDispatcher("ViewIncome.jsp");
			view.forward(request, response);
		}

	}




	private List<EventBean> ddlListEventName(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		EventDao objEventDao=new EventDao();
		List<EventBean> eventlist=objEventDao.getEventName();

		return eventlist;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}

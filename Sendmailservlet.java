package com.churchmanagementsystem.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chruchmanagementsystem.bean.IncomeDetailsBean;
import com.churchmanagementsystem.SendMail.SendMail;
import com.churchmanagementsystem.dao.IncomeDao;
import com.churchmanagementsystem.dao.MailDao;
import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class Sendmailservlet
 */
@WebServlet("/Sendmailservlet")
public class Sendmailservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sendmailservlet() {
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
		MailDao objMail=new MailDao();
		response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Cache-Control","no-store"); 
		response.setDateHeader("Expires", 0); 
		response.setHeader("Pragma","no-cache");
		HttpSession session=request.getSession(); 
		String username=(String)session.getAttribute("uname");
		String action=request.getParameter("action");
		if(action.equals("sendmail")) //view income link
		{
			String incomeid=request.getParameter("incomeid");
			int income_id=Integer.parseInt(incomeid);
			try {
				String mailid=objMail.getMailidofuser(income_id);
				System.out.println("mailid"+mailid);
				if(!mailid.equals(null))
				{
					mailid="extern_jose.bismi@allianz.com";
				}
				request.setAttribute("mailid", mailid);
				RequestDispatcher view = request.getRequestDispatcher("Sendmail.jsp");
				view.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("send"))
		{
			try {
				sendmail(request,response);

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("DownloadReport"))
		{
			String balanceid=request.getParameter("balanceid");
			try {
				downloadreport(balanceid,request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void downloadreport(String balanceid, HttpServletRequest request, HttpServletResponse response) throws SQLException, DocumentException, IOException {

		IncomeDao objIncomeDao=new IncomeDao();
		objIncomeDao.downloadreport(balanceid);
		PrintWriter out = response.getWriter(); 
		out.println("<script type=\"text/javascript\">");
		out.println("alert('report downloaded successfully');");
		out.println("location='ViewEventServlet?action=viewEvent';");
		out.println("</script>");
	}

	private void sendmail(HttpServletRequest request, HttpServletResponse response) throws AddressException, MessagingException, IOException {
		String TBTo=request.getParameter("TBTo");
		String TBFrom=request.getParameter("TBFrom");
		String TBBody=request.getParameter("TBBody");
		String TBSubject=request.getParameter("TBSubject");
		SendMail objSendMail=new SendMail();
		objSendMail.sendPlainTextEmail(TBSubject, TBTo, TBFrom, TBBody);
		PrintWriter out = response.getWriter(); 
		out.println("<script type=\"text/javascript\">");
		out.println("alert('mail sent successfully');");
		out.println("location='ViewEventServlet?action=viewevent';");
		out.println("</script>");

	}

}

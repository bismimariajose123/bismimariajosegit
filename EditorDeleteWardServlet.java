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
import com.churchmanagementsystem.dao.WardDao;

/**
 * Servlet implementation class EditWardServlet
 */
@WebServlet("/EditorDeleteWardServlet")
public class EditorDeleteWardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditorDeleteWardServlet() {
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
		//read "command" parameter from viewward.jsp
		try
		{

			String action = request.getParameter("action");
			String wardid=request.getParameter("wardid");


			if(wardid==null) 
			{
				System.out.println("wardid null");
			}
			if(action==null)
			{
				System.out.println("action null");
			}

			if(action.equals("cancel"))
			{
				response.sendRedirect("ViewWardServlet");
			}

			if (action.equals("edit"))  //edit link
			{
				int id=Integer.parseInt(wardid);
				List<WardBean> wardlist=new ArrayList<WardBean>();
				wardlist=editWard(request,response,id);

				request.setAttribute("editward", wardlist);
				RequestDispatcher view1 = request.getRequestDispatcher("EditWard.jsp");
				view1.forward(request, response);

			}
			if (action.equals("detele"))  //edit link
			{
				int id=Integer.parseInt(wardid);

				int result=deleteWard(request,response,id);

				if(result==1)
				{



					PrintWriter out = response.getWriter(); 
					//  out.println("<script>alert('invalid login');</script>");
					//						  response.sendRedirect("Login.jsp");

					out.println("<script type=\"text/javascript\">");
					out.println("alert('deleted ward');");
					out.println("location='ViewWardServlet';");
					out.println("</script>");



				}

			}
			else if(action.equals("update"))  //update button
			{
				int id=Integer.parseInt(wardid);
				String TBWardname = request.getParameter("TBwardname");
				//List<WardBean> wardlist=new ArrayList<WardBean>();
				int result=updateWard(request,response,id,TBWardname);
				if(result==1)
				{ PrintWriter out = response.getWriter(); 
				out.println("<script type=\"text/javascript\">");
				out.println("alert('updated ward');");
				out.println("location='ViewWardServlet';");
				out.println("</script>");
				//response.sendRedirect("ViewWardServlet");

				}
				else
				{
					PrintWriter out = response.getWriter(); 
					out.println("<script type=\"text/javascript\">");
					out.println("alert('value already exists');");
					out.println("location='ViewWardServlet';");
					out.println("</script>");
				}


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	//	private void deleteWard(HttpServletRequest request, HttpServletResponse response) {
	//		// read ward id from jsp 
	//		
	//	}



	private int deleteWard(HttpServletRequest request, HttpServletResponse response, int id) throws SQLException {

		WardDao objWardDao=new WardDao();
		int result=objWardDao.deleteWard(id);
		return result;
	}

	private int updateWard(HttpServletRequest request, HttpServletResponse response, int id,String TBWardname) {

		WardBean objWardBean=new WardBean();
		WardDao objWardDao=new WardDao();

		objWardBean.setWardid(id);
		objWardBean.setWardName(TBWardname);
		int result=0;
		try {
			result = objWardDao.updateWard(objWardBean);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	private List<WardBean> editWard(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {   // edit method in get

		WardBean objWardBean=new WardBean();
		WardDao objWardDao=new WardDao();

		objWardBean.setWardid(id);

		List<WardBean> wardlist=new ArrayList<WardBean>();

		try {
			wardlist=objWardDao.editWard(objWardBean);

		}

		catch (SQLException e)
		{
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

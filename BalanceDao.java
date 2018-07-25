package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chruchmanagementsystem.bean.BalanceBean;

import com.churchmanagementsystem.ConnectionString.ConnectionString;


public class BalanceDao {
	Connection con = null;
	public int addEventDetails(String eventid, String tBExpectedincome, String tBdate) throws SQLException {

		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		try
		{
			
			String query="insert into BIS_BALANCETABLE(BALANCE_AMOUNT,EXPEXTED_INCOME,EVENTID,EVENTDATE) "
					+ "VALUES('"+""+"','"+tBExpectedincome+"',"+eventid+", TO_DATE('"+tBdate+"','DD/MM/YYYY'))";
			Statement st = con.createStatement(); 
			result=st.executeUpdate(query)  ;

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			con.close();
		}
		return result;


	}


	public String getExpectedIncomeForEvent(int balanceid) throws SQLException {

		con = ConnectionString.getConnection();

		String query="select EXPEXTED_INCOME from BIS_BALANCETABLE  where balanceid="+balanceid;
		String expectedincome=null;
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
			expectedincome = rs.getString("EXPEXTED_INCOME");
			System.out.println(expectedincome);
		}
		rs.close();
		con.close();

		return expectedincome;

	}


	public String getExpectedIncomeForEvent(int eventid, String datesearch1, String datesearch2) throws SQLException {
		con = ConnectionString.getConnection();

		String query="select EXPEXTED_INCOME from BIS_BALANCETABLE  where EVENTID="+eventid+" and EVENTDATE  between '"+datesearch1+"' and '"+datesearch2+"'";

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String expectedincome = rs.getString("EXPEXTED_INCOME");
		System.out.println(expectedincome);
		rs.close();
		con.close();

		return expectedincome;
	}


	public List<BalanceBean> getEventDetails(int eventid) throws SQLException {
		List<BalanceBean> balancelist=new ArrayList<BalanceBean>();
		Statement st =null;
		ResultSet rs=null;
		con = ConnectionString.getConnection();

		if(eventid==3)
		{
			String query="select e.EVENT_NAME,i.AMOUNT,i.DATERECEIVED from bis_incometable i,bis_eventtable e where i.EVENT_ID=e.EVENT_ID and e.EVENT_ID="+eventid;
			st = con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				BalanceBean objBalanceBean=new BalanceBean();
				objBalanceBean.setEventname(rs.getString("EVENT_NAME"));
				objBalanceBean.setEventincome(rs.getString("AMOUNT"));
				objBalanceBean.setEventdate(rs.getString("DATERECEIVED"));

				System.out.println(rs.getString("EVENT_NAME"));
				System.out.println(rs.getString("AMOUNT"));
				System.out.println(rs.getString("DATERECEIVED"));
				balancelist.add(objBalanceBean);
			}
			rs.close();
			con.close();

			return balancelist;
		}
		else
		{



			String query="select e.EVENT_NAME, b.EXPEXTED_INCOME,b.EVENTDATE from bis_balancetable b,bis_eventtable e where e.EVENT_ID=b.EVENTID and b.EVENTID="+eventid;
			st = con.createStatement();
			rs=st.executeQuery(query);
			while(rs.next())
			{
				BalanceBean objBalanceBean=new BalanceBean();
				objBalanceBean.setEventname(rs.getString("EVENT_NAME"));
				objBalanceBean.setEventincome(rs.getString("EXPEXTED_INCOME"));
				objBalanceBean.setEventdate(rs.getString("EVENTDATE"));
				System.out.println(rs.getString("EVENT_NAME"));
				System.out.println(rs.getString("EXPEXTED_INCOME"));
				System.out.println(rs.getString("EVENTDATE"));
				balancelist.add(objBalanceBean);

			}
			rs.close();
			con.close();

			return balancelist;
		}
	}


	public List<BalanceBean> getEventNameforIncome(int eventid) throws SQLException {
		con = ConnectionString.getConnection();
		List<BalanceBean> eventList=new ArrayList<BalanceBean>();
		try
		{
			String query="select e.EVENT_NAME,b.eventdate,b.balanceid from bis_balancetable b,bis_eventtable e where b.eventid=e.event_id and b.eventid="+eventid;
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				BalanceBean objBalanceBean=new BalanceBean();
				System.out.println(rs.getString("EVENT_NAME"));
				objBalanceBean.setBalanceid(rs.getInt("balanceid"));
				objBalanceBean.setEventdate(rs.getString("eventdate"));
				objBalanceBean.setEventname(rs.getString("EVENT_NAME"));

				eventList.add(objBalanceBean);

			}
			rs.close();
			st.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{

			con.close();
		}
		return eventList;
	}


	public List<BalanceBean> getEventNameforIncome() throws SQLException {
		con = ConnectionString.getConnection();
		List<BalanceBean> eventList=new ArrayList<BalanceBean>();
		try
		{
			String query="select e.EVENT_NAME,b.eventdate,b.balanceid from bis_balancetable b,bis_eventtable e where b.eventid=e.event_id";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				BalanceBean objBalanceBean=new BalanceBean();
				System.out.println(rs.getString("EVENT_NAME"));
				objBalanceBean.setBalanceid(rs.getInt("balanceid"));
				objBalanceBean.setEventdate(rs.getString("eventdate"));
				objBalanceBean.setEventname(rs.getString("EVENT_NAME"));

				eventList.add(objBalanceBean);

			}
			rs.close();
			st.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{

			con.close();
		}
		return eventList;
	}

}

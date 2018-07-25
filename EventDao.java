package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chruchmanagementsystem.bean.EventBean;
import com.churchmanagementsystem.ConnectionString.ConnectionString;

public class EventDao {

	Connection con = null;

	public int addEvent(EventBean objEventBean) throws SQLException {    //INSERT WARD NAMES
		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		
		String querychk="select * from BIS_EVENTTABLE";
		Statement stchk = con.createStatement(); 
		ResultSet rschk=stchk.executeQuery(querychk) ;
		int chk=0;
		
			while(rschk.next())
			{

				if(objEventBean.getEventName().equals(rschk.getString("EVENT_NAME")))
				{
					chk=1;
					break;
				}
				else
				{
					chk=0;
				}
			}
			
			if(chk==0)
			{

				try
				{
					String query="insert into BIS_EVENTTABLE(EVENT_NAME) VALUES('"+objEventBean.getEventName()+"')";
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
			}
			else
			{
				result=0;
			}
		
		return result;
	}

	public List<EventBean> getEventName() throws SQLException {
		con = ConnectionString.getConnection();
		List<EventBean> eventList=new ArrayList<EventBean>();
		try
		{
			String query="select * from BIS_EVENTTABLE";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				EventBean objEventBean=new EventBean();
				System.out.println(rs.getString("EVENT_NAME"));
				objEventBean.setEventid(rs.getInt("EVENT_ID"));

				objEventBean.setEventName(rs.getString("EVENT_NAME"));

				eventList.add(objEventBean);

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

	public List<EventBean> editEventName(int eventid) throws SQLException {

		con = ConnectionString.getConnection();
		List<EventBean> eventList=new ArrayList<EventBean>();
		try
		{
			String query="select * from BIS_EVENTTABLE where EVENT_ID="+eventid;
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				EventBean objEventBean=new EventBean();

				objEventBean.setEventid(rs.getInt("EVENT_ID"));

				objEventBean.setEventName(rs.getString("EVENT_NAME"));

				eventList.add(objEventBean);

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

	public int updateEvent(EventBean objEventBean) throws SQLException {
		int result=0;

		con = ConnectionString.getConnection();
		try
		{
			String query="update BIS_EVENTTABLE set EVENT_NAME='"+objEventBean.getEventName()+"' where EVENT_ID="+objEventBean.getEventid();
			Statement st = con.createStatement(); 
			result=st.executeUpdate(query);
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

	public int deleteEvent(int eventid) throws SQLException {
		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		try
		{
			String query="delete from  BIS_EVENTTABLE  where EVENT_ID="+eventid;
			Statement st = con.createStatement(); 
			result=st.executeUpdate(query);
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

	public List<EventBean> getEventNameaandDate(int eventid) throws SQLException {
		con = ConnectionString.getConnection();
		List<EventBean> eventList=new ArrayList<EventBean>();
		Statement st=null;
		ResultSet rs=null;
		try
		{
			if(eventid==3)
			{
				String querysunday="select e.EVENT_NAME,i.DATERECEIVED,e.EVENT_ID from bis_incometable  i,bis_eventtable e where e.EVENT_ID=i.EVENT_ID and e.EVENT_ID="+eventid;
				st=con.createStatement(); 
				rs=st.executeQuery(querysunday);
				while(rs.next())
				{
					EventBean objEventBean=new EventBean();
					objEventBean.setEventid(rs.getInt("EVENT_ID"));

					objEventBean.setEventName(rs.getString("EVENT_NAME"));

					objEventBean.setEventdate(rs.getString("DATERECEIVED"));
					eventList.add(objEventBean);
				}
				rs.close();
				st.close();
			}
			else
			{
				String query="select b.EVENTDATE,e.EVENT_NAME,e.event_id from bis_balancetable b,bis_eventtable e where b.EVENTID=e.EVENT_ID and e.event_id="+eventid;
				st = con.createStatement(); 
				rs=st.executeQuery(query) ;

				while(rs.next())
				{
					EventBean objEventBean=new EventBean();

					objEventBean.setEventid(rs.getInt("EVENT_ID"));

					objEventBean.setEventName(rs.getString("EVENT_NAME"));

					objEventBean.setEventdate(rs.getString("EVENTDATE"));
					eventList.add(objEventBean);

				}
				rs.close();
				st.close();
			}
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



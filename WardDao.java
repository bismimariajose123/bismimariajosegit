package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.ConnectionString.ConnectionString;

public class WardDao {

	Connection con = null;

	public int addWard(WardBean objAddWardBean) throws SQLException {    //INSERT WARD NAMES
		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		String querychk="select * from BIS_WARDTABLE";
		Statement stchk = con.createStatement(); 
		ResultSet rs=stchk.executeQuery(querychk) ;
		int chk=0;
		try
		{
			while(rs.next())
			{

				if(objAddWardBean.getWardName().equals(rs.getString("WARDNAME")))
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
				String query="insert into BIS_WARDTABLE(WARDNAME) VALUES('"+objAddWardBean.getWardName()+"')";
				Statement st = con.createStatement(); 
				result=st.executeUpdate(query)  ;
				System.out.println("result"+result);
			}

			else
			{
				result=0;
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


		return result;
	}

	public List<WardBean> viewWard() throws SQLException {   //VIEW WARD NAMES
		con = ConnectionString.getConnection();
		List<WardBean> wardList=new ArrayList<WardBean>();
		try
		{
			String query="select * from BIS_WARDTABLE";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				WardBean objAddWardBean=new WardBean();
				System.out.println(rs.getString("WARDNAME"));
				objAddWardBean.setWardid(rs.getInt("WARD_ID"));

				objAddWardBean.setWardName(rs.getString("WARDNAME"));

				wardList.add(objAddWardBean);

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
		return wardList;
	}

	public List<WardBean> editWard(WardBean objWardBean) throws SQLException {    //edit ward name
		con = ConnectionString.getConnection();
		List<WardBean> wardList=new ArrayList<WardBean>();




		String query="select * from  BIS_WARDTABLE where WARD_ID="+objWardBean.getWardid();
		Statement st = con.createStatement(); 
		ResultSet rs=st.executeQuery(query) ;
		try
		{
			while(rs.next())
			{
				WardBean objAddWardBean=new WardBean();
				//	System.out.println(rs.getString("WARDNAME"));
				objAddWardBean.setWardid(rs.getInt("WARD_ID"));

				objAddWardBean.setWardName(rs.getString("WARDNAME"));

				wardList.add(objAddWardBean);

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
		return wardList;


	}

	public int updateWard(WardBean objWardBean) throws SQLException {

		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		String querychk="select * from BIS_WARDTABLE";
		Statement stchk = con.createStatement(); 
		ResultSet rschk=stchk.executeQuery(querychk) ;
		int chk=0;
		
			while(rschk.next())
			{

				if(objWardBean.getWardName().equals(rschk.getString("WARDNAME")))
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
					String query="update BIS_WARDTABLE set WARDNAME='"+objWardBean.getWardName()+"' where WARD_ID="+objWardBean.getWardid();
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
			}
			
			else
			{
				result=0;
			}
			return result;

		

		}

		public int deleteWard(int id) throws SQLException {
			int result=0;
			// TODO Auto-generated method stub


			con = ConnectionString.getConnection();
			try
			{
				String query="delete from  BIS_WARDTABLE  where WARD_ID="+id;
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


	}



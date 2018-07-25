package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chruchmanagementsystem.bean.FamilyBean;
import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.ConnectionString.ConnectionString;


public class FamilyDao {

	Connection con = null;
	public int addFamily(WardBean objWardBean, String familyname) throws SQLException {

		int result=0;
		// TODO Auto-generated method stub

		con = ConnectionString.getConnection();
		String querychk="select * from bis_familytable";
		Statement stchk = con.createStatement(); 
		ResultSet rschk=stchk.executeQuery(querychk) ;
		int chk=0;
		
			while(rschk.next())
			{

				if(familyname.equals(rschk.getString("FAMILYNAME")))
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
					String query="insert into bis_familytable(FAMILYNAME,WARD_ID) VALUES('"+familyname+"',"+objWardBean.getWardid()+")";
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

	public List<FamilyMemberDetailsBean> viewFamily() throws SQLException {
		con = ConnectionString.getConnection();
		List<FamilyMemberDetailsBean> list= new ArrayList<FamilyMemberDetailsBean>();

		try
		{
			String query="select f.FAMILY_ID,f.FAMILYNAME,w.WARDNAME,w.WARD_ID from bis_wardtable w,bis_familytable f where f.ward_id=w.ward_id";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyWardBean=new FamilyMemberDetailsBean();
				objFamilyWardBean.setWardName(rs.getString("WARDNAME"));
				objFamilyWardBean.setWardid(rs.getInt("WARD_ID"));
				objFamilyWardBean.setFamilyId(rs.getInt("FAMILY_ID"));
				objFamilyWardBean.setFamilyName(rs.getString("FAMILYNAME"));
				list.add(objFamilyWardBean);

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
		return list;
	}

	public List<FamilyBean> viewFamilyName() throws SQLException {

		con = ConnectionString.getConnection();
		List<FamilyBean> familylist=new ArrayList<FamilyBean>();
		try
		{
			String query="select * from bis_familytable";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyBean objFamilyBean=new FamilyBean();
				//	System.out.println(rs.getString("FAMILYNAME"));
				objFamilyBean.setFamilyId(rs.getInt("FAMILY_ID"));
				objFamilyBean.setFamilyName(rs.getString("FAMILYNAME"));


				familylist.add(objFamilyBean);

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
		return familylist;
	}

	public int insertFamilyMemberDetails(FamilyMemberDetailsBean objFamilyMemberDetailsBean) throws SQLException {
		int result=0;
		// TODO Auto-generated method stub
		con = ConnectionString.getConnection();
		try
		{
			String query="insert into BIS_MEMBERTABLE(FAMILYID,MEMBERNAME,CONTACT,GENDER,WARD_ID,EMAIL) "
					+ "VALUES("+objFamilyMemberDetailsBean.getFamilyId()+",'"+objFamilyMemberDetailsBean.getOfficialName()+"','"+objFamilyMemberDetailsBean.getContact()+"','"+objFamilyMemberDetailsBean.getGender()+"',"+objFamilyMemberDetailsBean.getWardid()+",'"+objFamilyMemberDetailsBean.getEmail()+"')";
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

	public List<FamilyBean> getFamilyNameList(int id) throws SQLException {
		con = ConnectionString.getConnection();
		List<FamilyBean> familylist=new ArrayList<FamilyBean>();
		try
		{
			String query="select family_id,familyname from bis_familytable  where family_id in(select familyid from bis_membertable where ward_id="+id+")";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyBean objFamilyBean=new FamilyBean();
				System.out.println(rs.getString("FAMILYNAME"));
				objFamilyBean.setFamilyId(rs.getInt("FAMILY_ID"));
				objFamilyBean.setFamilyName(rs.getString("FAMILYNAME"));
				familylist.add(objFamilyBean);

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
		return familylist;
	}

	public List<FamilyMemberDetailsBean> viewFamilybySearch(String tBSearch) throws SQLException {

		con = ConnectionString.getConnection();
		List<FamilyMemberDetailsBean> list= new ArrayList<FamilyMemberDetailsBean>();

		try
		{
			String query="select f.FAMILY_ID,f.FAMILYNAME,w.WARDNAME,w.WARD_ID from bis_wardtable w,bis_familytable f where f.ward_id=w.ward_id and f.FAMILYNAME like '%"+tBSearch+"%'";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyWardBean=new FamilyMemberDetailsBean();
				objFamilyWardBean.setWardName(rs.getString("WARDNAME"));
				objFamilyWardBean.setWardid(rs.getInt("WARD_ID"));
				objFamilyWardBean.setFamilyId(rs.getInt("FAMILY_ID"));
				objFamilyWardBean.setFamilyName(rs.getString("FAMILYNAME"));
				list.add(objFamilyWardBean);

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
		return list;


	}

	public String getOfficialName(int id) throws SQLException {
		con = ConnectionString.getConnection();
		String  oficialname=null;
		try
		{
			String query="select MEMBERNAME from bis_membertable where MEMBER_ID="+id; 
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{

				oficialname=rs.getString("MEMBERNAME");

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
		return oficialname;
	}

}

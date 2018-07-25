package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.chruchmanagementsystem.bean.FamilyMemberDetailsBean;
import com.chruchmanagementsystem.bean.WardBean;
import com.churchmanagementsystem.ConnectionString.ConnectionString;

public class FamilyMemberDetailsDao {
	Connection con = null;
	public List<FamilyMemberDetailsBean> getMemberNameList(int id) throws SQLException {
		con = ConnectionString.getConnection();
		List<FamilyMemberDetailsBean> memberlist=new ArrayList<FamilyMemberDetailsBean>();
		try
		{
			String query="select MEMBER_ID,MEMBERNAME from bis_membertable where FAMILYID="+id;
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();

				objFamilyMemberDetailsBean.setMemberid(rs.getInt("MEMBER_ID"));
				objFamilyMemberDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));
				memberlist.add(objFamilyMemberDetailsBean);

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
		return memberlist;
	}

	public List<FamilyMemberDetailsBean> viewfamilyMember(int wardid, int familyid) throws SQLException {
		List<FamilyMemberDetailsBean> list=new  ArrayList<FamilyMemberDetailsBean>();

		Connection con = null;

		con = ConnectionString.getConnection();

		try
		{
			String query="select f.FAMILYNAME,m.membername,w.WARDNAME,m.contact,m.email,m.gender,m.member_id from bis_membertable m,bis_familytable f,bis_wardtable w where m.FAMILYID=f.FAMILY_ID and m.ward_id=w.WARD_ID and  m.ward_id="+wardid + "and f.FAMILY_ID="+familyid;

			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();

				objFamilyMemberDetailsBean.setMemberid(rs.getInt("MEMBER_ID"));

				objFamilyMemberDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));

				objFamilyMemberDetailsBean.setContact(rs.getString("contact"));
				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));

				objFamilyMemberDetailsBean.setGender(rs.getString("gender"));

				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));
				objFamilyMemberDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));

				objFamilyMemberDetailsBean.setWardName(rs.getString("WARDNAME"));

				list.add(objFamilyMemberDetailsBean);

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

	public List<FamilyMemberDetailsBean> editMemberList(int id) throws SQLException {
		Connection con = null;
		con = ConnectionString.getConnection();
		List<FamilyMemberDetailsBean> list=new ArrayList<FamilyMemberDetailsBean>();
		try
		{
			String query="select f.FAMILYNAME,m.membername,w.WARDNAME,m.contact,m.email,m.gender,m.member_id from bis_membertable m,bis_familytable f,bis_wardtable w where m.FAMILYID=f.FAMILY_ID and m.ward_id=w.WARD_ID and m.member_id="+id;
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();

				objFamilyMemberDetailsBean.setMemberid(rs.getInt("MEMBER_ID"));

				objFamilyMemberDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));

				objFamilyMemberDetailsBean.setContact(rs.getString("contact"));
				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));

				objFamilyMemberDetailsBean.setGender(rs.getString("gender"));

				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));
				objFamilyMemberDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));

				objFamilyMemberDetailsBean.setWardName(rs.getString("WARDNAME"));

				list.add(objFamilyMemberDetailsBean);


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

	public static int updateWard(FamilyMemberDetailsBean objFamilyMemberDetailsBean) throws SQLException {
		Connection con = null;
		int result=0;
		con = ConnectionString.getConnection();
		try
		{
			String query="update BIS_MEMBERTABLE set MEMBERNAME='"+objFamilyMemberDetailsBean.getOfficialName()+"', CONTACT='"+objFamilyMemberDetailsBean.getContact()+"', GENDER='"+objFamilyMemberDetailsBean.getGender()+"',EMAIL='"+objFamilyMemberDetailsBean.getEmail()+"' where MEMBER_ID="+objFamilyMemberDetailsBean.getMemberid();
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

	public List<FamilyMemberDetailsBean> viewfamilyMemberDetails(int wardid) throws SQLException {
		List<FamilyMemberDetailsBean> list=new  ArrayList<FamilyMemberDetailsBean>();

		Connection con = null;

		con = ConnectionString.getConnection();

		try
		{
			String query="select f.FAMILYNAME,m.membername,w.WARDNAME,m.contact,m.email,m.gender,m.member_id from bis_membertable m,bis_familytable f,bis_wardtable w where m.FAMILYID=f.FAMILY_ID and m.ward_id=w.WARD_ID and  m.ward_id="+wardid;

			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				FamilyMemberDetailsBean objFamilyMemberDetailsBean=new FamilyMemberDetailsBean();

				objFamilyMemberDetailsBean.setMemberid(rs.getInt("MEMBER_ID"));

				objFamilyMemberDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));

				objFamilyMemberDetailsBean.setContact(rs.getString("contact"));
				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));

				objFamilyMemberDetailsBean.setGender(rs.getString("gender"));

				objFamilyMemberDetailsBean.setEmail(rs.getString("email"));
				objFamilyMemberDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));

				objFamilyMemberDetailsBean.setWardName(rs.getString("WARDNAME"));

				list.add(objFamilyMemberDetailsBean);

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

}




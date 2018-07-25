package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.chruchmanagementsystem.bean.LoginBean;
import com.churchmanagementsystem.ConnectionString.ConnectionString;


public class LoginDao {
	Connection con = null;
	public String validateUserLogin(LoginBean objLoginBean) throws SQLException {
		String result="Invalid";
		// TODO Auto-generated method stub

		con = ConnectionString.getConnection();
		try
		{
			String query="select * from BIS_LOGIN_TABLE where USERNAME='"+objLoginBean.getUsername()+"' and PASSWORD='"+objLoginBean.getPassword()+"'";
			PreparedStatement ps=con.prepareStatement(query) ;
			ResultSet rs=ps.executeQuery();  

			while(rs.next())
			{

				String usertype=rs.getString("USERTYPE");

				if(usertype.equals("Admin"))
				{
					result="Admin";
				}
				else if(usertype.equals("Priest"))
				{

					result="Priest";
				}
				else if(usertype.equals("Accountant"))
				{
					result="Accountant";
				}
				else
				{
					result="Invalid";
				}
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

	public int addUser(LoginBean objLoginBean) throws SQLException {

		int result=0;
		// TODO Auto-generated method stub

		con = ConnectionString.getConnection();
		try
		{
			String query="insert into bis_login_table(USERNAME,PASSWORD,USERTYPE) VALUES('"+objLoginBean.getUsername()+"','"+objLoginBean.getPassword()+"','"+objLoginBean.getUsertype()+"')";
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
}



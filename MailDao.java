package com.churchmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.churchmanagementsystem.ConnectionString.ConnectionString;

public class MailDao {
	Connection con = null;
	public String getMailidofuser(int income_id) throws SQLException {

		String mailid = null;
		con = ConnectionString.getConnection();

		try
		{
			String query="select EMAIL from bis_membertable m,bis_incometable i where i.MEMBER_ID=m.MEMBER_ID and i.INCOME_ID="+income_id;
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				mailid=rs.getString("EMAIL");

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

			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mailid;
	}

}

package com.churchmanagementsystem.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet.FontAttribute;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.chruchmanagementsystem.bean.IncomeBean;
import com.chruchmanagementsystem.bean.IncomeDetailsBean;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.churchmanagementsystem.ConnectionString.ConnectionString;

public class IncomeDao {
	Connection con = null;

	public int addIncome(IncomeBean objIncomeBean, int balanceid) throws SQLException {

		int result=0;
		// TODO Auto-generated method stub


		con = ConnectionString.getConnection();
		try
		{
			String query="insert into bis_incometable(MEMBER_ID,AMOUNT,BALANCEID,DATERECEIVED) VALUES("+objIncomeBean.getMEMBER_ID()+",'"+objIncomeBean.getAMOUNT()+"',"+balanceid+",TO_DATE('"+objIncomeBean.getDATERECEIVED()+"','DD/MM/YYYY'))";
			System.out.println(objIncomeBean.getDATERECEIVED());
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
	public List<IncomeDetailsBean> getIncomeListByEventid(int eventid,String datwofevent) throws SQLException {
		con = ConnectionString.getConnection();
		List<IncomeDetailsBean> incomeList=new ArrayList<IncomeDetailsBean>();
		Statement st=null;
		ResultSet rs=null;
		IncomeDetailsBean objIncomeDetailsBean=null;
		try
		{


			if(eventid==3)
			{
				String querysunday="select e.EVENT_NAME,i.DATERECEIVED,i.AMOUNT,e.EVENT_ID from bis_incometable  i,bis_eventtable e where e.EVENT_ID=i.EVENT_ID and e.EVENT_ID="+eventid;
				st = con.createStatement();
				rs=st.executeQuery(querysunday) ;
				while(rs.next())
				{
					objIncomeDetailsBean=new IncomeDetailsBean();
					objIncomeDetailsBean.setAmount(rs.getString("AMOUNT"));
					objIncomeDetailsBean.setAmountReceivedDate(rs.getString("DATERECEIVED"));

					incomeList.add(objIncomeDetailsBean);
				}

				rs.close();
				st.close();
			}
			else
			{


				String query="select m.MEMBERNAME,f.FAMILYNAME,w.WARDNAME,i.AMOUNT,i.DATERECEIVED,i.INCOME_ID from bis_membertable m join bis_familytable f on m.FAMILYID = f.FAMILY_ID join bis_wardtable w on m.ward_id=w.WARD_ID join bis_incometable i on m.member_id=i.MEMBER_ID  join bis_eventtable e on i.EVENT_ID=e.EVENT_ID join BIS_BALANCETABLE b on e.EVENT_ID=b.EVENTID where b.EVENTID="+eventid+" and b.EVENTDATE='"+datwofevent+"'";

				st = con.createStatement();

				rs=st.executeQuery(query) ;

				while(rs.next())
				{
					objIncomeDetailsBean=new IncomeDetailsBean();

					objIncomeDetailsBean.setAmount(rs.getString("AMOUNT"));
					objIncomeDetailsBean.setAmountReceivedDate(rs.getString("DATERECEIVED"));
					objIncomeDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));
					objIncomeDetailsBean.setIncomeId(rs.getInt("INCOME_ID"));
					objIncomeDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));
					objIncomeDetailsBean.setWardName(rs.getString("WARDNAME"));

					incomeList.add(objIncomeDetailsBean);

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

		return incomeList;
	}
	public List<IncomeDetailsBean> getIncomeListByDate(String datesearch, String datesearch2, int eventid) throws SQLException {

		con = ConnectionString.getConnection();
		List<IncomeDetailsBean> incomeList=new ArrayList<IncomeDetailsBean>();
		try
		{
			String query="select m.MEMBERNAME,f.FAMILYNAME,w.WARDNAME,i.AMOUNT,i.DATERECEIVED,i.INCOME_ID "
					+ "from bis_membertable m join bis_familytable f on m.FAMILYID = f.FAMILY_ID "
					+ "join bis_wardtable w on m.ward_id=w.WARD_ID  "
					+ "join bis_incometable i on m.member_id=i.MEMBER_ID  "
					+ "join bis_eventtable e on i.EVENT_ID=e.EVENT_ID where e.EVENT_ID="+eventid+"and i.DATERECEIVED between '"+datesearch+"' and '"+datesearch2+"'";
			Statement st = con.createStatement(); 
			ResultSet rs=st.executeQuery(query) ;

			while(rs.next())
			{
				IncomeDetailsBean objIncomeDetailsBean=new IncomeDetailsBean();
				System.out.println(rs.getString("MEMBERNAME"));
				objIncomeDetailsBean.setAmount(rs.getString("AMOUNT"));
				objIncomeDetailsBean.setAmountReceivedDate(rs.getString("DATERECEIVED"));
				objIncomeDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));
				objIncomeDetailsBean.setIncomeId(rs.getInt("INCOME_ID"));
				objIncomeDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));
				objIncomeDetailsBean.setWardName(rs.getString("WARDNAME"));

				incomeList.add(objIncomeDetailsBean);

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
		return incomeList;
	}
	public  String  getTotalAmount(int eventid,String eventdate) throws SQLException {
		con = ConnectionString.getConnection();
		String totalincome=null;
		Statement st=null;
		ResultSet rs=null;
		if(eventid==3)
		{
			String querysunday="select sum(AMOUNT) from bis_incometable where event_id="+eventid;
			st = con.createStatement();
			rs=st.executeQuery(querysunday);
			rs.next();
			totalincome = rs.getString(1);
			System.out.println(totalincome);
			rs.close();
			con.close();
		}
		else
		{

			String query="select sum(i.AMOUNT) as total from bis_incometable i,BIS_BALANCETABLE b where i.event_id=b.eventid and (b.eventid="+eventid+" and b.eventdate='"+eventdate+"')";
			st = con.createStatement();
			rs=st.executeQuery(query);
			rs.next();
			totalincome = rs.getString(1);
			System.out.println(totalincome);
			rs.close();
			con.close();
		}
		return totalincome;


	}
	public String getEventname(int eventid) throws SQLException {

		con = ConnectionString.getConnection();

		String query="select EVENT_NAME from BIS_EVENTTABLE where EVENT_ID="+eventid;

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String eventname = rs.getString("EVENT_NAME");


		System.out.println(eventname);


		rs.close();
		con.close();

		return eventname;


	}

	public String getWardName(int wardid) throws SQLException {

		con = ConnectionString.getConnection();

		String query="select WARDNAME from BIS_WARDTABLE where WARD_ID="+wardid;

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String wardname = rs.getString("WARDNAME");


		System.out.println(wardname);


		rs.close();
		con.close();

		return wardname;
	}

	public String getFamilyName(int familyid) throws SQLException {
		con = ConnectionString.getConnection();

		String query="select FAMILYNAME from bis_familytable where FAMILY_ID="+familyid;

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String familyname = rs.getString("FAMILYNAME");


		System.out.println(familyname);


		rs.close();
		con.close();

		return familyname;
	}
	public List<IncomeDetailsBean> getTotalAmountByDate(String datesearch1, String datesearch2, int eventid) throws SQLException {
		con = ConnectionString.getConnection();
		List<IncomeDetailsBean> totalamount=new ArrayList<IncomeDetailsBean>();
		String query="select sum(AMOUNT) as total from bis_incometable where event_id="+eventid+" and DATERECEIVED between '"+datesearch1+"' and '"+datesearch2+"'";

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String sum = rs.getString(1);
		IncomeDetailsBean objIncomeDetailsBean=new IncomeDetailsBean();

		System.out.println(sum);
		objIncomeDetailsBean.setTotalincome(sum);
		totalamount.add(objIncomeDetailsBean);
		rs.close();
		con.close();

		return totalamount;
	}

	public List<IncomeDetailsBean> getIncomeListByBalanceid(int balanceid) throws SQLException {
		con = ConnectionString.getConnection();
		List<IncomeDetailsBean> incomeList=new ArrayList<IncomeDetailsBean>();
		Statement st=null;
		ResultSet rs=null;
		IncomeDetailsBean objIncomeDetailsBean=null;
		try
		{
			String query="select m.MEMBERNAME,f.FAMILYNAME,w.WARDNAME,i.AMOUNT,TO_CHAR(i.DATERECEIVED,'DD-MM-YYYY')as DATERECEIVED,i.INCOME_ID from bis_membertable m join bis_familytable f on m.FAMILYID = f.FAMILY_ID join bis_wardtable w on m.ward_id=w.WARD_ID join bis_incometable i on m.member_id=i.MEMBER_ID join BIS_BALANCETABLE ba on ba.BALANCEID=i.BALANCEID and ba.BALANCEID="+balanceid;
				st = con.createStatement();

			rs=st.executeQuery(query) ;

			while(rs.next())
			{
				objIncomeDetailsBean=new IncomeDetailsBean();

				objIncomeDetailsBean.setAmount(rs.getString("AMOUNT"));
				System.out.println(rs.getString("DATERECEIVED"));
				objIncomeDetailsBean.setAmountReceivedDate(rs.getString("DATERECEIVED"));
				objIncomeDetailsBean.setFamilyName(rs.getString("FAMILYNAME"));
				objIncomeDetailsBean.setIncomeId(rs.getInt("INCOME_ID"));
				objIncomeDetailsBean.setOfficialName(rs.getString("MEMBERNAME"));
				objIncomeDetailsBean.setWardName(rs.getString("WARDNAME"));

				incomeList.add(objIncomeDetailsBean);

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

		return incomeList;
	}

	public String getTotalAmount(int balanceid) throws SQLException {
		con = ConnectionString.getConnection();
		String totalincome=null;
		Statement st=null;
		ResultSet rs=null;
		String query="select sum(i.AMOUNT) as total from bis_incometable i,BIS_BALANCETABLE b where b.BALANCEID=i.BALANCEID and  b.BALANCEID="+balanceid;

		st = con.createStatement();
		rs=st.executeQuery(query);
		rs.next();
		totalincome = rs.getString(1);
		System.out.println(totalincome);
		rs.close();
		con.close();

		return totalincome;

	}
	public String getEventnamefromBalanceid(int balanceid) throws SQLException {
		con = ConnectionString.getConnection();

		String query="select e.EVENT_NAME from bis_balancetable b,bis_eventtable e where b.balanceid="+balanceid+" and e.event_id=b.eventid";

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String eventname = rs.getString("EVENT_NAME");


		System.out.println(eventname);


		rs.close();
		con.close();

		return eventname;
	}
	public void downloadreport(String balanceid) throws SQLException, FileNotFoundException, DocumentException {
		con = ConnectionString.getConnection();
		Document document = new Document(PageSize.A4, 50, 40, 50, 40);


		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("U:\\incomereport.pdf"));
		document.open();
		Paragraph para1 = new Paragraph("Income Report",FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(60, 40, 40, 100)));
		
		para1.setAlignment(Element.ALIGN_CENTER);
		document.add(para1);
		PdfPTable t = new PdfPTable(5);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);
		Font font1 = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.WHITE);

		PdfPCell c1 = new PdfPCell(new Phrase("Member Name",font1));
		c1.setBackgroundColor(BaseColor.BLACK);
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Family Name",font1));	
		c2.setBackgroundColor(BaseColor.BLACK);
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Ward Name",font1));	
		c3.setBackgroundColor(BaseColor.BLACK);
		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Amount",font1));	
		c4.setBackgroundColor(BaseColor.BLACK);
		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Date",font1));	
		c5.setBackgroundColor(BaseColor.BLACK);
		t.addCell(c5);	


		Statement st=null;
		ResultSet rs=null;
		try
		{
			String query="select m.MEMBERNAME,f.FAMILYNAME,w.WARDNAME,i.AMOUNT,i.DATERECEIVED,i.INCOME_ID from bis_membertable m join bis_familytable f on m.FAMILYID = f.FAMILY_ID join bis_wardtable w on m.ward_id=w.WARD_ID join bis_incometable i on m.member_id=i.MEMBER_ID join BIS_BALANCETABLE ba on ba.BALANCEID=i.BALANCEID and ba.BALANCEID="+balanceid;

			st = con.createStatement();

			rs=st.executeQuery(query) ;

			while(rs.next())
			{


				t.addCell(rs.getString("MEMBERNAME"));

				t.addCell(rs.getString("FAMILYNAME"));

				t.addCell(rs.getString("WARDNAME"));

				t.addCell(rs.getString("AMOUNT"));

				t.addCell(rs.getString("DATERECEIVED"));



			}
			document.add(t);
			document.close();
			System.out.println("\tPDF download complete");
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


	}



}


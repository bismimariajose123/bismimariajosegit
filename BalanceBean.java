package com.chruchmanagementsystem.bean;

public class BalanceBean {
	private int balanceid;
	private String balanceamount;
	private String eventincome;
	private int eventid;
	private String eventdate;
	private String eventname;
	
	public String getBALANCE_AMOUNT() {
		return balanceamount;
	}
	public void setBALANCE_AMOUNT(String bALANCE_AMOUNT) {
		balanceamount = bALANCE_AMOUNT;
	}
	
	public int getEVENTID() {
		return eventid;
	}
	public void setEVENTID(int eVENTID) {
		eventid = eVENTID;
	}

	
	public String getEventdate() {
		return eventdate;
	}
	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}
	public String getEventincome() {
		return eventincome;
	}
	public void setEventincome(String eventincome) {
		this.eventincome = eventincome;
	}
	public int getBalanceid() {
		return balanceid;
	}
	public void setBalanceid(int balanceid) {
		this.balanceid = balanceid;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

}

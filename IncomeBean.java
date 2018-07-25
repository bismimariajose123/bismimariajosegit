package com.chruchmanagementsystem.bean;

public class IncomeBean {

	private int incomeId;
	private int eventId;
	private int fanilyId;
	private int memberId;
	private String amount;
	private int wardId;
	private String amountReceivedDate;
	public int getINCOME_ID() {
		return incomeId;
	}
	public void setINCOME_ID(int iNCOME_ID) {
		incomeId = iNCOME_ID;
	}
	public int getEVENT_ID() {
		return eventId;
	}
	public void setEVENT_ID(int eVENT_ID) {
		eventId = eVENT_ID;
	}
	public String getAMOUNT() {
		return amount;
	}
	public void setAMOUNT(String aMOUNT) {
		amount = aMOUNT;
	}
	public int getFAMILY_ID() {
		return fanilyId;
	}
	public void setFAMILY_ID(int fAMILY_ID) {
		fanilyId = fAMILY_ID;
	}
	public int getMEMBER_ID() {
		return memberId;
	}
	public void setMEMBER_ID(int mEMBER_ID) {
		memberId = mEMBER_ID;
	}
	public String getDATERECEIVED() {
		return amountReceivedDate;
	}
	public void setDATERECEIVED(String dATERECEIVED) {
		amountReceivedDate = dATERECEIVED;
	}
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	
}

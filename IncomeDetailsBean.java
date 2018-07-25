package com.chruchmanagementsystem.bean;

public class IncomeDetailsBean {
	private String amount;
	private String amountReceivedDate;
	private String familyName;
	private String wardName;
	private String officialName;
	private int incomeId;
	private String totalincome;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getAmountReceivedDate() {
		return amountReceivedDate;
	}
	public void setAmountReceivedDate(String amountReceivedDate) {
		this.amountReceivedDate = amountReceivedDate;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	public String getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(String totalincome) {
		this.totalincome = totalincome;
	}
}

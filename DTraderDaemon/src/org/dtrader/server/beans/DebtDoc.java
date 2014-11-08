package org.dtrader.server.beans;

public class DebtDoc {
	/**
	 * Debt Document ID
	 */
	private int id;
	/**
	 * Client ID
	 */
	private int clientID;
	/**
	 * Pay form 0 - no data, 1 and 2
	 */
	private int payclass;
	/**
	 * Document type ID in table DocTypes
	 */
	private int docTypesID;
	/**
	 * Document's register number
	 */
	private String regNumber;
	/**
	 * Document date
	 */
	private String docDate;
	/**
	 * Pay type ID
	 */
	private int payTypeID;
	/**
	 * Pay type name
	 */
	private String payTypeSName;
	/**
	 * Responsible person name
	 */
	private String responsiblePerson;
	/**
	 * Document sum
	 */
	private double mainSum;
	/**
	 * Days for differing
	 */
	private int tillPayDays;
	/**
	 * discount %
	 */
	private int payPercent;
	/**
	 * End date for pay
	 */
	private String payTermDate;
	/**
	 * Debt sum
	 */
	private double debt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getPayclass() {
		return payclass;
	}
	public void setPayclass(int payclass) {
		this.payclass = payclass;
	}
	public int getDocTypesID() {
		return docTypesID;
	}
	public void setDocTypesID(int docTypesID) {
		this.docTypesID = docTypesID;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public int getPayTypeID() {
		return payTypeID;
	}
	public void setPayTypeID(int payTypeID) {
		this.payTypeID = payTypeID;
	}
	public String getPayTypeSName() {
		return payTypeSName;
	}
	public void setPayTypeSName(String payTypeSName) {
		this.payTypeSName = payTypeSName;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public double getMainSum() {
		return mainSum;
	}
	public void setMainSum(double mainSum) {
		this.mainSum = mainSum;
	}
	public int getTillPayDays() {
		return tillPayDays;
	}
	public void setTillPayDays(int tillPayDays) {
		this.tillPayDays = tillPayDays;
	}
	public int getPayPercent() {
		return payPercent;
	}
	public void setPayPercent(int payPercent) {
		this.payPercent = payPercent;
	}
	public String getPayTermDate() {
		return payTermDate;
	}
	public void setPayTermDate(String payTermDate) {
		this.payTermDate = payTermDate;
	}
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
}

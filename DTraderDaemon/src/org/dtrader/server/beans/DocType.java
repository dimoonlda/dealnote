package org.dtrader.server.beans;

public class DocType {
	private int id;
	private String sname;
	private int days;
	private int discount;
	private int payclass;
	private int reppattern;
	private int accincrnovat;
	private int accincrwithvat;
	private int paytypeid;
	private String discountFirst;
	private String vatOverSum;
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getPayclass() {
		return payclass;
	}
	public void setPayclass(int payclass) {
		this.payclass = payclass;
	}
	public int getReppattern() {
		return reppattern;
	}
	public void setReppattern(int reppattern) {
		this.reppattern = reppattern;
	}
	public int getAccincrnovat() {
		return accincrnovat;
	}
	public void setAccincrnovat(int accincrnovat) {
		this.accincrnovat = accincrnovat;
	}
	public int getAccincrwithvat() {
		return accincrwithvat;
	}
	public void setAccincrwithvat(int accincrwithvat) {
		this.accincrwithvat = accincrwithvat;
	}
	public int getPaytypeid() {
		return paytypeid;
	}
	public void setPaytypeid(int paytypeid) {
		this.paytypeid = paytypeid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiscountFirst() {
		return discountFirst;
	}
	public void setDiscountFirst(String discountFirst) {
		this.discountFirst = discountFirst;
	}
	public String getVatOverSum() {
		return vatOverSum;
	}
	public void setVatOverSum(String vatOverSum) {
		this.vatOverSum = vatOverSum;
	}
	
}

package org.dtrader.server.beans;

public class Goods {
	private int id;
	private int categoryID;
	private int sortPos;
	private String name;
	/**
	 * Count in package * 100
	 */
	private int caseSize;
	/**
	 * Price per one goods * 100
	 */
	private int price;
	/**
	 * Count of goods what is available on sales rep's warehouse * 100 
	 */
	private int available;
	private int assotrment;
	private int vatType;
	private String sertificat;
	private String ekka;
	private String dopCod;
	/**
	 * Goods full name
	 */
	private String fName;
	private int prioritetID;
	private int clientTypeMask;
	/**
	 * Goods class 1(weight, pre-packing etc.)
	 */
	private String class1;
	/**
	 * Weight per one goods
	 */
	private double weight;
	/**
	 * Priority color's code
	 */
	private String colorcode;
	
	private int measureid;
	
	private int outerid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getSortPos() {
		return sortPos;
	}
	public void setSortPos(int sortPos) {
		this.sortPos = sortPos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCaseSize() {
		return caseSize;
	}
	public void setCaseSize(int caseSize) {
		this.caseSize = caseSize;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getAssotrment() {
		return assotrment;
	}
	public void setAssotrment(int assotrment) {
		this.assotrment = assotrment;
	}
	public int getVatType() {
		return vatType;
	}
	public void setVatType(int vatType) {
		this.vatType = vatType;
	}
	public String getSertificat() {
		return sertificat;
	}
	public void setSertificat(String sertificat) {
		this.sertificat = sertificat;
	}
	public String getEkka() {
		return ekka;
	}
	public void setEkka(String ekka) {
		this.ekka = ekka;
	}
	public String getDopCod() {
		return dopCod;
	}
	public void setDopCod(String dopCod) {
		this.dopCod = dopCod;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public int getPrioritetID() {
		return prioritetID;
	}
	public void setPrioritetID(int prioritetID) {
		this.prioritetID = prioritetID;
	}
	public int getClientTypeMask() {
		return clientTypeMask;
	}
	public void setClientTypeMask(int clientTypeMask) {
		this.clientTypeMask = clientTypeMask;
	}	
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getColorcode() {
		return colorcode;
	}
	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}
	public int getMeasureId() {
		return measureid;
	}
	public void setMeasureId(int measureid) {
		this.measureid = measureid;
	}
	public int getOuterId() {
		return outerid;
	}
	public void setOuterId(int outerid) {
		this.outerid = outerid;
	}	
}

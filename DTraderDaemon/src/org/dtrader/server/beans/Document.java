package org.dtrader.server.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.NONE)
public class Document {
	
	@XmlElement(required=true)
	private int id;
	@XmlElement(required=true)
	private int clientID;
	@XmlElement(required=true)
	private String ordDate;
	@XmlElement(required=true)
	private String ordTime;
	@XmlElement(required=true)
	private double discount;
	@XmlElement(required=true)
	private double caseCount;
	@XmlElement(required=true)
	private double mainSumm;
	@XmlElement(required=true)
	private String descript;
	@XmlElement(required=true)
	private int ordState;
	@XmlElement(required=true)
	private int docTypeID;
	@XmlElement(required=true)
	private String docTypeName;
	@XmlElement(required=true)
	private String termDate;
	@XmlElement(required=true)
	private int ctPayType;
	@XmlElement(required=true)
	private double sumWithoutVat;
	@XmlElement(required=true)
	private double sumWithVat;
	@XmlElement(required=true)
	private String regNum;
	@XmlElement(required=true)
	private int preOrVan;
	@XmlElement(required=true)
	private int beePresLinkID;
	@XmlElement(required=true)
	private double longitude;
	@XmlElement(required=true)
	private double latitude;

	@XmlElementWrapper(name="docDetVector", required=false)	
	@XmlElement(required=false, name="documentDet")
	protected List<DocumentDet> docDetVector; 

	public Document() {
	}
	
	public Document(int id) {
		this.id = id;
	}
	
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
	public String getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getCaseCount() {
		return caseCount;
	}
	public void setCaseCount(double caseCount) {
		this.caseCount = caseCount;
	}
	public double getMainSumm() {
		return mainSumm;
	}
	public void setMainSumm(double mainSumm) {
		this.mainSumm = mainSumm;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getOrdState() {
		return ordState;
	}
	public void setOrdState(int ordState) {
		this.ordState = ordState;
	}
	public int getDocTypeID() {
		return docTypeID;
	}
	public void setDocTypeID(int docTypeID) {
		this.docTypeID = docTypeID;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	public String getTermDate() {
		return termDate;
	}
	public void setTermDate(String termDate) {
		this.termDate = termDate;
	}
	public int getCtPayType() {
		return ctPayType;
	}
	public void setCtPayType(int ctPayType) {
		this.ctPayType = ctPayType;
	}
	public double getSumWithoutVat() {
		return sumWithoutVat;
	}
	public void setSumWithoutVat(double sumWithoutVat) {
		this.sumWithoutVat = sumWithoutVat;
	}
	public double getSumWithVat() {
		return sumWithVat;
	}
	public void setSumWithVat(double sumWithVat) {
		this.sumWithVat = sumWithVat;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public int getPreOrVan() {
		return preOrVan;
	}
	public void setPreOrVan(int preOrVan) {
		this.preOrVan = preOrVan;
	}

	public String getOrdTime() {
		return ordTime;
	}

	public void setOrdTime(String ordTime) {
		this.ordTime = ordTime;
	}

	public int getBeePresLinkID() {
		return beePresLinkID;
	}

	public void setBeePresLinkID(int beePresLinkID) {
		this.beePresLinkID = beePresLinkID;
	}
	
	public List<DocumentDet> getDocDetVector() {
		return docDetVector;
	}

	public void setDocDetVector(List<DocumentDet> docDetVector) {
		this.docDetVector = docDetVector;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + getId() + "; ");
		sb.append("ordDate: " + getOrdDate() + "; ");
		sb.append("ordTime: " + getOrdTime() + "; ");
		sb.append("clientID: " + getClientID() + "; ");
		sb.append("discount: " + getDiscount() + "; ");
		sb.append("caseCount: " + getCaseCount() + "; ");
		sb.append("mainSumm: " + getMainSumm() + "; ");
		sb.append("descript: " + getDescript() + "; ");
		sb.append("docTypeID: " + getDocTypeID() + "; ");
		sb.append("termDate: " + getTermDate() + "; ");
		sb.append("ctPayType: " + getCtPayType() + "; ");
		sb.append("sumWithoutVat: " + getSumWithoutVat() + "; ");
		sb.append("sumWithVat: " + getSumWithVat() + "; ");
		sb.append("regNum: " + getRegNum() + "; ");
		sb.append("preOrVan: " + getPreOrVan() + "; ");
		sb.append("BeePresLinkID: " + getBeePresLinkID() + "; ");
		sb.append("longitude: " + getLongitude() + "; ");
		sb.append("latitude: " + getLatitude() + "; ");
		
		return sb.toString();
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}

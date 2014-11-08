package org.dtrader.server.beans;

public class DocumentDet {
	private int docID;
	private int goodsID;
	private double ship;
	private double priceWithoutVat;
	private double priceWithVat;
	
	public DocumentDet(int docID, int goodsID, double ship) {
		this(docID, goodsID, ship, 0.0, 0.0);
	}
	
	public DocumentDet(int docID, int goodsID, double ship, double priceWithoutVat, double priceWithVat) {
		this.docID = docID;
		this.goodsID = goodsID;
		this.ship = ship;
		this.priceWithoutVat = priceWithoutVat;
		this.priceWithVat = priceWithVat;
	}
	
	public DocumentDet(){
		
	}
	
	public int getDocID() {
		return docID;
	}
	public void setDocID(int docID) {
		this.docID = docID;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public double getShip() {
		return ship;
	}
	public void setShip(double ship) {
		this.ship = ship;
	}
	public double getPriceWithoutVat() {
		return priceWithoutVat;
	}
	public void setPriceWithoutVat(double priceWithoutVat) {
		this.priceWithoutVat = priceWithoutVat;
	}
	public double getPriceWithVat() {
		return priceWithVat;
	}
	public void setPriceWithVat(double priceWithVat) {
		this.priceWithVat = priceWithVat;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("goodsID: " + getGoodsID() + "; ");
		sb.append("ship: " + getShip() + "; ");
		sb.append("priceWithoutVat: " + getPriceWithoutVat() + "; ");
		sb.append("priceWithVat: " + getPriceWithVat() + "; ");
		return sb.toString();
	}
	
}

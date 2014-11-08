package org.dtrader.server.beans;

public class TradeAgent {
	
	private int id;
	private String name;
	
	public TradeAgent(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("id: " + getId() + "; ");
		sb.append("name: " + getName() + "; ");
		return sb.toString();
	}
}

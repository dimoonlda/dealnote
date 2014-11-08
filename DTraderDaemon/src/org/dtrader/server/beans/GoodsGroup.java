package org.dtrader.server.beans;

public class GoodsGroup {
	private int id;
	private int parentid;
	private String name;
	private String groupidset;
	
	public int getParentID() {
		return parentid;
	}
	public void setParentID(int parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getGroupidset() {
		return groupidset;
	}
	public void setGroupidset(String groupidset) {
		this.groupidset = groupidset;
	}
		
}

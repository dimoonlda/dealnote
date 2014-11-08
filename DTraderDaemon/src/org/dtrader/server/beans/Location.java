package org.dtrader.server.beans;

public class Location{
	private double longitude;
	private double latitude;
	private String clock;
	private String provider;
	private int accuracy;
	private int searchtime;
	private int savestate;
	private int checkins;
	
	public Location(){
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
	public String getClock() {
		return clock;
	}
	public void setClock(String clock) {
		this.clock = clock;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public int getSearchtime() {
		return searchtime;
	}
	public void setSearchtime(int searchtime) {
		this.searchtime = searchtime;
	}
	public int getSavestate() {
		return savestate;
	}
	public void setSavestate(int savestate) {
		this.savestate = savestate;
	}
	public int getCheckins() {
		return checkins;
	}
	public void setCheckins(int checkins) {
		this.checkins = checkins;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("longitude: " + getLongitude() + "; ");
		sb.append("latitude: " + getLatitude() + "; ");
		sb.append("clock: " + getClock() + "; ");
		sb.append("provider: " + getProvider() + "; ");
		sb.append("accuracy: " + getAccuracy() + "; ");
		sb.append("searchtime: " + getSearchtime() + "; ");
		sb.append("savestate: " + getSavestate() + "; ");
		sb.append("checkins: " + getCheckins() + "; ");
		return sb.toString();
	}
	
}

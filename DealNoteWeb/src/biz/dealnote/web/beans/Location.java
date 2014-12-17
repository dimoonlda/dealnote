package biz.dealnote.web.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Location {
	public static final String SAVESTATE_OK_BY_STR = "Успех";
	public static final String SAVESTATE_GPS_OFF_BY_STR = "GPS отключен";
	public static final String SAVESTATE_SEARCH_TIME_OUT_BY_STR = "Время поиска вышло";
	public static final String SAVESTATE_DEVICE_ON_BY_STR = "Устройство включено";
	public static final String SAVESTATE_DEVICE_OFF_BY_STR = "Устройство выключено";
	public static final String SAVESTATE_NONE_BY_STR = "Неизвестно";

	public static final int SAVESTATE_OK = 1;
	public static final int SAVESTATE_GPS_OFF = 2;
	public static final int SAVESTATE_SEARCH_TIME_OUT = 3;
	public static final int SAVESTATE_DEVICE_ON = 4;
	public static final int SAVESTATE_DEVICE_OFF = 5;
	
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
	
	private double longitude;
	private double latitude;
	private Date clock;
	private String provider;
	private int accuracy;
	private int searchtime;
	private int savestate;
	private int battery;
	private int agentID;
	
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
	public Date getClock() {
		return clock;
	}
	public void setClock(Date clock) {
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
	public String getSavestateToString(){
		String res = "";
		switch(this.savestate){
		case SAVESTATE_OK:
			res = SAVESTATE_OK_BY_STR;
			break;
		case SAVESTATE_GPS_OFF:
			res = SAVESTATE_GPS_OFF_BY_STR;
			break;
		case SAVESTATE_SEARCH_TIME_OUT:
			res = SAVESTATE_SEARCH_TIME_OUT_BY_STR;
			break;
		case SAVESTATE_DEVICE_ON:
			res = SAVESTATE_DEVICE_ON_BY_STR;
			break;
		case SAVESTATE_DEVICE_OFF:
			res = SAVESTATE_DEVICE_OFF_BY_STR;
			break;
		default:
			res = SAVESTATE_NONE_BY_STR;
			break;
		}
		return res;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getAgentID() {
		return agentID;
	}

	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}
	
	public String getClockAsString(){
		return DATE_FORMAT.format(clock);
	}
	
}
package biz.dealnote.web.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
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
	@Id
	@SequenceGenerator(name="gen_loc", sequenceName="GEN_LOCATION_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_loc")
	private Integer id;
	
	private Double longitude;
	private Double latitude;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date clock;
	private String provider;
	private Integer accuracy;
	private Integer searchtime;
	private Integer savestate;
	private Integer battery;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="agentid")
	private Agent agent;
	
	public Location(){}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
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
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	public int getSearchtime() {
		return searchtime;
	}
	public void setSearchtime(Integer searchtime) {
		this.searchtime = searchtime;
	}
	public int getSavestate() {
		return savestate;
	}
	public void setSavestate(Integer savestate) {
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
		return battery == null ? -1 : battery;
	}
	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public String getClockAsString(){
		return DATE_FORMAT.format(clock);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + "]";
	}
	
}
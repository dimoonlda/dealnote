package biz.dealnote.web.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
public class Location {
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
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name="CLOCK")
	private DateTime creationDate;
	private String provider;
	private Integer accuracy;
	private Integer searchtime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="savestate")
	private LocationSaveState savestate;
	
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public DateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Integer getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}
	public Integer getSearchtime() {
		return searchtime;
	}
	public void setSearchtime(Integer searchtime) {
		this.searchtime = searchtime;
	}
	public LocationSaveState getSavestate() {
		return savestate;
	}
	public void setSavestate(LocationSaveState savestate) {
		this.savestate = savestate;
	}

	public Integer getBattery() {
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
	
	public String getCreationDateAsString(String format){
		if (this.creationDate != null) {
			return new SimpleDateFormat(format)
					.format(((DateTime) this.creationDate).toDate());
		}
		return null;
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
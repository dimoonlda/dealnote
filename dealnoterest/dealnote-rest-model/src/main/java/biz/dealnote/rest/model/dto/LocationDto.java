package biz.dealnote.rest.model.dto;

public class LocationDto {
	public Double longitude;
	public Double latitude;
	public Long creationDate;
	public String provider;
	public Integer accuracy;
	public Integer searchtime;
	public Integer savestate;
	public Integer battery;
	public Integer agentId;
	
	@Override
	public String toString() {
		return "LocationDto [longitude=" + longitude + ", latitude=" + latitude
				+ ", creationDate=" + creationDate + ", provider=" + provider
				+ ", accuracy=" + accuracy + ", searchtime=" + searchtime
				+ ", savestate=" + savestate + ", battery=" + battery
				+ ", agentId=" + agentId + "]";
	}
}

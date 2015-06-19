package biz.dealnote.rest.model.dto;

public class AgentSettingsDto {
	public Integer id;
	public Integer agentClientId;
	public Integer strictstopship;
	public Integer vsandps;
	public Short allowDiscount;
	public String fio;
	public String email;
	public String adminPass;
	public String moneyname;
	public String moneyformat;
	public String qtyformat;
	public Integer dayDelDoc;
	public Integer isGPS;
	public Integer frequencyGetGPS;
	public Integer frequencySendGPS;
	public Integer gpsFromHour;
	public Integer gpsToHour;
	public String gpsByDay;
	public Integer isAppStat;
	public Integer isGPSBeforeOrder;
	
	@Override
	public String toString() {
		return "AgentSettingsDto [id=" + id + ", agentClientId="
				+ agentClientId + ", strictstopship=" + strictstopship
				+ ", vsandps=" + vsandps
				+ ", allowDiscount=" + allowDiscount + ", fio=" + fio
				+ ", email=" + email + ", adminPass=" + adminPass
				+ ", moneyname=" + moneyname + ", moneyformat=" + moneyformat
				+ ", qtyformat=" + qtyformat + ", dayDelDoc=" + dayDelDoc
				+ ", isGPS=" + isGPS + ", frequencyGetGPS=" + frequencyGetGPS
				+ ", frequencySendGPS=" + frequencySendGPS + ", gpsFromHour="
				+ gpsFromHour + ", gpsToHour=" + gpsToHour + ", gpsByDay="
				+ gpsByDay + ", isAppStat=" + isAppStat + ", isGPSBeforeOrder="
				+ isGPSBeforeOrder + "]";
	}
}

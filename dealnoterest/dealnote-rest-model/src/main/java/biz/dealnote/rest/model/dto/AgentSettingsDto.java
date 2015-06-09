package biz.dealnote.rest.model.dto;

public class AgentSettingsDto {
	public Integer id;
	public Integer agentClientId;
	public Integer strictstopship;
	public String regnumprefix1;
	public Integer regnumnext1;
	public String regnumprefix2;
	public Integer regnumnext2;
	public Integer vsandps;
	public Integer autoDiscount;
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
				+ ", regnumprefix1=" + regnumprefix1 + ", regnumnext1="
				+ regnumnext1 + ", regnumprefix2=" + regnumprefix2
				+ ", regnumnext2=" + regnumnext2 + ", vsandps=" + vsandps
				+ ", autoDiscount=" + autoDiscount + ", fio=" + fio
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

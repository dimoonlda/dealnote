package biz.dealnote.rest.model.dto;

import org.joda.time.DateTime;

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
	public String wsServiceName;
	public String wsUserName;
	public String wsUserPass;	
	public Integer isAppStat;
	public Integer isGPSBeforeOrder;
}

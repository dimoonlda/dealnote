package biz.dealnote.web.web.dto;

import org.joda.time.DateTime;

import biz.dealnote.web.model.Agent;

public class AgentFilterDto {
	
	private Agent agent;
	private DateTime locationDate;
	
	public AgentFilterDto() {	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public DateTime getLocationDate() {
		return locationDate;
	}

	public void setLocationDate(DateTime locationDate) {
		this.locationDate = locationDate;
	}
}

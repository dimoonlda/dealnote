package org.dtrader.server.beans;
/**
 * Sales rep info
 * @author Lutay D.A.
 *
 */
public class AgentInfo {
	private String imei;
	private int agentID;
	private String message;
	
	public AgentInfo(){
		
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getAgentID() {
		return agentID;
	}

	public void setAgentID(int agentID) {
		this.agentID = agentID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

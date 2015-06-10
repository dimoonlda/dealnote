package biz.dealnote.rest.model;

/**
 * Consists information about REST service clients.
 * @author Lutay D.A.
 *
 */
public class RestClientInfo {
	
	private Integer clientType;
	private Integer agentId;
	
	public RestClientInfo(){
		
	}
	
	public RestClientInfo(Integer clientType, Integer agentId) {
		this.clientType = clientType;
		this.agentId = agentId;
	}
	
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer type) {
		this.clientType = type;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "RestClientInfo [clientType=" + clientType + ", agentId="
				+ agentId + "]";
	}
	
	
}

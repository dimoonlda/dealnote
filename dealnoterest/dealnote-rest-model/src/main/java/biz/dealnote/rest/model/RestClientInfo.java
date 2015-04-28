package biz.dealnote.rest.model;

/**
 * Consists information about REST service clients.
 * @author Lutay D.A.
 *
 */
public class RestClientInfo {
	/**
	 * Clients from mobile app
	 */
	public static final Integer REST_MOBILE_CLIENT_TYPE = 1;
	/**
	 * Clients from web app
	 */
	public static final Integer REST_WEB_CLIENT_TYPE = 2;
	
	private Integer clientType;
	private Integer agentId;
	private String serialNumber;
	private String login;
	private String pass;
	
	public RestClientInfo(){
		
	}
	
	public RestClientInfo(Integer clientType, Integer agentId, String serialNumber,
			String login, String pass) {
		this.clientType = clientType;
		this.agentId = agentId;
		this.serialNumber = serialNumber;
		this.login = login;
		this.pass = pass;
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
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "RestClientInfo [clientType=" + clientType + ", agentId="
				+ agentId + ", serialNumber=" + serialNumber + "]";
	}
	
	
}

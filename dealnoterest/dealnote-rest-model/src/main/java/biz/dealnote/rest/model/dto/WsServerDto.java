package biz.dealnote.rest.model.dto;


public class WsServerDto {
	public Integer id;
	public String serverAddress;
	public String description;
	public Short isDefault;
	
	public WsServerDto() {}
	
	public WsServerDto(Integer id, String serverAddress, String description,
			Short isDefault) {
		this.id = id;
		this.serverAddress = serverAddress;
		this.description = description;
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "WsServerDto [id=" + id + ", serverAddress=" + serverAddress
				+ ", description=" + description + ", isDefault=" + isDefault
				+ "]";
	}
}

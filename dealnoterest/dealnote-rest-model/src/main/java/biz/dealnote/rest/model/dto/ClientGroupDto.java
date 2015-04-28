package biz.dealnote.rest.model.dto;

public class ClientGroupDto {
	public Integer id;
	public String name;
	public Integer outerId;
	
	public ClientGroupDto(){}
	
	public ClientGroupDto(Integer id, String name, Integer outerId) {
		this.id = id;
		this.name = name;
		this.outerId = outerId;
	}
}

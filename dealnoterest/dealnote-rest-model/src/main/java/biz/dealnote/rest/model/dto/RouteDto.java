package biz.dealnote.rest.model.dto;


public class RouteDto {
	public Integer id;
	public String name;
	public Integer outerId;
	
	public RouteDto(){}
	
	public RouteDto(Integer id, String name, Integer outerId) {
		this.id = id;
		this.name = name;
		this.outerId = outerId;
	}
}

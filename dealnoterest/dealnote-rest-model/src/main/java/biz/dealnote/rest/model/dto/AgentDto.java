package biz.dealnote.rest.model.dto;


public class AgentDto {
	public Integer id;
	public String name;
	public Integer active;
	
	public AgentDto(){}
	
	public AgentDto(Integer id, String name, Integer active) {
		this.id = id;
		this.name = name;
		this.active = active;
	}

	@Override
	public String toString() {
		return "AgentDto [id=" + id + ", name=" + name + ", active=" + active
				+ "]";
	}
}

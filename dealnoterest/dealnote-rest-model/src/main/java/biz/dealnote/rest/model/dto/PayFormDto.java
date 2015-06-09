package biz.dealnote.rest.model.dto;

public class PayFormDto {
	public Integer id;
	public String name;
	public Integer outerId;
	
	public PayFormDto(){}
	
	public PayFormDto(Integer id, String name, Integer outerId) {
		this.id = id;
		this.name = name;
		this.outerId = outerId;
	}

	@Override
	public String toString() {
		return "PayFormDto [id=" + id + ", name=" + name + ", outerId="
				+ outerId + "]";
	}
}

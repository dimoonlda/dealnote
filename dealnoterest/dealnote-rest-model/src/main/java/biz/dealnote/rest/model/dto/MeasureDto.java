package biz.dealnote.rest.model.dto;

public class MeasureDto {
	public Integer id;
	public Integer outerId;
	public String name;
	
	public MeasureDto(){}
	
	public MeasureDto(Integer id, Integer outerId, String name) {
		this.id = id;
		this.outerId = outerId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "MeasureDto [id=" + id + ", outerId=" + outerId + ", name="
				+ name + "]";
	}
}

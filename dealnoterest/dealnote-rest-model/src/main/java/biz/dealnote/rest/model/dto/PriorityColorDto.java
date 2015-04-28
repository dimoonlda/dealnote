package biz.dealnote.rest.model.dto;

public class PriorityColorDto {
	public Integer id;
	public String colorCode;
	public String description;
	
	public PriorityColorDto(){}
	
	public PriorityColorDto(Integer id, String colorCode, String description) {
		this.id = id;
		this.colorCode = colorCode;
		this.description = description;
	}
}

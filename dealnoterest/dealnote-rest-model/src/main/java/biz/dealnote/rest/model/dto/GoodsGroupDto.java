package biz.dealnote.rest.model.dto;

public class GoodsGroupDto {
	public Integer id;
	public Integer outerId;
	public String name;
	public Integer parentId;
	
	public GoodsGroupDto(){}
	
	public GoodsGroupDto(Integer id, Integer outerId, String name,
			Integer parentId) {
		this.id = id;
		this.outerId = outerId;
		this.name = name;
		this.parentId = parentId;
	}
}

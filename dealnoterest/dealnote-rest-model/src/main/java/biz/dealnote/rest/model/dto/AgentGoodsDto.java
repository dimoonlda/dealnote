package biz.dealnote.rest.model.dto;

public class AgentGoodsDto {
	public Double price;
	public Double available;
	public GoodsDto goods;
	
	public AgentGoodsDto(){}
	
	public AgentGoodsDto(GoodsDto goods, Double price, Double available) {
		this.goods = goods;
		this.price = price;
		this.available = available;
	}
}

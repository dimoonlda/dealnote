package biz.dealnote.rest.model.dto;

public class DocumentDetailDto {
	public Integer id;
	public Integer goodsId;
	public Double itemcount;
	public Double priceWithoutVat;
	public Double priceWithVat;
	
	public DocumentDetailDto() {}

	public DocumentDetailDto(Integer id, Integer goodsId, Double itemcount,
			Double priceWithoutVat, Double priceWithVat) {
		this.id = id;
		this.goodsId = goodsId;
		this.itemcount = itemcount;
		this.priceWithoutVat = priceWithoutVat;
		this.priceWithVat = priceWithVat;
	}

	@Override
	public String toString() {
		return "DocumentDetailDto [id=" + id + ", goodsId=" + goodsId
				+ ", itemcount=" + itemcount + ", priceWithoutVat="
				+ priceWithoutVat + ", priceWithVat=" + priceWithVat + "]";
	}
}

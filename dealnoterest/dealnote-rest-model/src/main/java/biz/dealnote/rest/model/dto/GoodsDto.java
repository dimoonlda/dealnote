package biz.dealnote.rest.model.dto;

public class GoodsDto {
	public Integer id;
	public Integer goodsGroupId;
	public Integer sortPos;
	public Double vatCoef;
	public String name;
	public String fName;
	public Double weight;
	public Integer isActive;
	public Integer priorityColorId;
	public Integer outerId;
	public Integer measureId;
    public String goodsData;
	
    @Override
	public String toString() {
		return "GoodsDto [id=" + id + ", goodsGroupId=" + goodsGroupId
				+ ", sortPos=" + sortPos + ", vatCoef=" + vatCoef + ", name="
				+ name + ", fName=" + fName + ", weight=" + weight
				+ ", isActive=" + isActive + ", priorityColorId="
				+ priorityColorId + ", outerId=" + outerId + ", measureId="
				+ measureId + ", goodsData=" + goodsData + "]";
	}
}

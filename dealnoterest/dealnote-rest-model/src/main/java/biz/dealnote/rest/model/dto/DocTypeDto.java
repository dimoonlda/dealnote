package biz.dealnote.rest.model.dto;

public class DocTypeDto {
	public Integer id;
	public Integer payFormId;
	public Integer outerId;
	public Integer days;
	public Short accIncrNoVat;
	public Short accIncrWithVat;
	public String name;
	public Short discountFirst;
	public Short vatOverSum;
	
	@Override
	public String toString() {
		return "DocTypeDto [id=" + id + ", payFormId=" + payFormId
				+ ", outerId=" + outerId + ", days=" + days + ", accIncrNoVat="
				+ accIncrNoVat + ", accIncrWithVat=" + accIncrWithVat
				+ ", name=" + name + ", discountFirst=" + discountFirst
				+ ", vatOverSum=" + vatOverSum + "]";
	}
}

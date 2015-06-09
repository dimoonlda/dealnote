package biz.dealnote.rest.model.dto;

import java.util.LinkedList;
import java.util.List;

public class DocumentDto {
	public Integer id;
	public Integer clientId;
	public Integer agentId;
	public Integer docTypeId;
	public Integer linkId;
	public Long docDate;
	public Double discount;
	public Short saleType;
	public Long termDate;
	public Double sumWithoutVat;
	public Double sumWithVat;
	public String descript;
	public String regNum;
	public Double longitude;
	public Double latitude;
	public Double itemCount;
	public List<DocumentDetailDto> details = new LinkedList<DocumentDetailDto>();
	
	public DocumentDto() {}

	@Override
	public String toString() {
		return "DocumentDto [id=" + id + ", clientId=" + clientId
				+ ", agentId=" + agentId + ", docTypeId=" + docTypeId
				+ ", linkId=" + linkId + ", docDate=" + docDate + ", discount="
				+ discount + ", saleType=" + saleType + ", termDate="
				+ termDate + ", sumWithoutVat=" + sumWithoutVat
				+ ", sumWithVat=" + sumWithVat + ", descript=" + descript
				+ ", regNum=" + regNum + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", itemCount=" + itemCount
				+ ", details=" + details + "]";
	}
}

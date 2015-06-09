package biz.dealnote.rest.model.dto;

public class MeasureLinkDto {
	public Integer goodsId;
	public Integer measureSrcId;
	public Integer measureDstId;
	public Double srcValue;
	public Double dstValue;
	
	public MeasureLinkDto(){}
	
	public MeasureLinkDto(Integer goodsId, Integer measureSrcId,
			Integer measureDstId, Double srcValue, Double dstValue) {
		this.goodsId = goodsId;
		this.measureSrcId = measureSrcId;
		this.measureDstId = measureDstId;
		this.srcValue = srcValue;
		this.dstValue = dstValue;
	}

	@Override
	public String toString() {
		return "MeasureLinkDto [goodsId=" + goodsId + ", measureSrcId="
				+ measureSrcId + ", measureDstId=" + measureDstId
				+ ", srcValue=" + srcValue + ", dstValue=" + dstValue + "]";
	}
}

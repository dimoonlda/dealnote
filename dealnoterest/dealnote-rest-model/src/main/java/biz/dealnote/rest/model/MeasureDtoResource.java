package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.MeasureDto;

public class MeasureDtoResource extends AbstractModelResource<MeasureDto> {
	
	public MeasureDtoResource(){}
	
	public MeasureDtoResource(Collection<MeasureDto> data){
		super(data);
	}
}

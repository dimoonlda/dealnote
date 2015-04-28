package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.MeasureLinkDto;

public class MeasureLinksDtoResource extends
		AbstractModelResource<MeasureLinkDto> {
	
	public MeasureLinksDtoResource(){}
	
	public MeasureLinksDtoResource(Collection<MeasureLinkDto> data){
		super(data);
	}
}

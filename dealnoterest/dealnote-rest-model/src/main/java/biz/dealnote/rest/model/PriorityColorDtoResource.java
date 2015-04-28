package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.PriorityColorDto;

public class PriorityColorDtoResource extends AbstractModelResource<PriorityColorDto>{
	
	public PriorityColorDtoResource(){}
	
	public PriorityColorDtoResource(Collection<PriorityColorDto> data){
		super(data);
	}
}

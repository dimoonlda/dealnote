package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.GoodsGroupDto;

public class GoodsGroupDtoResource extends AbstractModelResource<GoodsGroupDto> {
	
	public GoodsGroupDtoResource(){}
	
	public GoodsGroupDtoResource(Collection<GoodsGroupDto> data){
		super(data);
	}
}

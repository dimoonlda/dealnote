package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.GoodsDto;

public class GoodsDtoResource extends AbstractModelResource<GoodsDto> {
	
	public GoodsDtoResource(){}
	
	public GoodsDtoResource(Collection<GoodsDto> data){
		super(data);
	}
}

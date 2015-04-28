package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.AgentGoodsDto;

public class AgentGoodsDtoResource extends AbstractModelResource<AgentGoodsDto> {
	
	public AgentGoodsDtoResource(){}
	
	public AgentGoodsDtoResource(Collection<AgentGoodsDto> data){
		super(data);
	}
}

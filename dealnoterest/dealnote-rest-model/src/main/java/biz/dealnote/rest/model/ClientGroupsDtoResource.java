package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.ClientGroupDto;

public class ClientGroupsDtoResource extends AbstractModelResource<ClientGroupDto> {
	
	public ClientGroupsDtoResource(){}
	
	public ClientGroupsDtoResource(Collection<ClientGroupDto> data){
		super(data);
	}
}

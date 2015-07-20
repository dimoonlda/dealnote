package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.ClientDto;

public class ClientDtoResource extends AbstractModelResource<ClientDto> {
	//test
	public ClientDtoResource(){}
	
	public ClientDtoResource(Collection<ClientDto> data){
		super(data);
	}
}

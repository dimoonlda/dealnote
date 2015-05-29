package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.WsServerDto;

public class WsServerDtoResource extends AbstractModelResource<WsServerDto> {
	
	public WsServerDtoResource() {}

	public WsServerDtoResource(Collection<WsServerDto> data) {
		super(data);
	}
	
}

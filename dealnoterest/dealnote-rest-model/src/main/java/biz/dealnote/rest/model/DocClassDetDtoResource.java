package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.DocClassDetDto;

public class DocClassDetDtoResource extends
		AbstractModelResource<DocClassDetDto> {

	public DocClassDetDtoResource(){}
	
	public DocClassDetDtoResource(Collection<DocClassDetDto> data){
		super(data);
	}
}

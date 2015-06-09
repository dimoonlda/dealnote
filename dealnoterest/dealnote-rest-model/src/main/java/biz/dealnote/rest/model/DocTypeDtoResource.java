package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.DocTypeDto;

public class DocTypeDtoResource extends AbstractModelResource<DocTypeDto> {

	public DocTypeDtoResource() {}
	
	public DocTypeDtoResource(Collection<DocTypeDto> data){
		super(data);
	}
}

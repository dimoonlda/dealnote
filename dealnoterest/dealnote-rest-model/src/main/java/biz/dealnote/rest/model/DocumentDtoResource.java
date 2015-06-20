package biz.dealnote.rest.model;

import java.util.Arrays;

import biz.dealnote.rest.model.dto.DocClassDetDto;
import biz.dealnote.rest.model.dto.DocumentDto;

public class DocumentDtoResource extends AbstractModelResource<DocumentDto> {
	
	private RestClientInfo restClient;
	private DocClassDetDto docClassDet;

	public DocumentDtoResource() {}

	public DocumentDtoResource(RestClientInfo restClient, 
			DocumentDto data,
			DocClassDetDto docClassDet){
		super(Arrays.asList(data));
		this.restClient = restClient;
		this.docClassDet = docClassDet;
	}
	
	public RestClientInfo getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClientInfo restClient) {
		this.restClient = restClient;
	}

	public DocClassDetDto getDocClassDet() {
		return docClassDet;
	}

	public void setDocClassDet(DocClassDetDto docClassDet) {
		this.docClassDet = docClassDet;
	}
}

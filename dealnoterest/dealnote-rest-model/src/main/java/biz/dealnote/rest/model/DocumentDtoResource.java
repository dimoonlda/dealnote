package biz.dealnote.rest.model;

import java.util.Arrays;
import biz.dealnote.rest.model.dto.DocumentDto;

public class DocumentDtoResource extends AbstractModelResource<DocumentDto> {
	
	private RestClientInfo restClient;

	public DocumentDtoResource() {}

	public DocumentDtoResource(RestClientInfo restClient, 
			DocumentDto data){
		super(Arrays.asList(data));
		this.restClient = restClient;
	}
	
	public RestClientInfo getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClientInfo restClient) {
		this.restClient = restClient;
	}
	
}

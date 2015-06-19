package biz.dealnote.rest.controllers;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static biz.dealnote.web.model.DefaultObjectsFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import biz.dealnote.rest.model.dto.AgentGoodsDto;
import biz.dealnote.rest.model.dto.AgentSettingsDto;
import biz.dealnote.rest.model.dto.ClientDto;
import biz.dealnote.rest.model.dto.ClientGroupDto;
import biz.dealnote.rest.model.dto.DocClassDetDto;
import biz.dealnote.rest.model.dto.DocTypeDto;
import biz.dealnote.rest.model.dto.DocumentDto;
import biz.dealnote.rest.model.dto.GoodsDto;
import biz.dealnote.rest.model.dto.GoodsGroupDto;
import biz.dealnote.rest.model.dto.LocationDto;
import biz.dealnote.rest.model.dto.MeasureDto;
import biz.dealnote.rest.model.dto.MeasureLinkDto;
import biz.dealnote.rest.model.dto.PayFormDto;
import biz.dealnote.rest.model.dto.PriorityColorDto;
import biz.dealnote.rest.model.dto.RouteDto;
import biz.dealnote.rest.model.dto.WsServerDto;
import biz.dealnote.rest.security.UserContext;
import biz.dealnote.rest.service.DealNoteRestService;
import biz.dealnote.rest.service.DtoConverterService;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.Location;

public class DealNoteRestControllerTest{

	private static final String REQUEST_BODY = "{\"clientType\":\"1\",\"agentId\":\"444\"}"; 
	private static final String LOCATION_REQUEST_BODY = "{\"restClient\":{\"clientType\":\"1\",\"agentId\":\"444\"},\"size\":2,\"dataArray\":[{\"longitude\":1212323.56,\"latitude\":456734522.0,\"creationDate\":1427654365,\"provider\":\"gps\",\"accuracy\":1,\"searchtime\":87,\"savestate\":1,\"battery\":45,\"agentId\":444},{\"longitude\":1212323.56,\"latitude\":456734522.0,\"creationDate\":1427654365,\"provider\":\"gps\",\"accuracy\":1,\"searchtime\":22,\"savestate\":1,\"battery\":77,\"agentId\":444}]}";
	private static final String DOCUMENT_REQUEST_BODY = "{\"restClient\":{\"clientType\":\"1\",\"agentId\":\"444\"},\"size\":1,\"dataArray\":[{\"id\":121,\"clientId\":3,\"agentId\":444,\"docTypeId\":1,\"linkId\":123234,\"docDate\":1429041182,\"discount\":8,\"saleType\":1,\"termDate\":1429041182,\"sumWithoutVat\":123.0,\"sumWithVat\":522.0,\"descript\":\"Test кирилица\",\"regNum\":\"W444\",\"longitude\":50.2345,\"latitude\":51.5436677,\"itemCount\":222,\"details\":[{\"id\":12,\"goodsId\":2,\"itemcount\":12.0,\"priceWithoutVat\":1.2,\"priceWithVat\":1.8},{\"id\":13,\"goodsId\":3,\"itemcount\":142,\"priceWithoutVat\":123.99,\"priceWithVat\":134.0}]}]}";
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	private static final Agent AGENT = createDefaultAgent(DEFAULT_AGENT_ID, createDefaultUser(1));
	
	private MockMvc mockMvc;
	@Mock
	private DealNoteRestService dealNoteRestService;
	@Mock
	private DtoConverterService dtoConverterService;
	
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	this.mockMvc = MockMvcBuilders.standaloneSetup(
    			new DealNoteRestController(dealNoteRestService, dtoConverterService))
    			.build();
    	when(dealNoteRestService.getAgentById(anyInt())).thenReturn(AGENT);
    	
    	Authentication authentication = mock(Authentication.class);
    	when(authentication.getPrincipal()).thenReturn(new UserContext(AGENT.getUser()));
    	SecurityContext securityContext = mock(SecurityContext.class);
    	when(securityContext.getAuthentication()).thenReturn(authentication);
    	SecurityContextHolder.setContext(securityContext);
    }
    
	@Test
	public void testGetAllGoodsGroup() throws Exception{
		when(dtoConverterService.buildGoodsGroupDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new GoodsGroupDto(), 
				new GoodsGroupDto()));
		
		this.mockMvc.perform(post("/restfull/goodsgroups")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2));
	}

	@Test
	public void testGetClientsByAgentId() throws Exception{
		when(dtoConverterService.buildClientDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new ClientDto(), 
				new ClientDto()));
		
		this.mockMvc.perform(post("/restfull/clients")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2));
	}
	
	@Test
	public void testGetAllClientGroups() throws Exception{
		when(dtoConverterService.buildClientGroupDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new ClientGroupDto(1, null, null), 
				new ClientGroupDto(2, null, null)));
		
		this.mockMvc.perform(post("/restfull/clientgroups")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetDocClassDets() throws Exception{
		when(dtoConverterService.buildDocClassDetDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new DocClassDetDto(1), 
				new DocClassDetDto(2)));
		
		this.mockMvc.perform(post("/restfull/docclassdets")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetMeasures() throws Exception{
		when(dtoConverterService.buildMeasureDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new MeasureDto(1, null, null), 
				new MeasureDto(2, null, null)));
		
		this.mockMvc.perform(post("/restfull/measures")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetRoutes() throws Exception{
		when(dtoConverterService.buildRouteDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new RouteDto(1, null, null), 
				new RouteDto(2, null, null)));
		
		this.mockMvc.perform(post("/restfull/routes")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetWsServers() throws Exception{
		when(dtoConverterService.buildWsServerDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new WsServerDto(1, null, null, null), 
				new WsServerDto(2, null, null, null)));
		
		this.mockMvc.perform(post("/restfull/wsservers")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetPriorityColors() throws Exception{
		when(dtoConverterService.buildPriorityColorDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new PriorityColorDto(1, null, null), 
				new PriorityColorDto(2, null, null)));
		
		this.mockMvc.perform(post("/restfull/prioritycolors")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetDocumentTypes() throws Exception{
		when(dtoConverterService.buildDocTypeDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new DocTypeDto(1), 
				new DocTypeDto(2)));
		
		this.mockMvc.perform(post("/restfull/documenttypes")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetPayForms() throws Exception{
		when(dtoConverterService.buildPayFormDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new PayFormDto(1, null, null), 
				new PayFormDto(2, null, null)));
		
		this.mockMvc.perform(post("/restfull/payforms")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2))
        .andExpect(jsonPath("$.dataArray[0].id").value(1));
	}

	@Test
	public void testGetGoods() throws Exception{
		when(dtoConverterService.buildGoodsDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new GoodsDto(), 
				new GoodsDto()));
		
		this.mockMvc.perform(post("/restfull/goods")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2));
	}

	@Test
	public void testGetMeasureLinks() throws Exception{
		when(dtoConverterService.buildMeasureLinkDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new MeasureLinkDto(), 
				new MeasureLinkDto()));
		
		this.mockMvc.perform(post("/restfull/measurelinks")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2));
	}

	@Test
	public void testGetAgentStore() throws Exception{
		when(dtoConverterService.buildAgentGoodsDtoCollection(anyCollection()))
		.thenReturn(Arrays.asList(new AgentGoodsDto(), 
				new AgentGoodsDto()));
		
		this.mockMvc.perform(post("/restfull/agentgoods")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(2));
	}

	@Test
	public void testGetAgentInfo() throws Exception{
		when(dtoConverterService.buildAgentSettingsDto(any(Agent.class)))
		.thenReturn(new AgentSettingsDto());
		
		this.mockMvc.perform(post("/restfull/agentsettings")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.size").value(1));
	}

	@Test
	public void testSaveLocations() throws Exception{
		when(dtoConverterService.buildLocation(any(LocationDto.class)))
		.thenReturn(new Location());
		
		this.mockMvc.perform(post("/restfull/locations")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(LOCATION_REQUEST_BODY))
        .andExpect(status().isOk());
	}

	@Test
	public void testSaveDocument() throws Exception{
		int docId = 111;
		Document doc = DefaultObjectsFactory.createDefaultDocument(docId);
		when(dtoConverterService.buildDocument(any(DocumentDto.class)))
		.thenReturn(Optional.of(doc));
		
		this.mockMvc.perform(post("/restfull/documents")
				.accept(MediaType.parseMediaType(CONTENT_TYPE))
				.contentType(MediaType.APPLICATION_JSON)
				.content(DOCUMENT_REQUEST_BODY))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString().equals(doc.getId().toString());
	}

}

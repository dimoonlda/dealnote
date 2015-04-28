package biz.dealnote.rest.controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import biz.dealnote.rest.controllers.exceptions.RequestHandledException;
import biz.dealnote.rest.controllers.exceptions.WrongAgentException;
import biz.dealnote.rest.controllers.exceptions.WrongRestClientException;
import biz.dealnote.rest.model.AgentGoodsDtoResource;
import biz.dealnote.rest.model.AgentSettingsDtoResource;
import biz.dealnote.rest.model.ClientGroupsDtoResource;
import biz.dealnote.rest.model.ClientDtoResource;
import biz.dealnote.rest.model.DocumentDtoResource;
import biz.dealnote.rest.model.GoodsGroupDtoResource;
import biz.dealnote.rest.model.GoodsDtoResource;
import biz.dealnote.rest.model.LocationDtoResource;
import biz.dealnote.rest.model.MeasureDtoResource;
import biz.dealnote.rest.model.MeasureLinksDtoResource;
import biz.dealnote.rest.model.PriorityColorDtoResource;
import biz.dealnote.rest.model.RestClientInfo;
import biz.dealnote.rest.model.RoutesDtoResource;
import biz.dealnote.rest.model.dto.AgentGoodsDto;
import biz.dealnote.rest.model.dto.AgentSettingsDto;
import biz.dealnote.rest.model.dto.ClientDto;
import biz.dealnote.rest.model.dto.ClientGroupDto;
import biz.dealnote.rest.model.dto.DocumentDto;
import biz.dealnote.rest.model.dto.GoodsDto;
import biz.dealnote.rest.model.dto.GoodsGroupDto;
import biz.dealnote.rest.model.dto.LocationDto;
import biz.dealnote.rest.model.dto.MeasureDto;
import biz.dealnote.rest.model.dto.MeasureLinkDto;
import biz.dealnote.rest.model.dto.PriorityColorDto;
import biz.dealnote.rest.model.dto.RouteDto;
import biz.dealnote.rest.service.DealNoteRestService;
import biz.dealnote.rest.service.DtoConverterService;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.PriorityColor;

@RestController
@RequestMapping("/restfull")
public class DealNoteRestController {
	
	ThreadLocal<Integer> counter = new ThreadLocal<Integer>();
			
	private static Logger logger = Logger.getLogger(DealNoteRestController.class);
	
	private DealNoteRestService dealNoteRestService;
	
	@Autowired
	private DtoConverterService dtoConverterService;
	
	@Autowired
	public DealNoteRestController(DealNoteRestService dealNoteRestService) {
		this.dealNoteRestService = dealNoteRestService;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public RestClientInfo test(){
		return new RestClientInfo(1, 100, "1234567890", "test", "testPass");
	}
		
	@RequestMapping(value = "/goodsgroups", method = RequestMethod.POST)
	public GoodsGroupDtoResource getAllGoodsGroup(@RequestBody RestClientInfo client) throws RequestHandledException{
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			Collection<GoodsGroupDto> groups = dtoConverterService
					.buildGoodsGroupDtoCollection(dealNoteRestService.getAllGoodsGroups());
			logger.info(String.format("%s: got goods groups. Size: %d", client, groups.size()));
			return new GoodsGroupDtoResource(groups);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting goods groups. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting goods groups for agentId: %d", client.getAgentId()), ex);
		}
	}
	
	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public ClientDtoResource getClientsByAgentId(@RequestBody RestClientInfo client) throws RequestHandledException{
		Agent agent = null;
		try{
			checkClient(client);
			agent = checkAgentAndReturn(client.getAgentId());
			Collection<ClientDto> clients = dtoConverterService
					.buildClientDtoCollection(dealNoteRestService.getClientsByAgentID(agent.getId()));
			logger.info(String.format("%s: got clients. Size: %d", client, clients.size()));		
			return new ClientDtoResource(clients);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting clients. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting clients for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/clientgroups", method = RequestMethod.POST)
	public ClientGroupsDtoResource getAllClientGroups(@RequestBody RestClientInfo client) throws RequestHandledException{
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			Collection<ClientGroupDto> groups = dtoConverterService
					.buildClientGroupDtoCollection(dealNoteRestService.getAllClientGroups());
			logger.info(String.format("%s: got client groups. Size: %d", client, groups.size()));
			return new ClientGroupsDtoResource(groups);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting client groups. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting client groups for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/measures", method = RequestMethod.POST)
	public MeasureDtoResource getMeasures(@RequestBody RestClientInfo client) throws RequestHandledException{
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			Collection<MeasureDto> measures = dtoConverterService
					.buildMeasureDtoCollection(dealNoteRestService.getAllMeasures());
			logger.info(String.format("%s: got measures. Size: %d", client, measures.size()));
			
			return new MeasureDtoResource(measures);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting measures. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting measures for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/routes", method = RequestMethod.POST)
	public RoutesDtoResource getRoutes(@RequestBody RestClientInfo client) throws RequestHandledException{
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			Collection<RouteDto> routes = dtoConverterService
					.buildRouteDtoCollection(dealNoteRestService.getAllRoutes());
			logger.info(String.format("%s: got routes. Size: %d", client, routes.size()));
			return new RoutesDtoResource(routes);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting routes. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting routes for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/prioritycolors", method = RequestMethod.POST)
	public PriorityColorDtoResource getPriorityColors(@RequestBody RestClientInfo client) throws RequestHandledException{
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			Collection<PriorityColorDto> colors = dtoConverterService
					.buildPriorityColorDtoCollection(dealNoteRestService.getAllPriorityColor());
			logger.info(String.format("%s: got priority color. Size: %d", client, colors.size()));
			return new PriorityColorDtoResource(colors);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting priority color. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting priority color for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	public GoodsDtoResource getGoods(@RequestBody RestClientInfo client) throws RequestHandledException{
		Agent agent = null;
		try{
			checkClient(client);
			agent = checkAgentAndReturn(client.getAgentId());
			
			Collection<GoodsDto> goodsCol = dtoConverterService
					.buildGoodsDtoCollection(dealNoteRestService.getGoodsByAgent(agent));
			logger.info(String.format("%s: got goods. Size: %d", client, goodsCol.size()));
			return new GoodsDtoResource(goodsCol);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting goods. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting goods for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/measurelinks", method = RequestMethod.POST)
	public MeasureLinksDtoResource getMeasureLinks(@RequestBody RestClientInfo client) throws RequestHandledException{
		Agent agent = null;
		try{
			checkClient(client);
			agent = checkAgentAndReturn(client.getAgentId());
			
			Collection<MeasureLinkDto> links = dtoConverterService
					.buildMeasureLinkDtoCollection(dealNoteRestService.getMeasureLinkByAgent(agent));
			logger.info(String.format("%s: got measure links. Size: %d", client, links.size()));
			return new MeasureLinksDtoResource(links);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting measure links. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting measure links for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/agentgoods", method = RequestMethod.POST)
	public AgentGoodsDtoResource getAgentStore(@RequestBody RestClientInfo client) throws RequestHandledException{
		Agent agent = null;
		try{
			checkClient(client);
			agent = checkAgentAndReturn(client.getAgentId());
			
			Collection<AgentGoodsDto> agentGoodsCol = dtoConverterService
					.buildAgentGoodsDtoCollection(dealNoteRestService.getAgentGoodsByAgent(agent));
			logger.info(String.format("%s: got agent's goods. Size: %d", client, agentGoodsCol.size()));
			return new AgentGoodsDtoResource(agentGoodsCol);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting agent's goods. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting agent's goods for agentId: %d", client.getAgentId()), ex);
		}
	}
	
	@RequestMapping(value = "/agentsettings", method = RequestMethod.POST)
	public AgentSettingsDtoResource getAgentInfo(@RequestBody RestClientInfo client, 
			BindingResult result) throws RequestHandledException{
		Agent agent = null; 
		try{
			checkClient(client);
			agent = checkAgentAndReturn(client.getAgentId());
			
			Collection<AgentSettingsDto> agentCol = 
					Arrays.asList(dtoConverterService.buildAgentSettingsDto(agent));
			logger.info(String.format("%s: got agent's info. Size: %d", client, agentCol.size()));
			return new AgentSettingsDtoResource(agentCol);
		}catch(Exception ex){
			logger.error(String.format("%s: error when getting agent's info. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when getting agent's info for agentId: %d", client.getAgentId()), ex);
		}
	}

	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	public void saveLocations(@RequestBody LocationDtoResource locationResource) 
			throws RequestHandledException{
		
		RestClientInfo client = locationResource.getRestClient();
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			
			Collection<Location> locations =locationResource.getDataArray().stream()
					.map(locationDto -> dtoConverterService.buildLocation(locationDto))
					.collect(Collectors.toList());
			
			dealNoteRestService.saveLocations(locations);
			logger.info(String.format("%s: saved agent's locations. Size: %d", client, locations.size()));
		}catch(Exception ex){
			logger.error(String.format("%s: error when saving locations. ", client), ex);
			
			throw new RequestHandledException(
					String.format("Exception when saving locations for agentId: %d", client.getAgentId()), ex);
		}
		
	}
	
	@RequestMapping(value = "/documents", method = RequestMethod.POST)
	public void saveDocument(@RequestBody DocumentDtoResource docRes) 
			throws RequestHandledException{
		RestClientInfo client = docRes.getRestClient();
		try{
			checkClient(client);
			checkAgentAndReturn(client.getAgentId());
			
			Document doc = null;
			if(!docRes.getDataArray().isEmpty()){
				DocumentDto docDto = docRes.getDataArray().iterator().next();
				doc = dtoConverterService.biuldDocument(docDto)
						.orElseThrow(NullPointerException::new);
				dealNoteRestService.save(doc);
				logger.info(String.format("%s: saved agent's document. ID: %d", client, doc.getId()));
			}
			
		}catch(Exception ex){
			logger.error(String.format("%s: error when saving document. ", client), ex);
		
			throw new RequestHandledException(
					String.format("Exception when saving document for agentId: %d", client.getAgentId()), ex);
		}

	}

	/**
	 * Check if client has right data.
	 * @param client
	 * @throws WrongRestClientException
	 */
	private void checkClient(RestClientInfo client) throws WrongRestClientException{
		if(client == null){
			logger.error("Rest Client is null.");
			throw new WrongRestClientException("Client is null.");
		}
			
	}
	
	/**
	 * Check if agent with agentId presents in database.
	 * If agent presents then method will return this sgent object
	 * @param agentId - agent ID
	 * @return 
	 * @throws WrongAgentException
	 */
	private Agent checkAgentAndReturn(int agentId) throws WrongAgentException{
		try{
			Agent agent = dealNoteRestService.getAgentById(agentId);
			return agent;
		}catch(Exception e){
			throw new WrongAgentException("Wrong agent.");
		}
		
	}
	
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	@ExceptionHandler(GoodsGroupNotFound.class)
//	private void groupNotFound(){
//		//TODO: e.g. logging this exception
//		System.out.println("Error!!!");
//	}
}

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such group found")
//class GoodsGroupNotFound extends RuntimeException{
//
//	private static final long serialVersionUID = 1L;
//
//	public GoodsGroupNotFound(int id) {
//		System.out.println("groupId: " + id);
//	}
//	
//}
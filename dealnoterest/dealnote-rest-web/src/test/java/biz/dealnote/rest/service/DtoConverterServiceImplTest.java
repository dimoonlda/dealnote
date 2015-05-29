package biz.dealnote.rest.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Optional;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.rest.AbstractTest;
import biz.dealnote.rest.controllers.exceptions.CreateDtoException;
import biz.dealnote.rest.model.dto.AgentSettingsDto;
import biz.dealnote.rest.model.dto.DocumentDetailDto;
import biz.dealnote.rest.model.dto.DocumentDto;
import biz.dealnote.rest.model.dto.LocationDto;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.dao.WsServerDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.WsServer;

public class DtoConverterServiceImplTest extends AbstractTest{

	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private PriorityColorDao priorityColorDao;
	
	@Autowired
	private MeasureDao measureDao;
	
	@Autowired
	private AgentDAO agentDao;

	@Autowired
	private MeasureLinkDao measureLinkDao;
	
	@Autowired
	private GoodsGroupDao goodsGroupDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private ClientGroupDao clientGroupDao;
	
	@Autowired
	private ClientDAO clientDao;
	
	@Autowired
	private AgentGoodsDao agentGoodsDao;
	
	@Autowired
	private LocationDAO locationDao;
	
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private WsServerDao wsServerDao;

	@Autowired
	private DocumentDetailDao documentDetailDao;
	
	@Autowired
	private DtoConverterService dtoConverterService;
	
	private static final Integer TEST_ROUTE_ID = 81;
	private static final Integer TEST_PRIORITY_COLOR_ID = 10;
	private static final Integer TEST_MEASURE_ID = 1;
	private static final Integer TEST_AGENT_ID = 444;
	private static final Integer TEST_MEASURE_LINK_ID = 2;
	private static final Integer TEST_GOODS_GROUP_ID = 2;
	private static final Integer TEST_GOODS_ID = 2;
	private static final Integer TEST_CLIENT_ID = 3;
	private static final Integer TEST_CLIENT_GROUP_ID = 3;
	private static final Integer TEST_AGENT_GOODS_ID = 5;
	private static final Integer TEST_WSSERVER_ID = 1;
	private static final Integer TEST_LOCATION_ID = 1;
	private static final Integer TEST_DOCUMENT_ID = 1;
	private static final Integer TEST_DOCUMENT_DETAIL_ID = 1;
	private static final Integer TEST_DOCUMENT_TYPE_ID = 1;
	
	@Test
	public void testBuildRouteDto() {
		Route route = routeDao.getRouteById(TEST_ROUTE_ID);
		assertNotNull("Route object is null.", route);
		assertNotNull("RouteDto object wasn't created.", 
				dtoConverterService.buildRouteDto(route));
	}

	@Test
	public void testBuildRouteDtoCollection() throws CreateDtoException {
		Collection<Route> routes = routeDao.getRoutes();
		assertNotNull(routes);
		assertEquals(routes.size(), 
				dtoConverterService.buildRouteDtoCollection(routes).size());
	}

	@Test
	public void testBuildPriorityColorDto() {
		PriorityColor priorityColor = priorityColorDao.getPriorityColorById(TEST_PRIORITY_COLOR_ID);
		assertNotNull("PriorityColor object is null.", priorityColor);
		assertNotNull("PriorityColorDto object wasn't created.", 
				dtoConverterService.buildPriorityColorDto(priorityColor));
	}

	@Test
	public void testBuildPriorityColorDtoCollection() throws CreateDtoException {
		Collection<PriorityColor> priorityColors = priorityColorDao.getAllPriorityColors();
		assertNotNull(priorityColors);
		assertEquals(priorityColors.size(), 
				dtoConverterService.buildPriorityColorDtoCollection(priorityColors).size());
	}

	@Test
	public void testBuildMeasureDto() {
		Measure measure = measureDao.getMeasureById(TEST_MEASURE_ID);
		assertNotNull("Measure object is null.", measure);
		assertNotNull("MeasureDto object wasn't created.", 
				dtoConverterService.buildMeasureDto(measure));
	}

	@Test
	public void testBuildMeasureDtoCollection() throws CreateDtoException {
		Collection<PriorityColor> priorityColors = priorityColorDao.getAllPriorityColors();
		assertNotNull(priorityColors);
		assertEquals(priorityColors.size(), 
				dtoConverterService.buildPriorityColorDtoCollection(priorityColors).size());
	}

	@Test
	public void testBuildAgentSettingsDto() throws CreateDtoException {
		Agent agent = agentDao.getAgentById(TEST_AGENT_ID);
		assertNotNull("Agent object is null.", agent);
		assertNotNull("AgentSettingsDto object wasn't created.", 
				dtoConverterService.buildAgentSettingsDto(agent));
	}

	@Test
	public void testBuildMeasureLinkDto() {
		MeasureLink measureLink = measureLinkDao.getMeasureLinkById(TEST_MEASURE_LINK_ID);
		assertNotNull("MeasureLink object is null.", measureLink);
		assertNotNull("MeasureLinkDto object wasn't created.", 
				dtoConverterService.buildMeasureLinkDto(measureLink));
	}

	@Test
	public void testBuildMeasureLinkDtoCollection() throws CreateDtoException {
		Collection<MeasureLink> measureLinks = measureLinkDao.getAllMeasureLinks();
		assertNotNull(measureLinks);
		assertEquals(measureLinks.size(), 
				dtoConverterService.buildMeasureLinkDtoCollection(measureLinks).size());
	}

	@Test
	public void testBuildGoodsGroupDto() {
		GoodsGroup goodsGroup = goodsGroupDao.getGoodsGroupById(TEST_GOODS_GROUP_ID);
		assertNotNull("GoodsGroup object is null.", goodsGroup);
		assertNotNull("GoodsGroupDto object wasn't created.", 
				dtoConverterService.buildGoodsGroupDto(goodsGroup));
	}

	@Test
	public void testBuildGoodsGroupDtoCollection() throws CreateDtoException {
		Collection<GoodsGroup> goodsGroups = goodsGroupDao.getAllGoodsGroups();
		assertNotNull(goodsGroups);
		assertEquals(goodsGroups.size(), 
				dtoConverterService.buildGoodsGroupDtoCollection(goodsGroups).size());
	}

	@Test
	public void testBuildGoodsDto() {
		Goods goods = goodsDao.getGoodsById(TEST_GOODS_ID);
		assertNotNull("Goods object is null.", goods);
		assertNotNull("GoodsDto object wasn't created.", 
				dtoConverterService.buildGoodsDto(goods));
	}

	@Test
	public void testBuildGoodsDtoCollection() throws CreateDtoException {
		Collection<Goods> goods = goodsDao.getAllGoods();
		assertNotNull(goods);
		assertEquals(goods.size(), 
				dtoConverterService.buildGoodsDtoCollection(goods).size());
	}

	@Test
	public void testBuildClientGroupDto() {
		ClientGroup clientGroup = clientGroupDao.getGroupById(TEST_CLIENT_GROUP_ID);
		assertNotNull("ClientGroup object is null.", clientGroup);
		assertNotNull("ClientGroupDto object wasn't created.", 
				dtoConverterService.buildClientGroupDto(clientGroup));
	}

	@Test
	public void testBuildClientGroupDtoCollection() throws CreateDtoException {
		Collection<ClientGroup> clientGroups = clientGroupDao.getGroups();
		assertNotNull(clientGroups);
		assertEquals(clientGroups.size(), 
				dtoConverterService.buildClientGroupDtoCollection(clientGroups).size());
	}

	@Test
	public void testBuildClientDto() {
		Client client = clientDao.getClietnById(TEST_CLIENT_ID);
		assertNotNull("Client object is null.", client);
		assertNotNull("ClientDto object wasn't created.", 
				dtoConverterService.buildClientDto(client));
	}

	@Test
	public void testBuildClientDtoCollection() throws CreateDtoException {
		Collection<Client> clients = clientDao.getClientsByAgent(TEST_AGENT_ID);
		assertNotNull(clients);
		assertEquals(clients.size(), 
				dtoConverterService.buildClientDtoCollection(clients).size());
	}

	@Test
	public void testBuildAgentGoodsDto() {
		AgentGoods agentGoods = agentGoodsDao.getAgentGoodsById(TEST_AGENT_GOODS_ID);
		assertNotNull("AgentGoods object is null.", agentGoods);
		assertNotNull("AgentGoodsDto object wasn't created.", 
				dtoConverterService.buildAgentGoodsDto(agentGoods));
	}

	@Test
	public void testBuildAgentGoodsDtoCollection() throws CreateDtoException {
		Collection<AgentGoods> agentGoods = agentGoodsDao.getAllAgentGoods();
		assertNotNull(agentGoods);
		assertEquals(agentGoods.size(), 
				dtoConverterService.buildAgentGoodsDtoCollection(agentGoods).size());
	}

	@Test
	public void testBuildLocation() {
		LocationDto locDto = new LocationDto();
		locDto.agentId = TEST_AGENT_ID;
		locDto.accuracy = 1;
		locDto.battery = 1;
		locDto.creationDate = DateTime.now().getMillis();
		locDto.latitude = 45.456778;
		locDto.longitude = 56.785654;
		locDto.provider = "gps";
		locDto.savestate = 1;
		locDto.searchtime = 10;
		assertEquals(locDto.agentId,
				dtoConverterService.buildLocation(locDto).getAgent().getId());
	}

	@Test
	public void testBuildDocument() {
		DocumentDto docDto = new DocumentDto();
		docDto.agentId = TEST_AGENT_ID;
		docDto.clientId = TEST_CLIENT_ID;
		docDto.descript = "test description";
		docDto.discount = 0.0;
		docDto.docDate = DateTime.now().getMillis();
		docDto.docTypeId = TEST_DOCUMENT_TYPE_ID;
		docDto.itemCount = 11d;
		docDto.latitude = 33.67543;
		docDto.longitude = 45.6789;
		docDto.linkId = 123456789;
		docDto.saleType = 1;
		docDto.sumWithoutVat = 123.99;
		docDto.sumWithVat = 99.99;
		docDto.termDate = DateTime.now().getMillis();
		Optional<Document> document = dtoConverterService.buildDocument(docDto);
		assertTrue(document.isPresent());
		assertEquals(docDto.agentId,
				document.get().getAgent().getId());
	}

	@Test
	public void testBuildDocumentDetail() {
		DocumentDetailDto detDto = new DocumentDetailDto();
		detDto.goodsId = TEST_GOODS_ID;
		detDto.itemcount = 12d;
		detDto.priceWithoutVat = 123.4;
		detDto.priceWithVat = 3.6;
		Optional<DocumentDetail> detail = 
				dtoConverterService.buildDocumentDetail(detDto);
		assertTrue(detail.isPresent());
		assertEquals(detDto.goodsId, 
				detail.get().getGoods().getId());
	}

	@Test
	public void testBuildWsServerDtoCollection() throws CreateDtoException {
		Collection<WsServer> servers = wsServerDao.getServers();
		assertNotNull(servers);
		assertEquals(servers.size(), 
				dtoConverterService.buildWsServerDtoCollection(servers).size());
	}

	@Test
	public void testBuildWsServerDto() {
		WsServer server = wsServerDao.getServerById(TEST_WSSERVER_ID).get();
		assertNotNull("WsServer object is null.", server);
		assertNotNull("WsServerDto object wasn't created.", 
				dtoConverterService.buildWsServerDto(server));
	}
}

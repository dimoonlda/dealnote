package biz.dealnote.rest.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.dao.PayFormDao;
import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.dao.WsServerDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.DocType;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PayForm;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.WsServer;
import static biz.dealnote.web.model.DefaultObjectsFactory.*;
import biz.dealnote.web.model.GoodsGroup;

public class DealNoteRestServiceImplTest{

	@Mock
	private GoodsGroupDao goodsGroupDao;
	@Mock
	private ClientDAO clientDao;
	@Mock
	private ClientGroupDao clientGroupDao;
	@Mock
	private MeasureDao measureDao;
	@Mock
	private RouteDao routeDao;
	@Mock
	private GoodsDao goodsDao;
	@Mock
	private AgentDAO agentDao;
	@Mock
	private AgentGoodsDao agentGoodsDao;
	@Mock
	private PriorityColorDao priorityColorDao;
	@Mock
	private MeasureLinkDao measureLinkDao;
	@Mock
	private LocationDAO locationDao;
	@Mock
	private DocumentDao documentDao;
	@Mock
	private WsServerDao wsServerDao;
	@Mock
	private DocTypeDao docTypeDao;
	@Mock
	private PayFormDao payFormDao;
	
	private final Agent testAgent = createDefaultAgent(DEFAULT_AGENT_ID);
	
	private DealNoteRestService dealNoteRestService;
	
	@Before
	public void init(){
		initMocks(this);
		dealNoteRestService = new DealNoteRestServiceImpl(goodsGroupDao,
				clientDao, 
				clientGroupDao, 
				measureDao, 
				routeDao, 
				goodsDao,
				agentDao, 
				agentGoodsDao, 
				priorityColorDao, 
				measureLinkDao,
				locationDao, 
				documentDao,
				wsServerDao,
				payFormDao,
				docTypeDao);
	}
	
	@Test
	public void testGetGoodsGroupById() {
		GoodsGroup group = createDefaultGoodsGroup(null);
		when(goodsGroupDao.getGoodsGroupById(1)).thenReturn(group);
		GoodsGroup result = dealNoteRestService.getGoodsGroupById(1);
		assertEquals(group, result);
	}

	@Test
	public void testGetAllGoodsGroups() {
		Collection<GoodsGroup> groups = Arrays.asList(createDefaultGoodsGroup(1),
				createDefaultGoodsGroup(2),
				createDefaultGoodsGroup(3));
		when(goodsGroupDao.getAllGoodsGroups()).thenReturn(groups);
		Collection<GoodsGroup> result = dealNoteRestService.getAllGoodsGroups();
		assertEquals(groups.size(), result.size());
	}

	@Test
	public void testGetClientsByAgentID() {
		Collection<Client> clients = Arrays.asList(createDefaultClient(1, null, null),
				createDefaultClient(2, null, null),
				createDefaultClient(3, null, null));
		when(clientDao.getClientsByAgent(1)).thenReturn(clients);
		Collection<Client> result = dealNoteRestService.getClientsByAgentID(1);
		assertEquals(clients.size(), result.size());
	}

	@Test
	public void testGetAllClientGroups() {
		Collection<ClientGroup> groups = Arrays.asList(createDefaultClientGroup(1),
				createDefaultClientGroup(2),
				createDefaultClientGroup(3));
		when(clientGroupDao.getGroups()).thenReturn(groups);
		Collection<ClientGroup> result = dealNoteRestService.getAllClientGroups();
		assertEquals(groups.size(), result.size());
	}

	@Test
	public void testGetAllMeasures() {
		Collection<Measure> measures = Arrays.asList(createDefaultMeasure(1),
				createDefaultMeasure(2),
				createDefaultMeasure(3));
		when(measureDao.getAllMeasure()).thenReturn(measures);
		Collection<Measure> result = dealNoteRestService.getAllMeasures();
		assertEquals(measures.size(), result.size());
	}

	@Test
	public void testGetAllRoutes() {
		Collection<Route> routes = Arrays.asList(createDefaultRoute(1),
				createDefaultRoute(2),
				createDefaultRoute(3));
		when(routeDao.getRoutes()).thenReturn(routes);
		Collection<Route> result = dealNoteRestService.getAllRoutes();
		assertEquals(routes.size(), result.size());
	}

	@Test
	public void testGetGoodsByAgent() {
		Collection<Goods> goodsList = Arrays.asList(createDefaultGoods(1, null),
				createDefaultGoods(2, null),
				createDefaultGoods(3, null));
		when(goodsDao.getGoodsByAgent(testAgent))
		.thenReturn(goodsList);
		Collection<Goods> result = dealNoteRestService.getGoodsByAgent(testAgent);
		assertEquals(goodsList.size(), result.size());
	}

	@Test
	public void testGetAgentById() {
		when(agentDao.getAgentById(DEFAULT_AGENT_ID))
		.thenReturn(testAgent);
		assertSame(dealNoteRestService.getAgentById(DEFAULT_AGENT_ID), 
				testAgent);
	}

	@Test
	public void testGetAgentGoodsByAgent() {
		Collection<AgentGoods> goodsList = Arrays.asList(createDefaultAgentGoods(1, testAgent, null),
				createDefaultAgentGoods(2, testAgent, null),
				createDefaultAgentGoods(3, testAgent, null));
		when(agentGoodsDao.getAgentGoodsByAgent(testAgent)).thenReturn(goodsList);
		assertEquals(goodsList.size(),
				dealNoteRestService.getAgentGoodsByAgent(testAgent).size());
	}

	@Test
	public void testGetAllPriorityColor() {
		Collection<PriorityColor> colors = Arrays.asList(createDefaultPriorityColor(1),
				createDefaultPriorityColor(2),
				createDefaultPriorityColor(3));
		when(priorityColorDao.getAllPriorityColors()).thenReturn(colors);
		assertEquals(colors.size(),
				dealNoteRestService.getAllPriorityColor().size());		
	}

	@Test
	public void testGetMeasureLinkByAgent() {
		Collection<MeasureLink> measures = Arrays.asList(createDefaultMeasureLink(1, null, null, null),
				createDefaultMeasureLink(2, null, null, null),
				createDefaultMeasureLink(3, null, null, null));
		when(measureLinkDao.getMeasureLinkByAgent(testAgent)).thenReturn(measures);
		assertEquals(measures.size(), 
				dealNoteRestService.getMeasureLinkByAgent(testAgent).size());
	}

	@Test
	public void testSaveLocation() {
		Location loc = createDefaultLocation(1, testAgent);
		dealNoteRestService.saveLocation(loc);
		verify(locationDao).save(loc);
	}

	@Test
	public void testSaveLocations() {
		Collection<Location> locations = Arrays.asList(createDefaultLocation(1, testAgent), 
				createDefaultLocation(2, testAgent),
				createDefaultLocation(3, testAgent));
		dealNoteRestService.saveLocations(locations);
		verify(locationDao, times(3)).save(any(Location.class));		
	}

	@Test
	public void testDeleteLocation() {
		Location loc = createDefaultLocation(1, testAgent);
		dealNoteRestService.deleteLocation(loc);
		verify(locationDao).delete(loc);
	}

	@Test
	public void testSavePriorityColor() {
		PriorityColor color = createDefaultPriorityColor(1);
		dealNoteRestService.save(color);
		verify(priorityColorDao).save(color);
	}

	@Test
	public void testSaveDocument() {
		Document doc = createDefaultDocument(1);
		dealNoteRestService.save(doc);
		verify(documentDao).save(doc);
	}
	
	@Test
	public void testGetWsServers(){
		Collection<WsServer> servers = Arrays.asList(createDefaultWsServer(1),
				createDefaultWsServer(2),
				createDefaultWsServer(3));
		when(wsServerDao.getServers()).thenReturn(servers);
		assertEquals(servers.size(), 
				dealNoteRestService.getWsServers().size());
	}
	
	@Test
	public void testGetPayForms(){
		Collection<PayForm> forms = Arrays.asList(createDefaultPayForm(1),
				createDefaultPayForm(2),
				createDefaultPayForm(3));
		when(payFormDao.getAllPayForms()).thenReturn(forms);
		assertEquals(forms.size(), dealNoteRestService.getPayForms().size());
	}
	
	@Test
	public void testGetDocTypes(){
		Collection<DocType> types = Arrays.asList(createDefaultDocType(1),
				createDefaultDocType(2),
				createDefaultDocType(3));
		when(docTypeDao.getAllDocTypes()).thenReturn(types);
		assertEquals(types.size(), dealNoteRestService.getDocTypes().size());
	}
}

package biz.dealnote.web.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.datatable.AgentJQueryDataTable;
import biz.dealnote.web.model.datatable.ClientsJQueryDataTable;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class DealNoteServiceTest {

	@Autowired
	private DealNoteService dealNoteService;
	
	@Test
	public void testDealNoteService(){
		assertNotNull(dealNoteService);
	}
	
	@Test
	public void testGetAgentsList(){
		Collection<Agent> agents = dealNoteService.getAgentsList();
		assertEquals(3, agents.size());
	}	
	
	@Test
	public void testGetAgentById() {
		Agent agent = dealNoteService.getAgentById(444);
		assertEquals(new Integer(581), agent.getOuterId());
	}	
	
	@Rollback(true)
	@Transactional
	@Test
	public void testSaveAgent() {
		Agent agent = createAgent(1);
		dealNoteService.saveAgent(agent);
		assertEquals(4, dealNoteService.getAgentsList().size());
	}
	
	@Rollback(true)
	@Transactional
	@Test
	public void testDeleteAgentById(){
		dealNoteService.deleteAgentById(496);
		assertEquals(2, dealNoteService.getAgentsList().size());
	}

	@Test
	public void testGetClientsByAgent() {
		Collection<Client> clients = dealNoteService.getClientsByAgent(444);
		assertEquals(10, clients.size());
	}

	@Transactional(readOnly = true)
	@Test
	public void testGetClietnById() {
		Client client = dealNoteService.getClietnById(2);
		assertNotNull(client);
		assertEquals("Client2", client.getName());
		assertEquals(new Integer(444), client.getAgent().getId());
	}

	@Test
	public void testGetRoutes() {
		Collection<Route> routes = dealNoteService.getRoutes();
		assertEquals(4, routes.size());
	}

	@Test
	public void testGetRouteById() {
		Route route = dealNoteService.getRouteById(-1);
		assertNotNull(route);
		assertEquals("All", route.getName());
	}

	@Test
	public void testGetGroups() {
		Collection<ClientGroup> groups = dealNoteService.getGroups();
		assertEquals(3, groups.size());
	}

	@Test
	public void testGetGroupById() {
		ClientGroup group = dealNoteService.getGroupById(-1);
		assertNotNull(group);
		assertEquals("All", group.getName());
	}

	@Rollback
	@Transactional
	@Test
	public void testSaveClient() {
		Client client = createClient(1);
		dealNoteService.saveClient(client);
		assertEquals(11, dealNoteService.getClientsByAgent(444).size());
	}

	@Rollback
	@Transactional
	@Test
	public void testDeleteClientById() {
		dealNoteService.deleteClientById(2);
		assertEquals(9, dealNoteService.getClientsByAgent(444).size());
	}
	

	@Transactional(readOnly = true)
	@Test
	public void testGetAgentDataTable() {
		JQueryDataTableParamModel params = 
				createJQueryDataTableParamModel();
		DataTable dataTable = 
				new AgentJQueryDataTable(dealNoteService.getAgentsList(), 
				params);
		assertNotNull(dataTable);
		dataTable.processData();
		assertNotNull("There isn't list of agents in DataTable.", 
				dataTable.getDataTableAsJson().has("aaData"));
		JsonArray agents = dataTable.getDataTableAsJson().getAsJsonArray("aaData");
		assertEquals(3, agents.size());
	}

	@Transactional(readOnly = true)
	@Test
	public void testGetClientDataTable() {
		JQueryDataTableParamModel params = 
				createJQueryDataTableParamModel();
		DataTable dataTable = 
				new ClientsJQueryDataTable(dealNoteService.getClientsByAgent(444), 
				params);
		assertNotNull(dataTable);
		dataTable.processData();
		assertNotNull("There isn't list of clients in DataTable.", 
				dataTable.getDataTableAsJson().has("aaData"));
		JsonArray agents = dataTable.getDataTableAsJson().getAsJsonArray("aaData");
		assertEquals(10, agents.size());
	}

	private Agent createAgent(int id){
		Agent agent = new Agent();
		agent.setId(id);
		agent.setName("Agent" + id);
		agent.setActive(1);
		agent.setRoleCode(1);
		//agent.getClients().add(new Client());
		agent.setOuterId(1);
		return agent;
	}
	
	private Client createClient(int id){
		Client client = new Client();
		client.setId(id);
		client.setAgent(createAgent(444));
		client.setName("Client1");
		ClientGroup group = dealNoteService.getGroupById(-1);
		client.setGroup(group);
		client.setIsNotActive(0);
		client.setAddressLocation("addressLocation");
		return client;
	}
	
	private JQueryDataTableParamModel createJQueryDataTableParamModel(){
		JQueryDataTableParamModel params = new JQueryDataTableParamModel();
		params.setiColumns(5);
		params.setiDisplayLength(10);
		params.setiDisplayStart(0);
		params.setiSortCol_0(0);
		params.setsColumns(",,,,");
		params.setsSearch("");
		params.setsSortDir_0("asc");
		return params;
	}
}

package biz.dealnote.web.web;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.ModelAndViewAssert.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.service.DealNoteService;

public class AgentControllerTest {

	private List<Agent> agents;
	private DealNoteService dealNoteService;
	private AgentController agentController;
	
	private final int AGENT_ID = 1;

	@Before
	public void setUp() throws Exception {
		agents = new ArrayList<Agent>();
		Agent agent = createAgent(AGENT_ID);
		agents.add(agent);
		
		dealNoteService = mock(DealNoteService.class);
		agentController = new AgentController(dealNoteService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShowAgent() {
		when(dealNoteService.getAgentById(AGENT_ID)).thenReturn(agents.get(0));
		
		ModelAndView uiModel = agentController.showAgent(AGENT_ID);
		
		assertViewName(uiModel, "showAgentInfo");
		assertModelAttributeAvailable(uiModel, "agent");
		
		Agent agent = (Agent) uiModel.getModel().get("agent");
		
		assertEquals(agents.get(0).getId(), agent.getId());
	}

	@Test
	public void testInitCreationForm() {
		ModelAndView uiModel = agentController.initCreationForm();
		assertViewName(uiModel, "createOrUpdateAgentForm");
		assertModelAttributeAvailable(uiModel, "agent");
	}

	@Test
	public void testProcessCreationForm() {
		Agent agent = createAgent(2);
		BindingResult bindingResult = new BeanPropertyBindingResult(agent, "agent");
		SessionStatus sessionStatus = mock(SessionStatus.class);
		
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				agents.add(agent);
				return null;
			}
		}).when(dealNoteService).saveAgent(agent);
		
		String resultViewName = agentController.processCreationForm(agent, bindingResult, sessionStatus);
		
		assertEquals("redirect:/agents/listAgents", resultViewName);
		assertEquals(2, agents.size());
		
		agents.remove(agent);
	}

	@Test
	public void testInitUpdateAgentForm() {
		when(dealNoteService.getAgentById(AGENT_ID)).thenReturn(agents.get(0));
		ModelAndView model = agentController.initUpdateAgentForm(AGENT_ID);
		assertViewName(model, "createOrUpdateAgentForm");
	}

	@Test
	public void testProcessUpdateAgentForm() {
		Agent agent = agents.get(0);
		String name = "TestAgent";
		agent.setName(name);
		BindingResult bindingResult = new BeanPropertyBindingResult(agent, "agent");
		SessionStatus sessionStatus = mock(SessionStatus.class);
		
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				agents.set(0, agent);
				return null;
			}
		}).when(dealNoteService).saveAgent(agent);
		
		String resultViewName = agentController.processCreationForm(agent, bindingResult, sessionStatus);
		
		assertEquals("redirect:/agents/listAgents", resultViewName);
		assertEquals(name, agents.get(0).getName());
	}
/*
	@Test
	public void testInitListAgentForm() {
		HttpServletRequest httpServletRequest = new MockHttpServletRequest();
		JQueryDataTableParamModel params = new JQueryDataTableParamModel(httpServletRequest);
		params.iColumns = 5;
		params.iDisplayLength = 10;
		params.iDisplayStart = 0;
		params.iSortColumnIndex = 0;
		params.sColumns = ",,,,";
		params.sSearch = "";
		params.sSortDirection = "asc";

		AgentJQueryDataTable dataTable;
		//= mock(AgentJQueryDataTable.class);
		try {
			PowerMockito.whenNew(AgentJQueryDataTable.class)
				.withArguments(agents, params);
		} catch (Exception e) {
			fail(e.toString());
		}
		ReflectionTestUtils.setField(agentController, "dataTable", dataTable);
		doReturn(agents)
			.when(dealNoteService)
			.getAgentsList();
		String result = agentController.initListAgentForm(httpServletRequest);
		verify(dataTable).processData();
		verify(dataTable).getDataTableAsJson();
		assertNotNull(result);
	}*/

	@Test
	public void testInitShowAgents() {
		String resultViewName = agentController.initShowAgents();
		assertEquals("showAgents", resultViewName);
	}

	@Test
	public void testInitDeleteAgentForm() {
		doAnswer(new Answer() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				agents.remove(createAgent(AGENT_ID));
				return null;
			}
		}).when(dealNoteService).deleteAgentById(AGENT_ID);
		
		String resultViewName = agentController.initDeleteAgentForm(AGENT_ID);
		assertEquals(0, agents.size());
		assertEquals("redirect:/agents/listAgents", resultViewName);
	}

	private Agent createAgent(int id){
		Agent agent = new Agent();
		agent.setId(id);
		agent.setName("Agent" + id);
		agent.setActive(1);
		agent.setRoleCode(1);
		agent.getClients().add(new Client());
		agent.setOuterId(1);
		return agent;
	}
}

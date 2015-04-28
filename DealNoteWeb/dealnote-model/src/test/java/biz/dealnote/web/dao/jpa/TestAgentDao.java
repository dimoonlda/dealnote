package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.model.Agent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestAgentDao {
	
	@Autowired
	private AgentDAO agentDao;
	
	private static final String AGENT_NAME = "Test agent";
	
	@Test
	@Transactional
	public void testGetActiveAgentsList(){
		Collection<Agent> agents = agentDao.getActiveAgentsList();
		assertEquals(2, agents.size());
	}
	
	@Test
	@Transactional
	public void testGetAgentsList(){
		Collection<Agent> agents = agentDao.getAgentsList();
		assertEquals(3, agents.size());
	}
	
	@Test
	@Transactional
	public void testDeleteAgentById(){
		Agent agent = TestAgentDao.createTestAgent();
		agentDao.save(agent);
		Collection<Agent> agents = agentDao.getAgentsList();
		assertEquals(4, agents.size());
		
		int agentId = agent.getId();
		agentDao.deleteAgentById(agentId);
		
		agents = agentDao.getAgentsList();
		assertEquals(3, agents.size());
	}
	
	@Test
	@Transactional
	public void testGetAgentById(){
		assertNotNull(agentDao.getAgentById(444));
	}
	
	@Test
	@Transactional
	public void testAdd(){
		Agent agent = TestAgentDao.createTestAgent();
		
		int size = agentDao.getAgentsList().size();
		agentDao.save(agent);
		
		assertTrue(size < agentDao.getAgentsList().size());
		
		agentDao.deleteAgentById(agent.getId());
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		Agent agent = TestAgentDao.createTestAgent();
		agentDao.save(agent);
		
		agent.setName("1");
		agentDao.save(agent);
		
		Agent agentRes = agentDao.getAgentById(agent.getId());
		assertEquals("1", agentRes.getName());
		
		agentDao.deleteAgentById(agent.getId());
	}
	
	public static Agent createTestAgent(){
		Agent agent = new Agent();
		agent.setName(AGENT_NAME);
		agent.setActive(1);
		agent.setFio(AGENT_NAME);
		agent.setAdminPass("pass");
		agent.setMoneyname("money");
		agent.setMoneyformat("moneyformat");
		agent.setQtyformat("qtyformat");
		agent.setWsServiceName("wsServiceName");
		agent.setGpsByDay("gpsByDay");
		return agent;
	}
}

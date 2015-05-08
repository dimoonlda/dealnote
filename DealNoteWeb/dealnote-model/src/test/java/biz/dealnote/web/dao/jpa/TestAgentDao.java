package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;

public class TestAgentDao extends AbstractDaoJpaTest{
	
	@Autowired
	private AgentDAO agentDao;
	
	private static final String NEW_AGENT_NAME = "Test agent";
	
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
		Agent agent = DefaultObjectsFactory.createDefaultAgent(null);
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
		Agent agent = DefaultObjectsFactory.createDefaultAgent(null);
		
		int size = agentDao.getAgentsList().size();
		agentDao.save(agent);
		
		assertTrue(size < agentDao.getAgentsList().size());
		
		agentDao.deleteAgentById(agent.getId());
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		Agent agent = DefaultObjectsFactory.createDefaultAgent(null);
		agentDao.save(agent);
		
		agent.setName(NEW_AGENT_NAME);
		agentDao.save(agent);
		
		Agent agentRes = agentDao.getAgentById(agent.getId());
		assertEquals(NEW_AGENT_NAME, agentRes.getName());
		
		agentDao.deleteAgentById(agent.getId());
	}
}

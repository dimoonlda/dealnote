package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.DefaultObjectsFactory;

public class TestClientDaoJpa extends AbstractDaoJpaTest{

	private static final Integer TEST_CLIENT_GROUP_ID = 0;
	private static final Integer TEST_CLIENT_ID = 2;
	private static final String NEW_CLIENT_NAME = "test name";
	
	@Autowired
	private ClientDAO clientDao;
	@Autowired
	private AgentDAO agentDao;
	@Autowired
	private ClientGroupDao clientGroupDao;
	
	private Agent agent;
	private ClientGroup clientGroup;
	
	@Before
	public void initBefore(){
		agent = agentDao.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID);
		assertNotNull(agent);
		clientGroup = clientGroupDao.getGroupById(TEST_CLIENT_GROUP_ID);
		assertNotNull(clientGroup);
	}
	
	@Test
	public void testGetClientsByAgent() {
		assertEquals(10, clientDao.getClientsByAgent(agent.getId()).size());
	}

	@Test
	public void testGetClietnById() {
		assertNotNull(clientDao.getClietnById(TEST_CLIENT_ID));
	}

	@Test
	public void testSave() {
		Client client = DefaultObjectsFactory
				.createDefaultClient(null, agent, clientGroup);
		clientDao.save(client);
		assertNotNull("Object wasn't saved. Id isn't created.", client.getId());
		
		client.setName(NEW_CLIENT_NAME);
		clientDao.save(client);
		
		client = clientDao.getClietnById(client.getId());
		assertEquals("Object wasn't updated.", NEW_CLIENT_NAME, client.getName());
	}

	@Test
	public void testDeleteById() {
		int size = clientDao.getClientsByAgent(agent.getId()).size();
		Client client = DefaultObjectsFactory
				.createDefaultClient(null, agent, clientGroup);
		clientDao.save(client);
		assertTrue("Object wasn't added.", 
				size < clientDao.getClientsByAgent(agent.getId()).size());
		
		clientDao.deleteById(client.getId());
		assertTrue("Object wasn't removed.", 
				size == clientDao.getClientsByAgent(agent.getId()).size());
	}
}

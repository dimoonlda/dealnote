package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.GoodsGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestClientDaoJpa {

	private static final Integer TEST_AGENT_ID = 444;
	private static final Integer TEST_CLIENT_GROUP_ID = 0;
	private static final Integer TEST_CLIENT_ID = 2;
	private static final String TEST_CLIENT_NAME = "test name";
	private static final String TEST_CLIENT_ADDRESS_LOCATION = "test address";
	
	@Autowired
	private ClientDAO clientDao;
	@Autowired
	private AgentDAO agentDao;
	@Autowired
	private ClientGroupDao clientGroup;
	
	@Test
	@Transactional
	public void testGetClientsByAgent() {
		assertEquals(10, clientDao.getClientsByAgent(TEST_AGENT_ID).size());
	}

	@Test
	@Transactional
	public void testGetClietnById() {
		assertNotNull(clientDao.getClietnById(TEST_CLIENT_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		Client client = createTestClient();
		clientDao.save(client);
		assertNotNull("Object wasn't saved. Id isn't created.", client.getId());
		
		client.setName(TEST_CLIENT_NAME);
		clientDao.save(client);
		
		client = clientDao.getClietnById(client.getId());
		assertEquals("Object wasn't updated.", TEST_CLIENT_NAME, client.getName());
		
		clientDao.deleteById(client.getId());
	}

	@Test
	@Transactional
	public void testDeleteById() {
		int size = clientDao.getClientsByAgent(TEST_AGENT_ID).size();
		Client client = createTestClient();
		clientDao.save(client);
		assertTrue("Object wasn't added.", 
				size < clientDao.getClientsByAgent(TEST_AGENT_ID).size());
		
		clientDao.deleteById(client.getId());
		assertTrue("Object wasn't removed.", 
				size == clientDao.getClientsByAgent(TEST_AGENT_ID).size());
	}

	public Client createTestClient(){
		Client test = new Client();
		test.setAddressLocation(TEST_CLIENT_ADDRESS_LOCATION);
		test.setAgent(agentDao.getAgentById(TEST_AGENT_ID));
		test.setGroup(clientGroup.getGroupById(TEST_CLIENT_GROUP_ID));
		test.setIsNotActive(0);
		test.setName(TEST_CLIENT_NAME);
		return test;
	}
}

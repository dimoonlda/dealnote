package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.ServiceClientDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.ServiceClient;

public class ServiceClientDaoJpatest extends AbstractDaoJpaTest{

	private final static Integer TEST_ID = 1;
	private final static String TEST_CLIENT_NAME = "test";
	
	@Autowired
	private ServiceClientDao serviceClientDao;
	
	@Test
	public void testGetServiceClients() {
		assertEquals(3, serviceClientDao.getServiceClients().size());
	}

	@Test
	public void testGetServiceClientById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_ID),
				serviceClientDao.getServiceClientById(TEST_ID));
	}

	@Test
	public void testSave() {
		ServiceClient client = DefaultObjectsFactory.createDefaultServiceClient(null);
		serviceClientDao.save(client);
		assertNotNull("Object wasn't saved. Id isn't created.", client.getId());
		
		client.setName(TEST_CLIENT_NAME);
		serviceClientDao.save(client);
		
		client = serviceClientDao.getServiceClientById(client.getId());
		assertEquals("Object wasn't updated.", TEST_CLIENT_NAME, client.getName());
	}

	@Test
	public void testDelete() {
		ServiceClient client = DefaultObjectsFactory.createDefaultServiceClient(null);
		int size = serviceClientDao.getServiceClients().size();
		serviceClientDao.save(client);
		assertTrue("Object wasn't added.", 
				size < serviceClientDao.getServiceClients().size());
		
		serviceClientDao.delete(client);
		assertTrue("Object wasn't removed.", 
				size == serviceClientDao.getServiceClients().size());
	}

}

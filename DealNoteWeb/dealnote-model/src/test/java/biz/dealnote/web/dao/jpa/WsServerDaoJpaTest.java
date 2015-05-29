package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.WsServerDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.WsServer;

public class WsServerDaoJpaTest extends AbstractDaoJpaTest {

	private static final Integer TEST_SERVER_ID = 1;
	private static final String TEST_SERVER_ADDRESS = "TESTADDRESS";
	
	@Autowired
	private WsServerDao wsServerDao;
	
	@Test
	public void testGetServers() {
		assertEquals(2, wsServerDao.getServers().size());
	}

	@Test
	public void testGetServerById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_SERVER_ID),
				wsServerDao.getServerById(TEST_SERVER_ID));
	}

	@Test
	public void testSave() {
		WsServer server = DefaultObjectsFactory.createDefaultWsServer(null);
		wsServerDao.save(server);
		assertNotNull("Object wasn't saved. Id isn't created.", server.getId());
		
		server.setServerAddress(TEST_SERVER_ADDRESS);
		wsServerDao.save(server);
		
		server = wsServerDao.getServerById(server.getId()).get();
		assertEquals("Object wasn't updated.", TEST_SERVER_ADDRESS, server.getServerAddress());
	}

	@Test
	public void testDelete() {
		WsServer server = DefaultObjectsFactory.createDefaultWsServer(null);
		int size = wsServerDao.getServers().size();
		wsServerDao.save(server);
		assertTrue("Object wasn't added.", 
				size < wsServerDao.getServers().size());
		
		wsServerDao.delete(server);
		assertTrue("Object wasn't removed.", 
				size == wsServerDao.getServers().size());
	}

}

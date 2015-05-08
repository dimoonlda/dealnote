package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Location;

public class TestLocationDaoJpa extends AbstractDaoJpaTest{

	//private static final Integer TEST_ID = 1;
	private static final DateTime TEST_DATE = new DateTime(2013, 8, 20, 0, 0);
	private static final Integer TEST_AGENT_ID = 444;
	private static final String NEW_PROVIDER = "Test";
	
	private Agent TEST_AGENT;
	
	@Autowired
	private LocationDAO locationDao;
	
	@Autowired
	private AgentDAO agentDao;
	
	@Before
	public void initBefore(){
		TEST_AGENT = agentDao.getAgentById(TEST_AGENT_ID);
		assertNotNull(TEST_AGENT);
	}
	
	@Test
	public void testGetLocationListIntegerDateTime() {
		assertEquals(5, locationDao.getLocationList(TEST_AGENT.getId(), 
				TestLocationDaoJpa.TEST_DATE).size());
	}

	@Test
	public void testGetLocationListIntegerDateTimeDateTime() {
		assertEquals(5, locationDao.getLocationList(TEST_AGENT.getId(), 
				TestLocationDaoJpa.TEST_DATE, TestLocationDaoJpa.TEST_DATE).size());
	}

	@Test
	public void testSave() {
		
		Location loc = DefaultObjectsFactory.createDefaultLocation(null, TEST_AGENT);
		locationDao.save(loc);
		assertNotNull(loc.getId());
		
		loc.setProvider(NEW_PROVIDER);
		locationDao.save(loc);
		
		locationDao.delete(loc);
	}

	@Test
	public void testDelete() {
		int size = locationDao.getLocationList(TEST_AGENT.getId(), 
				TestLocationDaoJpa.TEST_DATE).size();
		Location loc = DefaultObjectsFactory.createDefaultLocation(null, TEST_AGENT);
		locationDao.save(loc);
		assertTrue(size < locationDao.getLocationList(TEST_AGENT.getId(), 
				TestLocationDaoJpa.TEST_DATE).size());
		
		locationDao.delete(loc);
		assertTrue(size == locationDao.getLocationList(TEST_AGENT.getId(), 
				TestLocationDaoJpa.TEST_DATE).size());
	}
}

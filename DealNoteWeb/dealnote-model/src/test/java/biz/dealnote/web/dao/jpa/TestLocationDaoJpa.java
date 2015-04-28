package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestLocationDaoJpa {

	private static final Integer TEST_ID = 1;
	private static final DateTime TEST_DATE = new DateTime(2013, 8, 20, 0, 0);
	private static final Integer TEST_AGENT_ID = 444;
	private static final String TEST_PROVIDER = "Test";
	
	@Autowired
	private LocationDAO locationDao;
	
	@Autowired
	private AgentDAO agentDao;
	
	@Test
	@Transactional
	public void testGetLocationListIntegerDateTime() {
		assertEquals(5, locationDao.getLocationList(TestLocationDaoJpa.TEST_AGENT_ID, 
				TestLocationDaoJpa.TEST_DATE).size());
	}

	@Test
	@Transactional
	public void testGetLocationListIntegerDateTimeDateTime() {
		assertEquals(5, locationDao.getLocationList(TestLocationDaoJpa.TEST_AGENT_ID, 
				TestLocationDaoJpa.TEST_DATE, TestLocationDaoJpa.TEST_DATE).size());
	}

	@Test
	@Transactional
	public void testSave() {
		Location loc = createTestLocation();
		locationDao.save(loc);
		assertNotNull(loc.getId());
		
		loc.setProvider(TestLocationDaoJpa.TEST_PROVIDER);
		locationDao.save(loc);
		
		locationDao.delete(loc);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = locationDao.getLocationList(TestLocationDaoJpa.TEST_AGENT_ID, 
				TestLocationDaoJpa.TEST_DATE).size();
		Location loc = createTestLocation();
		locationDao.save(loc);
		assertTrue(size < locationDao.getLocationList(TestLocationDaoJpa.TEST_AGENT_ID, 
				TestLocationDaoJpa.TEST_DATE).size());
		
		locationDao.delete(loc);
		assertTrue(size == locationDao.getLocationList(TestLocationDaoJpa.TEST_AGENT_ID, 
				TestLocationDaoJpa.TEST_DATE).size());
	}

	public Location createTestLocation(){
		Location loc = new Location();
		loc.setCreationDate(TestLocationDaoJpa.TEST_DATE);
		loc.setAgent(agentDao.getAgentById(TestLocationDaoJpa.TEST_AGENT_ID));
		return loc;
	}
}

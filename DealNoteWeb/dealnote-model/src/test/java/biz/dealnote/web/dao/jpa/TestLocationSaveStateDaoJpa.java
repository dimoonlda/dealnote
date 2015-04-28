package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.LocationSaveStateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestLocationSaveStateDaoJpa {

	private static final Integer TEST_ID = 1;
	
	@Autowired
	private LocationSaveStateDao locationSaveStateDao;
	
	@Test
	@Transactional
	public void testGetStateById() {
		assertNotNull(locationSaveStateDao
				.getStateById(TestLocationSaveStateDaoJpa.TEST_ID));
	}

}

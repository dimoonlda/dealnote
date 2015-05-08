package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.LocationSaveStateDao;

public class TestLocationSaveStateDaoJpa extends AbstractDaoJpaTest{

	private static final Integer TEST_ID = 1;
	
	@Autowired
	private LocationSaveStateDao locationSaveStateDao;
	
	@Test
	public void testGetStateById() {
		assertNotNull(locationSaveStateDao
				.getStateById(TestLocationSaveStateDaoJpa.TEST_ID));
	}

}

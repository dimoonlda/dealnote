package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.SystemSetsDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.SystemSets;

public class SystemSetsDaoJpaTest  extends AbstractDaoJpaTest{

	private static final Integer TEST_ID = 1;
	private static final Integer TEST_MOBILE_SWAP_VERSION = 1;
	
	@Autowired
	private SystemSetsDao systemSetsDao;
	
	@Test
	public void testGetSystemSetsById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_ID),
				systemSetsDao.getSystemSetsById(TEST_ID));
	}

	@Test
	public void testSave() {
		SystemSets sets = DefaultObjectsFactory.createDefaultSystemSets(null);
		systemSetsDao.save(sets);
		assertNotNull("Object wasn't saved. Id isn't created.", sets.getId());
		
		sets.setMobileSwapVersion(TEST_MOBILE_SWAP_VERSION);
		systemSetsDao.save(sets);
		
		sets = systemSetsDao.getSystemSetsById(sets.getId());
		assertEquals("Object wasn't updated.", TEST_MOBILE_SWAP_VERSION, sets.getMobileSwapVersion());
	}

}

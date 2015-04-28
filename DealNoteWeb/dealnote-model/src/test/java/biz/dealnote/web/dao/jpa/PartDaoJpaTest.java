package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.PartDao;
import biz.dealnote.web.model.Part;

public class PartDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_PART_ID = 1010;
	private static final Integer TEST_PARENT_ID = 1000;
	private static final String TEST_PART_NAME = "Test Part";
	private static final String TEST_PART_ROLE_NAME = "TESTROLE";
	private static final Integer TEST_SORTPOS = 1000;
	
	@Autowired
	private PartDao partDao;
	
	@Test
	public void testGetParts() {
		assertEquals(4, partDao.getParts().size());
	}

	@Test
	public void testGetPartById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_PART_ID),
				partDao.getPartById(TEST_PART_ID));
	}

	@Test
	public void testSave() {
		Part part = createTestPart();
		partDao.save(part);
		assertNotNull("Object wasn't saved. Id isn't created.", part.getId());
		
		part.setSortPos(TEST_SORTPOS);
		partDao.save(part);
		
		part = partDao.getPartById(part.getId());
		assertEquals("Object wasn't updated.", TEST_SORTPOS, part.getSortPos());
		assertNotNull("Parent wasn't set up.", part.getParent());
		
		partDao.delete(part);
	}

	@Test
	public void testDelete() {
		Part part = createTestPart();
		int size = partDao.getParts().size();
		partDao.save(part);
		assertTrue("Object wasn't added.", 
				size < partDao.getParts().size());
		
		partDao.delete(part);
		assertTrue("Object wasn't removed.", 
				size == partDao.getParts().size());
	}

	public Part createTestPart(){
		Part test = new Part();
		test.setName(TEST_PART_NAME);
		test.setRoleName(TEST_PART_ROLE_NAME);
		partDao.getPartById(TEST_PARENT_ID).addPart(test);
		return test;
	}
}

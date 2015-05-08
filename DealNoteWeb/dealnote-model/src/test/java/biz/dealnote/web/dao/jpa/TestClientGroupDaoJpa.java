package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.DefaultObjectsFactory;

public class TestClientGroupDaoJpa extends AbstractDaoJpaTest{

	private static final Integer TEST_GROUP_ID = 0;
	private static final String NEW_GROUP_NAME = "Test name";
	
	@Autowired
	private ClientGroupDao clientGroupDao;
	
	@Test
	public void testGetGroups() {
		assertEquals(3, clientGroupDao.getGroups().size());
	}

	@Test
	public void testGetGroupById() {
		assertNotNull(clientGroupDao.getGroupById(TEST_GROUP_ID));
	}
	
	@Test
	public void testSave() {
		ClientGroup group = DefaultObjectsFactory.createTestClientGroup(null);
		clientGroupDao.save(group);
		assertNotNull("Object wasn't saved. Id isn't created.", group.getId());
		
		group.setName(NEW_GROUP_NAME);
		clientGroupDao.save(group);
		
		group = clientGroupDao.getGroupById(group.getId());
		assertEquals("Object wasn't updated.", NEW_GROUP_NAME, group.getName());
		
		clientGroupDao.delete(group);
	}

	@Test
	public void testDelete() {
		int size = clientGroupDao.getGroups().size();
		ClientGroup group = DefaultObjectsFactory.createTestClientGroup(null);
		clientGroupDao.save(group);
		assertTrue("Object wasn't added.", 
				size < clientGroupDao.getGroups().size());
		
		clientGroupDao.delete(group);
		assertTrue("Object wasn't removed.", 
				size == clientGroupDao.getGroups().size());
	}
}

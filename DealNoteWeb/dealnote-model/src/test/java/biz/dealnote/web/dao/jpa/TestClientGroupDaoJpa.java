package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.model.ClientGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestClientGroupDaoJpa {

	private static final Integer TEST_GROUP_ID = 0;
	private static final Integer TEST_GROUP_OUTER_ID = 0;
	private static final String TEST_GROUP_NAME = "Test name";
	
	@Autowired
	private ClientGroupDao clientGroupDao;
	
	@Test
	@Transactional
	public void testGetGroups() {
		assertEquals(3, clientGroupDao.getGroups().size());
	}

	@Test
	@Transactional
	public void testGetGroupById() {
		assertNotNull(clientGroupDao.getGroupById(TEST_GROUP_ID));
	}
	
	@Test
	@Transactional
	public void testSave() {
		ClientGroup group = createTestClientGroup();
		clientGroupDao.save(group);
		assertNotNull("Object wasn't saved. Id isn't created.", group.getId());
		
		group.setName(TEST_GROUP_NAME);
		clientGroupDao.save(group);
		
		group = clientGroupDao.getGroupById(group.getId());
		assertEquals("Object wasn't updated.", TEST_GROUP_NAME, group.getName());
		
		clientGroupDao.delete(group);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = clientGroupDao.getGroups().size();
		ClientGroup group = createTestClientGroup();
		clientGroupDao.save(group);
		assertTrue("Object wasn't added.", 
				size < clientGroupDao.getGroups().size());
		
		clientGroupDao.delete(group);
		assertTrue("Object wasn't removed.", 
				size == clientGroupDao.getGroups().size());
	}

	public ClientGroup createTestClientGroup(){
		ClientGroup test = new ClientGroup();
		test.setName(TEST_GROUP_NAME);
		test.setOuterId(TEST_GROUP_OUTER_ID);
		return test;
	}
}

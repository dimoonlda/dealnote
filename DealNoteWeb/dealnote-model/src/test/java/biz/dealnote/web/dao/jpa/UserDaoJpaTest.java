package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.User;

public class UserDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_USER_ID = 100;
	private static final String TEST_USER_FIO = "testFIO";
	private static final String TEST_USER_NAME = "DEFAULT_AGENT";
	private static final String TEST_NEW_USER_NAME = "testNAME";
	private static final String TEST_USER_PASSWD = "testPASS";
	private static final Short TEST_USER_ACTIVE = 1;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testGetUsers() {
		assertEquals(3, userDao.getUsers().size());
	}

	@Test
	public void testGetUserById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_USER_ID),
				userDao.getUserById(TEST_USER_ID));
	}

	@Test
	public void testSave() {
		User user = createTestUser();
		userDao.save(user);
		assertNotNull("Object wasn't saved. Id isn't created.", user.getId());
		
		user.setFio(TEST_USER_FIO);
		userDao.save(user);
		
		user = userDao.getUserById(user.getId());
		assertEquals("Object wasn't updated.", TEST_USER_FIO, user.getFio());
		
		userDao.delete(user);
	}

	@Test
	public void testDelete() {
		User user = createTestUser();
		int size = userDao.getUsers().size();
		userDao.save(user);
		assertTrue("Object wasn't added.", 
				size < userDao.getUsers().size());
		
		userDao.delete(user);
		assertTrue("Object wasn't removed.", 
				size == userDao.getUsers().size());
	}

	@Test
	public void testGetUserByName(){
		assertNotNull(String.format("Couldn't find user with name = %s", TEST_USER_NAME), 
				userDao.getUserByName(TEST_USER_NAME).get());
	}
	
	public static User createTestUser(){
		User test = new User();
		test.setActive(TEST_USER_ACTIVE);
		test.setName(TEST_NEW_USER_NAME);
		test.setPasswd(TEST_USER_PASSWD);
		return test;
	}
}

package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.User;

public class UserDaoJpaTest extends AbstractDaoJpaTest{
	
	private static final String EXISTED_USER_NAME = "DEFAULT_AGENT";
	private static final String NEW_USER_FIO = "New user fio";
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testGetUsers() {
		assertEquals(3, userDao.getUsers().size());
	}

	@Test
	public void testGetUserById() {
		assertNotNull(String.format("Couldn't find object with id = %d", DefaultObjectsFactory.DEFAULT_USER_ID),
				userDao.getUserById(DefaultObjectsFactory.DEFAULT_USER_ID));
	}

	@Test
	public void testSave() {
		User user = DefaultObjectsFactory.createDefaultUser(null);
		userDao.save(user);
		assertNotNull("Object wasn't saved. Id isn't created.", user.getId());
		
		user.setFio(NEW_USER_FIO);
		userDao.save(user);
		
		user = userDao.getUserById(user.getId());
		assertEquals("Object wasn't updated.", 
				NEW_USER_FIO, 
				user.getFio());
		
		userDao.delete(user);
	}

	@Test
	public void testDelete() {
		User user = DefaultObjectsFactory.createDefaultUser(null);
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
		assertNotNull(String.format("Couldn't find user with name = %s", EXISTED_USER_NAME), 
				userDao.getUserByName(EXISTED_USER_NAME).get());
	}
}

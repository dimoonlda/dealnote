package biz.dealnote.rest.security;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.User;

public class DealNoteUserDetailServiceTest{

	@Mock
	private UserDao userDao;
	private final DealNoteUserDetailService service = new DealNoteUserDetailService();
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		service.setUserDao(userDao);
	}
	
	@Test
	public void testLoadUserByUsername() {
		User user = DefaultObjectsFactory.createDefaultUser(1);
		when(userDao.getUserByName(DefaultObjectsFactory.DEFAULT_NAME))
		.thenReturn(Optional.of(user));
		Object result = service.loadUserByUsername(DefaultObjectsFactory.DEFAULT_NAME);
		assertTrue(result instanceof UserContext);
	}

}

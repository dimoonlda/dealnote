package biz.dealnote.rest.security;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import biz.dealnote.rest.AbstractTest;
import biz.dealnote.web.dao.UserDao;
import static biz.dealnote.web.model.DefaultObjectsFactory.*;
import biz.dealnote.web.model.DeviceSerialNumber;
import biz.dealnote.web.model.User;

public class RestAuthenticationServiceTest extends AbstractTest{

	@Mock
	private UserDao userDao;
	
	@Mock
	private AuthenticationManager authenticationManager;
	private User user;
	
	private RestAuthenticationService service;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		service = new RestAuthenticationService(authenticationManager);
		service.setUserDao(userDao);
		user = createDefaultUser(1);
	}
	
	@Test
	@Ignore
	public void testRestAuthenticationServiceWhenUserHasSerialNumber() {
		DeviceSerialNumber sn = createDefaultDeviceSn(1, user);
		user.addDeviceSerialNumber(sn);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), user.getPasswd());
		when(authentication.getPrincipal()).thenReturn(new Object());
		when(userDao.getUserByName(anyString())).thenReturn(Optional.of(user));
		when(authenticationManager.authenticate(anyObject())).thenReturn(authentication);
		
		assertTrue(service.authenticateAndCheckSerialNumber(user.getName(), user.getPasswd(), sn.getSerialNumber()));
	}

	@Test
	public void testAuthenticateAndCheckSerialNumber() {
		//fail("Not yet implemented");
	}

}

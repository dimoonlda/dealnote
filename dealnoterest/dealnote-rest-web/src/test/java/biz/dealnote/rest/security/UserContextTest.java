package biz.dealnote.rest.security;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import static biz.dealnote.web.model.DefaultObjectsFactory.*;
import biz.dealnote.web.model.Job;
import biz.dealnote.web.model.Part;
import biz.dealnote.web.model.PartJob;
import biz.dealnote.web.model.User;

public class UserContextTest {

	@Mock
	private User user;
	private UserContext userContext;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		userContext = new UserContext(user);
	}
	

	@Test
	public void testGetAuthorities() {
		Job job = createDefaultJob(1);
		Part part = createDefaultPart(1, null);
		PartJob partJob = createDefaultPartJob(1, job, part, user);
		when(user.getPartJobs()).thenReturn(Arrays.asList(partJob));
		assertSame(1, userContext.getAuthorities().size());
	}

	@Test
	public void testGetPassword() {
		when(user.getPasswd()).thenReturn("passwd");
		assertEquals("passwd", userContext.getPassword());
	}

	@Test
	public void testGetUsername() {
		when(user.getName()).thenReturn("name");
		assertEquals("name", userContext.getUsername());
	}

	@Test
	public void testIsAccountNonExpired() {
		when(user.isActive()).thenReturn(true);
		assertSame(true, userContext.isAccountNonExpired());
	}

	@Test
	public void testIsAccountNonLocked() {
		when(user.isActive()).thenReturn(true);
		assertSame(true, userContext.isAccountNonLocked());
	}

	@Test
	public void testIsCredentialsNonExpired() {
		when(user.isActive()).thenReturn(true);
		assertSame(true, userContext.isCredentialsNonExpired());
	}

	@Test
	public void testIsEnabled() {
		when(user.isActive()).thenReturn(true);
		assertSame(true, userContext.isEnabled());
	}
}

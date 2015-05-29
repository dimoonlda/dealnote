package biz.dealnote.rest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationEntryPointTest {
	
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
    @Mock
	private AuthenticationException authException;
	
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint = 
    		new CustomAuthenticationEntryPoint();
    
    @Before
    public void init(){
    	MockitoAnnotations.initMocks(this);
    }
    
    @Test
    @Ignore
	public void testCommence() throws IOException, ServletException {
    	customAuthenticationEntryPoint.commence(request, response, authException);
    	verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, 
    			"Unauthorized: Authentication token was either missing or invalid.");
	}

}

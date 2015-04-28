package biz.dealnote.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class ClientAuthenticationFilter extends GenericFilterBean {
	
	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";
	private static final String HEADER_SN = "X-Sn";
	
	private final AuthenticationService authenticationService;

	public ClientAuthenticationFilter(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtered. ");
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpServletRequest request = (HttpServletRequest)req;
		String passwd = request.getHeader(HEADER_PASSWORD);
		String userName = request.getHeader(HEADER_USERNAME);
		String serialNumber = request.getHeader(HEADER_SN);
		
		System.out.println(HEADER_USERNAME +": " + userName);
		System.out.println(HEADER_PASSWORD +": " + passwd);
		System.out.println(HEADER_SN +": " + serialNumber);
		
		if(passwd == null 
				|| userName == null 
				|| serialNumber == null 
				|| !authenticationService
					.authenticateAndCheckSerialNumber(userName, passwd, serialNumber)){
			response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid." );
		}else{
			chain.doFilter(request, response);
		}
	}

}

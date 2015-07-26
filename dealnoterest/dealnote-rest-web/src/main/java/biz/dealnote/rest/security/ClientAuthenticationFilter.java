package biz.dealnote.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class ClientAuthenticationFilter extends GenericFilterBean {
	
	private static final String HEADER_USERNAME = "X-Username";
	private static final String HEADER_PASSWORD = "X-Password";
	private static final String HEADER_SN = "X-Sn";
	private static Logger filterLogger = Logger.getLogger(ClientAuthenticationFilter.class);
	
	private final AuthenticationService authenticationService;

	public ClientAuthenticationFilter(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpServletRequest request = (HttpServletRequest)req;
		String passwd = request.getHeader(HEADER_PASSWORD);
		String userName = request.getHeader(HEADER_USERNAME);
		String serialNumber = request.getHeader(HEADER_SN);
		
		if(passwd == null 
				|| userName == null 
				|| serialNumber == null 
				|| !authenticationService
					.authenticateAndCheckSerialNumber(userName, passwd, serialNumber)){
			filterLogger.error(String.format("User %s and sn = %s is not authenticated.", userName, serialNumber));
			response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication pair of login and password were missing or invalid." );
		}else{
			filterLogger.info(String.format("User %s and sn = %s is authenticated.", userName, serialNumber));
			chain.doFilter(request, response);
		}
	}

}

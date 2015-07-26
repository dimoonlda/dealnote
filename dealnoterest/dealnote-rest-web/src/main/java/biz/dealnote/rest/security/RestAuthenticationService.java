package biz.dealnote.rest.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.UserDao;

public class RestAuthenticationService implements AuthenticationService {


	private UserDao userDao;

	private AuthenticationManager authenticationManager;

	private static Logger logger = Logger.getLogger(RestAuthenticationService.class);

	@Override
	@Transactional
	public boolean authenticateAndCheckSerialNumber(String userName,
			String passwd, String serialNumber) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(userName, passwd);
		try {
			authentication = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (authentication.getPrincipal() != null) {
				//UserDetails userContext = (UserDetails) authentication.getPrincipal();
				return userDao.getUserByName(userName)
						.orElseThrow(NullPointerException::new)
						.getSerialNumbers().stream()
						.filter((sn) -> sn.getSerialNumber().equals(serialNumber))
						.count() > 0;
			}
		} catch (Exception e) {
			logger.error(String.format("User authentication error: login = %s", userName), e);
		}
		return false;
	}

	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
}

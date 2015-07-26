package biz.dealnote.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.User;
import org.springframework.stereotype.Service;

@Service
public class DealNoteUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
		return new UserContext(user);
	}

	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
}

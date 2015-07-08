package testrest;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public User getUser() {
		return new User("DiMoon", 1979, "password");
	}

}

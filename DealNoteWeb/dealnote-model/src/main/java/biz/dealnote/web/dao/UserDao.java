package biz.dealnote.web.dao;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.web.model.User;

public interface UserDao {
	public Collection<User> getUsers();
	public User getUserById(Integer id);
	public Optional<User> getUserByName(String userName);
	public void save(User user);
	public void delete(User user);
}

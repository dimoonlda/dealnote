package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.User;

@Repository
public class UserDaoJpa implements UserDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<User> getUsers() {
		return em.createQuery("from User u", User.class)
				.getResultList();
	}

	@Override
	public User getUserById(Integer id) {
		return em.createQuery("from User u where u.id = :id", User.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(User user) {
		if(user.isNew()){
			em.persist(user);
		}else{
			em.merge(user);
		}
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

	@Override
	public Optional<User> getUserByName(String userName) {
		return Optional.ofNullable(em.createQuery("from User u where u.name like :name", User.class)
									.setParameter("name", userName)
									.getSingleResult());
	}

}

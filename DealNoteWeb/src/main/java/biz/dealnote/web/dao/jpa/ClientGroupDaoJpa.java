package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.model.ClientGroup;

@Repository
public class ClientGroupDaoJpa implements ClientGroupDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<ClientGroup> getGroups() {
		return this.em.createQuery("from ClientGroup g")
				.getResultList();
	}

	@Override
	public ClientGroup getGroupById(int groupId) {
		return (ClientGroup) this.em
				.createQuery("from ClientGroup g where g.id=:id")
				.setParameter("id", groupId)
				.getSingleResult();
	}

}

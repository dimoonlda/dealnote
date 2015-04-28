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
		return this.em.createQuery("from ClientGroup g", ClientGroup.class)
				.getResultList();
	}

	@Override
	public ClientGroup getGroupById(int groupId) {
		return (ClientGroup) this.em
				.createQuery("from ClientGroup g where g.id=:id")
				.setParameter("id", groupId)
				.getSingleResult();
	}

	@Override
	public void save(ClientGroup group) {
		if(group.getId()==null){
			em.persist(group);
		}else{
			em.merge(group);
		}		
	}

	@Override
	public void delete(ClientGroup group) {
		em.remove(group);		
	}

}

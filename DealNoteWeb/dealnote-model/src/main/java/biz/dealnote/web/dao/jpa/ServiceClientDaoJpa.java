package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.ServiceClientDao;
import biz.dealnote.web.model.ServiceClient;

@Repository
public class ServiceClientDaoJpa implements ServiceClientDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<ServiceClient> getServiceClients() {
		return em.createQuery("from ServiceClient c", ServiceClient.class)
				.getResultList();
	}

	@Override
	public ServiceClient getServiceClientById(int clientId) {
		return em.createQuery("from ServiceClient c where c.id = :id", ServiceClient.class)
				.setParameter("id", clientId)
				.getSingleResult();
	}

	@Override
	public void save(ServiceClient client) {
		if(client.isNew()){
			em.persist(client);
		}else{
			em.merge(client);
		}
	}

	@Override
	public void delete(ServiceClient client) {
		if(client != null){
			em.remove(client);
		}
	}

	@Override
	public ServiceClient getServiceClientByTypeCode(int code) {
		return em.createQuery("from ServiceClient c where c.typeCode = :code", ServiceClient.class)
				.setParameter("code", code)
				.getSingleResult();
	}

}

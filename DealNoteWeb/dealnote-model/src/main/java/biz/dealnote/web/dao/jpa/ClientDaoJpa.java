package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.model.Client;

@Repository
public class ClientDaoJpa implements ClientDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Collection<Client> getClientsByAgent(int agentId) {
		
		return this.em.createQuery("from Client client where client.agent.id=:agentId", Client.class)
				.setParameter("agentId", agentId)
				.getResultList();
	}

	@Override
	public Client getClietnById(int clientId) {
		Client result = (Client) this.em
				.createQuery("from Client client where client.id=:id")
				.setParameter("id", clientId)
				.getSingleResult();
		
		return result;
	}

	@Override
	public void save(Client client) {
		if(client.getId()==null){
			this.em.persist(client);
		}else{
			this.em.merge(client);
		}
	}

	@Override
	public void deleteById(int clientId) {
		this.em.createQuery("delete from Client c where c.id=:id")
			.setParameter("id", clientId)
			.executeUpdate();
	}

}

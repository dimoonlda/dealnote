package biz.dealnote.web.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import biz.dealnote.web.beans.Client;
import biz.dealnote.web.dao.ClientDAO;

public class ClientDaoJpa extends BaseDaoJpa implements ClientDAO {


	public ClientDaoJpa(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Client> getClientsByAgent(int agentId) {
		
		Query q = getSession().createQuery("from Client client where client.agent.id=:agentId");
		q.setInteger("agentId", agentId);
		List<Client> result = q.list();
		return result;
	}

	@Override
	public Client getClietnById(int clientId) {
		Client result = (Client) getSession().get(Client.class, clientId);
		
		return result;
	}

}

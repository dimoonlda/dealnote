package biz.dealnote.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import biz.dealnote.web.beans.Client;

public class ClientDaoJpa implements ClientDAO {
	private DAOFactory daoFactory;
	
	public ClientDaoJpa(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Client> getClientsByAgent(int agentId) {
		Session session = daoFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Client client where client.agent.id=:agentId");
		q.setInteger("agentId", agentId);
		List<Client> result = q.list();
		tx.commit();
		session.close();
		return result;
	}

	@Override
	public Client getClietnById(int clientId) {
		Session session = daoFactory.getSession();
		session.beginTransaction();

		Client result = (Client) session.get(Client.class, clientId);
		
		session.getTransaction().commit();
		session.close();
		return result;
	}

}

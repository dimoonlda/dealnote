package biz.dealnote.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import biz.dealnote.web.beans.Client;
import biz.dealnote.web.utils.SessionUtil;

public class ClientDaoJpa implements ClientDAO {
	private DAOFactory daoFactory;
	private Session session;
	
	public ClientDaoJpa(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		session = SessionUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public List<Client> getClientsByAgent(int agentId) {
		
		Query q = session.createQuery("from Client client where client.agent.id=:agentId");
		q.setInteger("agentId", agentId);
		List<Client> result = q.list();
		return result;
	}

	@Override
	public Client getClietnById(int clientId) {
		Client result = (Client) session.get(Client.class, clientId);
		
		return result;
	}

}

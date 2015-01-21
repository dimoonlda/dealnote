package biz.dealnote.web.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;

public class TestClientDaoJpa {
	private static DAOFactory daoFactory;
	
	@BeforeClass
	public static void initData(){
		daoFactory = DAOFactory.getFactory("jpa", null);
		assertNotNull(daoFactory);
	}

	@Test
	public void testGetClientsByAgent(){
		Session session = daoFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Agent agent = new Agent();
		session.save(agent);
		int agentId = agent.getId();
		
		Client client = new Client();
		client.setAgent(agent);
		session.save(client);
		
		client = new Client();
		client.setAgent(agent);
		session.save(client);
		
		tx.commit();
		session.close();
		
		List<Client> clientList = daoFactory.getClientDAO().getClientsByAgent(agentId);
		assertEquals(2, clientList.size());
	}
	
	@Test
	public void testGetClietnById(){
		Session session = daoFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Agent agent = new Agent();
		session.save(agent);
		
		Client client = new Client();
		client.setAgent(agent);
		client.setName("test");
		session.save(client);
		int client_id = client.getId();
		
		client = new Client();
		client.setAgent(agent);
		session.save(client);
		
		tx.commit();
		session.close();
		
		client = daoFactory.getClientDAO().getClietnById(client_id);
		assertEquals("test", client.getName());
	}	
}

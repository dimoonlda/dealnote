package biz.dealnote.web.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.beans.Client;
import biz.dealnote.web.utils.SessionUtil;

public class TestAgentDaoJpa {
	private static DAOFactory daoFactory;
	
	@BeforeClass
	public static void initialize(){
		daoFactory = DAOFactory.getFactory("jpa", null);
		assertNotNull(daoFactory);
	}
	
	@Test
	public void testGetAgentsList(){
		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();
		
		Query q = SessionUtil.getSessionFactory().getCurrentSession().createQuery("delete from Agent");
		q.executeUpdate();
		
		Agent agent = new Agent();
		SessionUtil.getSessionFactory().getCurrentSession().save(agent);
		
		agent = new Agent();
		SessionUtil.getSessionFactory().getCurrentSession().save(agent);
		
		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();
		
		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();

		List<Agent> agentList = daoFactory.getAgentDAO().getAgentsList();

		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();

		assertEquals(2, agentList.size());
	}

	@Test
	public void testGetActiveAgentsList(){
		Session session = daoFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		Agent agent = new Agent();
		agent.setActive(1);
		session.save(agent);
		
		agent = new Agent();
		agent.setActive(0);
		session.save(agent);
		
		tx.commit();
		session.close();
		
		SessionUtil.getSessionFactory().getCurrentSession().beginTransaction();
		List<Agent> agentList = daoFactory.getAgentDAO().getActiveAgentsList();
		SessionUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		assertEquals(1, agentList.size());
	}
	
	@Test
	public void testGetAgentClientsCount(){
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Agent agent = new Agent();
		
		Client client = new Client();
		client.setName("client1");
		agent.getClients().add(client);
		
		client = new Client();
		client.setName("client2");
		agent.getClients().add(client);
		
		session.save(agent);
		int agent_id = agent.getId();
		
		client = new Client();
		client.setName("client3");
		session.save(client);
		
		session.getTransaction().commit();
		session.close();
		
		session = daoFactory.getSession();
		session.beginTransaction();
		
		agent = (Agent) session.get(Agent.class, agent_id);
		assertNotNull(agent);
		Iterator<Client> iter = agent.getClients().iterator();
		while(iter.hasNext()){
			Client cl = iter.next();
			if(!(cl.getName().equals("client1") || cl.getName().equals("client2"))){
				fail("Expected client1 or client2 but have " + cl.getName());
			}
		}
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testDeleteAgentById(){
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Agent agent = new Agent();
		session.save(agent);
		int agent_id = agent.getId();
		
		session.getTransaction().commit();
		session.close();

		session = daoFactory.getSession();
		session.beginTransaction();

		agent = (Agent) session.get(Agent.class, agent_id);
		assertNotNull(agent);

		session.getTransaction().commit();
		session.close();
		
		SessionUtil.getSessionFactory().getCurrentSession().beginTransaction();
		daoFactory.getAgentDAO().deleteAgentById(agent_id);
		SessionUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

		session = daoFactory.getSession();
		session.beginTransaction();
		
		agent = (Agent) session.get(Agent.class, agent_id);
		assertEquals(null, agent);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testGetAgentById(){
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Agent agent = new Agent();
		agent.setName("Test222");
		session.save(agent);
		int agentId = agent.getId();
		
		session.getTransaction().commit();
		session.close();
		
		SessionUtil.getSessionFactory().getCurrentSession().beginTransaction();
		agent = daoFactory.getAgentDAO().getAgentById(agentId);
		SessionUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		
		assertEquals("Test222", agent.getName());
	}

	@Test
	public void testAddAgent(){
		SessionUtil.getSessionFactory().getCurrentSession().beginTransaction();
		Agent agent = new Agent();
		agent.setName("Test222");
		daoFactory.getAgentDAO().addAgent(agent);
		int agentId = agent.getId();
		
		agent = null;
		daoFactory.getAgentDAO().addAgent(agent);
		assertEquals(null, agent);
		SessionUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		agent = (Agent) session.get(Agent.class, agentId);
		assertEquals("Test222", agent.getName());
		
		session.getTransaction().commit();
		session.close();
	}

/*	@Test
	public void testUpdateAgent(){
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Agent agent = new Agent();
		agent.setName("Test222");
		session.save(agent);
		int agentId = agent.getId();

		session.getTransaction().commit();
		session.close();
		
		agent = null;
		daoFactory.getAgentDAO().updateAgent(agent);
		assertEquals(null, agent);

		session = daoFactory.getSession();
		session.beginTransaction();
		
		agent = (Agent) session.get(Agent.class, agentId);
		assertEquals("Test222", agent.getName());
		agent.setName("Test3333");

		session.getTransaction().commit();
		session.close();

		daoFactory.getAgentDAO().updateAgent(agent);
		
		session = daoFactory.getSession();
		session.beginTransaction();
		
		agent = (Agent) session.get(Agent.class, agentId);
		assertEquals("Test3333", agent.getName());
		
		session.getTransaction().commit();
		session.close();

	}*/
	
	@Test
	public void testUpdateAgent(){
		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();
		
		Agent agent = new Agent();
		agent.setName("Test222");
		SessionUtil.getSessionFactory().getCurrentSession().save(agent);
		int agentId = agent.getId();

		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();
		
		agent = null;
		daoFactory.getAgentDAO().updateAgent(agent);
		assertEquals(null, agent);

		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();
		
		agent = (Agent) SessionUtil.getSessionFactory()
				.getCurrentSession().get(Agent.class, agentId);
		assertEquals("Test222", agent.getName());
		agent.setName("Test3333");

		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();

		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();
		
		daoFactory.getAgentDAO().updateAgent(agent);
		
		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();
		
		SessionUtil.getSessionFactory()
			.getCurrentSession().beginTransaction();
		
		agent = (Agent) SessionUtil.getSessionFactory()
					.getCurrentSession().get(Agent.class, agentId);
		assertEquals("Test3333", agent.getName());
		
		SessionUtil.getSessionFactory()
			.getCurrentSession().getTransaction().commit();

	}	

}

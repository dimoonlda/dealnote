package biz.dealnote.web.dao;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.beans.Location;

public class TestLocationDaoJpa {
	private static DAOFactory daoFactory;
	
	@BeforeClass
	public static void initData(){
		daoFactory = DAOFactory.getFactory("jpa", null);
		assertNotNull(daoFactory);
	}

	@Test
	public void testGetLocationList(){
		Session session = daoFactory.getSession();
		session.beginTransaction();

		Agent agent = new Agent();
		session.save(agent);
		int agent_id = agent.getId();

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse("28.10.2012");
			date2 = df.parse("29.10.2012");
		} catch (ParseException e1) {
		}
		assertNotNull(date1);
		assertNotNull(date2);
		
		Location loc = new Location();
		loc.setAgent(agent);
		loc.setClock(date1);
		session.save(loc);

		loc = new Location();
		loc.setAgent(agent);
		loc.setClock(date2);
		session.save(loc);

		session.getTransaction().commit();
		session.close();

		List<Location> res = (List<Location>) daoFactory.getLocationDAO().getLocationList(
				agent_id, date1);
		assertEquals(1, res.size());
	}
}

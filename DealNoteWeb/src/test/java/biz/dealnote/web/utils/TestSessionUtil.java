package biz.dealnote.web.utils;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class TestSessionUtil {
	
	@Test
	public void testGetSession(){
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		assertNotNull(tx);
		
		tx.commit();
		session.close();
	}
}

package biz.dealnote.web.dao.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoJpa {
	private SessionFactory sessionFactory;
	
	public BaseDaoJpa(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}

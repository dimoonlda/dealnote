package biz.dealnote.web.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionUtil {
	private final static SessionUtil instance = new SessionUtil();
	private final SessionFactory factory;

	private SessionUtil() {
		Configuration configuration = new Configuration();
		configuration.configure();
		StandardServiceRegistryBuilder srBuilder = new StandardServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.build();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session getSession() {
		return getInstance().factory.openSession();
	}
	
	public static SessionFactory getSessionFactory() {
		return getInstance().factory;
	}

	private static SessionUtil getInstance() {
		return instance;
	}
}

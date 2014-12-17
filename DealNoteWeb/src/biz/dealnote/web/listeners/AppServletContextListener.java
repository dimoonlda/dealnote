package biz.dealnote.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import biz.dealnote.web.dao.DAOFactory;

public class AppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		String dbContext = sc.getInitParameter("dbContext");
		DAOFactory base = DAOFactory.getFactory(dbContext);
		sc.setAttribute("base", base);
	}

}

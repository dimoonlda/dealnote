package org.dtrader.server.impl;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener{
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Servlet destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Servlet started");		
	}

}

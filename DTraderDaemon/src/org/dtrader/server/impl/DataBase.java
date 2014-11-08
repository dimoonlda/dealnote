package org.dtrader.server.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

public class DataBase {
	private static final Logger logger = Logger.getLogger(DataBase.class);
	
	public static Connection getConnection(WebServiceContext context){
		ServletContext servletContext = null;
		Connection conn = null;
		DataSource ds=null;
		Context ctx = null;
		try {
				try{
					servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
				
					ctx = new InitialContext();

					ds = (DataSource)ctx.lookup(servletContext.getInitParameter("dbContext"));

				}catch(Exception e){
					logger.error("Get DataSource error!!! " + e);
				}

				conn = ds.getConnection();
		} catch (SQLException e) {
			logger.error("Get connection error!!! " + e);
		}
		return conn;
	}

}

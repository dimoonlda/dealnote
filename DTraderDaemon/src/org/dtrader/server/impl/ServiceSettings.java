package org.dtrader.server.impl;

import javax.servlet.ServletContext;

import org.dtrader.server.utils.KeyFileContent;

public class ServiceSettings {
	
	private KeyFileContent keyFileSN = null;
	private String serviceUserName;
	private String serviceUserPass;
	private int useKeyFile;
	
	private static ServiceSettings instance = null;
	
	private ServiceSettings(){
		
	}
	
	public static ServiceSettings getInstance(ServletContext servletContext){
		if (instance == null) {
			instance = new ServiceSettings();
			instance.useKeyFile = Integer.valueOf(servletContext.getInitParameter("useKeyFile"));
			if(instance.useKeyFile == 1)
				instance.keyFileSN = KeyFileContent.getInstance(servletContext);
			instance.serviceUserName = servletContext.getInitParameter("serviceUserName");
			instance.serviceUserPass = servletContext.getInitParameter("serviceUserPass");
		}
		
		return instance;
	}

	public KeyFileContent getKeyFileSN() {
		return keyFileSN;
	}

	public String getServiceUserName() {
		return serviceUserName;
	}

	public String getServiceUserPass() {
		return this.serviceUserPass;
	}

	public int getUseKeyFile() {
		return useKeyFile;
	}
	
	public boolean useKeyFile() {
		return useKeyFile == 1 ? true : false;
	}

}

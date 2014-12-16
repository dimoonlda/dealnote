package biz.dealnote.web.dao;

public class DAOConfigurationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3813278396111532761L;

	public DAOConfigurationException(String message){
		super(message);
	}

	public DAOConfigurationException(String message, Exception e) {
		super(message, e);
	}
}

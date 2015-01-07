package biz.dealnote.web.dao;

import java.sql.SQLException;

public class DAOException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5203722936107858003L;

	public DAOException(String message){
		super(message);
	}

	public DAOException(Throwable e) {
		super(e);
	}
}

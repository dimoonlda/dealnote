package biz.dealnote.rest.controllers.exceptions;

public class WrongAgentException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	public WrongAgentException(String msg, Throwable ex){
		super(msg, ex);
	}
	
	public WrongAgentException(String msg){
		super(msg);
	}
}

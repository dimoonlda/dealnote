package biz.dealnote.rest.controllers.exceptions;

public class WrongRestClientException extends Exception {

	private static final long serialVersionUID = 1L;

	
	public WrongRestClientException(String msg, Throwable ex){
		super(msg, ex);
	}
	
	public WrongRestClientException(String msg){
		super(msg);
	}
	
}

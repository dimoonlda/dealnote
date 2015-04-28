package biz.dealnote.rest.controllers.exceptions;

public class RequestHandledException extends Exception {

	private static final long serialVersionUID = 1L;

	public RequestHandledException(String msg, Exception ex) {
		super(msg, ex);
	}

}

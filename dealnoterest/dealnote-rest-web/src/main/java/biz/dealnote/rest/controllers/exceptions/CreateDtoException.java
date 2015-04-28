package biz.dealnote.rest.controllers.exceptions;

public class CreateDtoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CreateDtoException(String msg, Exception ex){
		super(msg, ex);
	}
}

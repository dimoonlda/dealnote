package biz.dealnote.rest.controllers.exceptions;

public class GoodsGroupNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public GoodsGroupNotFoundException(int id) {
		super(String.format("Goods group with id %d is not found.", id));
	}
}

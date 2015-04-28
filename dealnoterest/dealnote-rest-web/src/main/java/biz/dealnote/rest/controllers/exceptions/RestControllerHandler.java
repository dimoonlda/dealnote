package biz.dealnote.rest.controllers.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestControllerHandler {
	
	@ResponseBody
	@ExceptionHandler({GoodsGroupNotFoundException.class, NotFoundException.class})
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public VndErrors notFoundExceptionHandler(RuntimeException ex){
		return new VndErrors("error", ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler({RequestHandledException.class})
	//@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public VndErrors requestHandledExceptionHandler(RequestHandledException ex){
		return new VndErrors("error", ex.getMessage());
	}
	
}

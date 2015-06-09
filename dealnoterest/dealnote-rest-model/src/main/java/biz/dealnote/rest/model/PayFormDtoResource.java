package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.PayFormDto;

public class PayFormDtoResource extends AbstractModelResource<PayFormDto> {

	public PayFormDtoResource() {}

	public PayFormDtoResource(Collection<PayFormDto> data) {
		super(data);
	}
}

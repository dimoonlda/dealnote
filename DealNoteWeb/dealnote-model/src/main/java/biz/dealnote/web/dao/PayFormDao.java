package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.PayForm;

public interface PayFormDao {
	public Collection<PayForm> getAllPayForms();
	public PayForm getPayFormById(int id);
	public void save(PayForm payForm);
	public void delete(PayForm payForm);
}

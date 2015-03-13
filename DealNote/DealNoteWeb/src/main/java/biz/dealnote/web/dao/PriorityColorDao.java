package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.PriorityColor;

public interface PriorityColorDao {
	public Collection<PriorityColor> getAllPriorityColors();
	public PriorityColor getPriorityColorById(int id);
	public void save(PriorityColor priority);
	public void delete(PriorityColor priority);
}

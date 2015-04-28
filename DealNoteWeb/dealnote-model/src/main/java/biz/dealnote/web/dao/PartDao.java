package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Part;

public interface PartDao {
	public Collection<Part> getParts();
	public Part getPartById(Integer id);
	public void save(Part part);
	public void delete(Part part);
}

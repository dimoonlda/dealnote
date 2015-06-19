package biz.dealnote.web.dao;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.web.model.DocClass;

public interface DocClassDao {
	public Collection<DocClass> getDocClasses();
	public Optional<DocClass> getDocClassById(int id);
	public void save(DocClass docClass);
	public void delete(DocClass docClass);

}

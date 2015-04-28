package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.DocType;

public interface DocTypeDao {
	public Collection<DocType> getAllDocTypes();
	public DocType getDocTypeById(int id);
	public void save(DocType docType);
	public void delete(DocType docType);
}

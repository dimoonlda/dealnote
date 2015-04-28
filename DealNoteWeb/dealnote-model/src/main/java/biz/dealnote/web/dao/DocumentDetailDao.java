package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.DocumentDetail;

public interface DocumentDetailDao {
	
	public Collection<DocumentDetail> getDocumentDetailByDocumentId(Integer id);
	public void save(DocumentDetail detail);
	public void delete(DocumentDetail detail);
}

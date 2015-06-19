package biz.dealnote.web.dao;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DocClassDet;

public interface DocClassDetDao {
	public Collection<DocClassDet> getDocClassDets();
	public Optional<DocClassDet> getDocClassDetById(int id);
	public Collection<DocClassDet> getDocClassDetsByAgent(Agent agent);
	public void save(DocClassDet docClassDet);
	public void delete(DocClassDet docClassDet);
}

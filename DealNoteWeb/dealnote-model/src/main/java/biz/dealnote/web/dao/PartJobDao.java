package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.PartJob;
import biz.dealnote.web.model.User;

public interface PartJobDao {
	public Collection<PartJob> getPartJobs();
	public Collection<PartJob> getActivePartJobsByUser(User user);
	public PartJob getPartJobById(Integer id);
	public void save(PartJob partJob);
	public void delete(PartJob partJob);
}

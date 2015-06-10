package biz.dealnote.web.dao;

import biz.dealnote.web.model.SystemSets;

public interface SystemSetsDao {
	public SystemSets getSystemSetsById(Integer id);
	public void save(SystemSets sets);
}

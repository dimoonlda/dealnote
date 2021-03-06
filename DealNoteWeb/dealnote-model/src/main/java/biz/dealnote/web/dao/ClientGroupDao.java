package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.ClientGroup;

public interface ClientGroupDao {
	public Collection<ClientGroup> getGroups();
	public ClientGroup getGroupById(int groupId);
	public void save(ClientGroup group);
	public void delete(ClientGroup group);
}

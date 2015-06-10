package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.ServiceClient;

public interface ServiceClientDao {
	public Collection<ServiceClient> getServiceClients();
	public ServiceClient getServiceClientById(int clientId);
	public void save(ServiceClient client);
	public void delete(ServiceClient client);
}

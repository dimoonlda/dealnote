package biz.dealnote.web.dao;

import java.util.Collection;
import java.util.List;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;

public interface ClientDAO {
	/**
	 * Get list of clients for agent with agentId.
	 * @param agentId - agent id
	 * @return - list of Clients
	 */
	public Collection<Client> getClientsByAgent(int agentId);
	/**
	 * Get client by ID.
	 * @param clientId - client id.
	 * @return - Client object
	 */
	public Client getClietnById(int clientId);
	
	public void save(Client client);
	public void deleteById(int clientId);
}

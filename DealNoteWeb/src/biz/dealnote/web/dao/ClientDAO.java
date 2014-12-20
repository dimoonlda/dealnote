package biz.dealnote.web.dao;

import java.util.List;

import biz.dealnote.web.beans.Client;

public interface ClientDAO {
	/**
	 * Get list of clients for agent with agentId.
	 * @param agentId - agent id
	 * @return - list of Clients
	 */
	public List<Client> getClientsByAgent(int agentId);
	/**
	 * Get client by ID.
	 * @param clientId - client id.
	 * @return - Client object
	 */
	public Client getClietnById(int clientId);
}

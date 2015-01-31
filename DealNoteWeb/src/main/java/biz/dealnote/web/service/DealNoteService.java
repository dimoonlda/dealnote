package biz.dealnote.web.service;

import java.util.Collection;
import java.util.List;

import biz.dealnote.web.dao.DAOException;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Route;

public interface DealNoteService {
	public Collection<Agent> getActiveAgentsList() throws DAOException;
	public Collection<Agent> getAgentsList() throws DAOException;
	public Agent getAgentById(int agentId);
	public void saveAgent(Agent agent);
	public void deleteAgentById(int agentId) throws DAOException;

	public Collection<Client> getClientsByAgent(int agentId);
	public Client getClietnById(int clientId);	
	public void saveClient(Client client);
	public void deleteClientById(int clientId);
	
	public Collection<Route> getRoutes();
	public Route getRouteById(int routeId);
	
	public Collection<ClientGroup> getGroups();
	public ClientGroup getGroupById(int groupId);
	
	
}

package biz.dealnote.web.service;

import java.util.Collection;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Route;

public interface DealNoteService {
	public Collection<Agent> getActiveAgentsList();
	public Collection<Agent> getAgentsList();
	public Agent getAgentById(int agentId);
	public void saveAgent(Agent agent);
	public void deleteAgentById(int agentId);

	public Collection<Client> getClientsByAgent(int agentId);
	public Client getClietnById(int clientId);	
	public void saveClient(Client client);
	public void deleteClientById(int clientId);
	
	public Collection<Route> getRoutes();
	public Route getRouteById(int routeId);
	
	public Collection<ClientGroup> getGroups();
	public ClientGroup getGroupById(int groupId);
	
	
}

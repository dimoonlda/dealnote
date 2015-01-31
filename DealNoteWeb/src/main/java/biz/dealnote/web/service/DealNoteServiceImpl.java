package biz.dealnote.web.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.dao.DAOException;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Route;

@Service
public class DealNoteServiceImpl implements DealNoteService{

	private AgentDAO agentDao;
	private ClientDAO clientDao;
	private ClientGroupDao clientGroupDao;
	private RouteDao routeDao;
	
	@Autowired
	public DealNoteServiceImpl(AgentDAO agentDao, ClientDAO clientDao,
			ClientGroupDao clientGroupDao, RouteDao routeDao) {
		this.agentDao = agentDao;
		this.clientDao = clientDao;
		this.clientGroupDao = clientGroupDao;
		this.routeDao = routeDao;
	}
	
	@Override
	public Collection<Agent> getActiveAgentsList() throws DAOException {
		return agentDao.getActiveAgentsList();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Agent> getAgentsList() throws DAOException {
		return agentDao.getAgentsList();
	}

	@Override
	@Transactional(readOnly = true)
	public Agent getAgentById(int agentId) {
		return agentDao.getAgentById(agentId);
	}

	@Override
	@Transactional
	public void saveAgent(Agent agent) {
		agentDao.save(agent);
	}

	@Override
	@Transactional
	public void deleteAgentById(int agentId) throws DAOException {
		agentDao.deleteAgentById(agentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Client> getClientsByAgent(int agentId) {
		return clientDao.getClientsByAgent(agentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Client getClietnById(int clientId) {
		return clientDao.getClietnById(clientId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Route> getRoutes() {
		return routeDao.getRoutes();
	}

	@Override
	@Transactional(readOnly = true)
	public Route getRouteById(int routeId) {
		return routeDao.getRouteById(routeId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ClientGroup> getGroups() {
		return clientGroupDao.getGroups();
	}

	@Override
	@Transactional(readOnly = true)
	public ClientGroup getGroupById(int groupId) {
		return clientGroupDao.getGroupById(groupId);
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		clientDao.save(client);
	}

	@Override
	@Transactional
	public void deleteClientById(int clientId) {
		clientDao.deleteById(clientId);
	}

}

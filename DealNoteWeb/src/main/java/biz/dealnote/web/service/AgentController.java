package biz.dealnote.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.utils.Consts;

public class AgentController extends AbstractController {
	
	private Agent agent;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		int agentId = getAgentIdFromRequest(req);
				
		agent = base.getAgentDAO().getAgentById(agentId);
		if (req.getRequestURL().indexOf("/dictionaries/DeleteAgent.do") > 0) {
			base.getAgentDAO().deleteAgentById(agentId);
			req.getRequestDispatcher("/jsp/showAgents.jsp").forward(req,resp);
					
		} else if (req.getRequestURL().indexOf("/dictionaries/ShowAgentInfo.do") > 0) {
			req.setAttribute("agent", agent);
			req.getRequestDispatcher("/jsp/showAgentInfo.jsp").forward(req, resp);
					
		} else if (req.getRequestURL().indexOf("/dictionaries/EditAgentInfo.do") > 0) {
			//save new or update existing agent
			try {
				if (req.getParameter("saveAgent") != null) {
					updateOrCreateAgentFromRequest(req);
					if (agent.getId() != null) {
						base.getAgentDAO().updateAgent(agent);
					} else {
						base.getAgentDAO().addAgent(agent);
					}
				}
			} catch (Exception e) {
				// TODO: Log
			}
			req.getRequestDispatcher("/jsp/showAgents.jsp").forward(req,resp);
					
		} else if (req.getRequestURL().indexOf("/dictionaries/AddAgent.do") > 0) {
			req.getRequestDispatcher("/jsp/showAgentInfo.jsp").forward(req,	resp);
					
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	/**
	 * Get agentId from HttpServletRequest and return.
	 * If agentId is absent then return Consts.NONE
	 * @param req - HttpServletRequest
	 * @return agent ID
	 */
	private int getAgentIdFromRequest(HttpServletRequest req){
		int agentId;
		try{
			agentId = Integer.valueOf(req.getParameter("agentId"));
		}catch(Exception e){
			agentId = Consts.NONE;
		}
		return agentId;
	}
	/**
	 * Get agent data from request and 
	 * update agent object or create new if null.
	 * @param agent
	 * @param req
	 * @throws NumberFormatException
	 */
	private void updateOrCreateAgentFromRequest(HttpServletRequest req) throws NumberFormatException{
			Integer outerId = Integer.valueOf(req.getParameter("outerId"));
			String agentName = req.getParameter("agentName");
			String isActive = req.getParameter("isActive");
			Integer agentRole = Integer.valueOf(req.getParameter("agentRole"));
			if (agent == null) {
				agent = new Agent();
			}
			agent.setName(agentName);
			agent.setOuterId(outerId);
			agent.setRoleCode(agentRole);
			agent.setActive(isActive != null ? 1 : 0);
	}
}

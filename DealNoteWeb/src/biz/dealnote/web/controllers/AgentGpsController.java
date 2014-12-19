package biz.dealnote.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.beans.Location;
import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.dao.LocationDAO;

public class AgentGpsController extends AbstractController {
	private static final long serialVersionUID = -6625434898849602970L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer agentID = null;
		String agentDate = null;
		try{
			agentID = Integer.valueOf(req.getParameter("agentid"));
			agentDate = req.getParameter("agentdate");
		}catch(Exception e){
			//TODO: Log
		}
		
		List<Agent> agents = base.getAgentDAO().getActiveAgentsList();
		req.setAttribute("agentsList", agents);
		if (agentID != null && agentDate != null){
			LocationDAO locationDAO = base.getLocationDAO();
			List<Location> locList = locationDAO.getLocationList(agentID, agentDate);
			req.setAttribute("locations", locList);			
		}
		//req.setAttribute("do", "showGPSLocations");
		
		RequestDispatcher view = req.getRequestDispatcher("/jsp/showAgentGps.jsp");
		view.forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}

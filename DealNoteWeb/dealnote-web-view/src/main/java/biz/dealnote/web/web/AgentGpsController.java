package biz.dealnote.web.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.utils.Consts;

public class AgentGpsController {
	private static final long serialVersionUID = -6625434898849602970L;
/*	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer agentID = null;
		Date agentDate = null;
		try{
			agentID = Integer.valueOf(req.getParameter("agentid"));
			agentDate = Consts.DATE_FORMAT.parse(req.getParameter("agentdate"));
		}catch(Exception e){
			//TODO: Log
		}
		
		List<Agent> agents = new ArrayList<Agent>(base.getAgentDAO().getActiveAgentsList());
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
	} */
}

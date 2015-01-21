package biz.dealnote.web.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Location;

public class ClientController extends AbstractController {
	private static final long serialVersionUID = 6303112483514332136L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer agentID = null;
		try{
			agentID = Integer.valueOf(req.getParameter("agentid"));
		}catch(Exception e){
			//TODO: Log
		}
		
		List<Agent> agents = base.getAgentDAO().getActiveAgentsList();
		req.setAttribute("agentsList", agents);
		
		RequestDispatcher view = req.getRequestDispatcher("/jsp/showClients.jsp");
		view.forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

package biz.dealnote.web.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.model.Client;

public class ClientInfoController extends AbstractController {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int clientId = Integer.valueOf(req.getParameter("clientId"));
		Client client = base.getClientDAO().getClietnById(clientId);
		req.setAttribute("client", client);
		
		req.getRequestDispatcher("/jsp/showClientInfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}

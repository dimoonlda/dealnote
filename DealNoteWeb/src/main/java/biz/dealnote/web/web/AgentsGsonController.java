package biz.dealnote.web.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.service.AbstractController;

/**
 * Servlet implementation class AgentController
 */
@WebServlet("/AgentController")
public class AgentsGsonController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataTable agentDataTable = new AgentJQueryDataTable();
		agentDataTable.init(request);
		agentDataTable.setResultInResponse(response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package biz.dealnote.web.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.service.AbstractController;

public class ClientsGsonController extends AbstractController {
	private static final long serialVersionUID = -8823982693714197299L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataTable clientDataTable = new ClientsJQueryDataTable();
		clientDataTable.init(request);
		clientDataTable.setResultInResponse(response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

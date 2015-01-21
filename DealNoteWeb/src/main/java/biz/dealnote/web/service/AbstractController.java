package biz.dealnote.web.service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import biz.dealnote.web.dao.DAOFactory;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = -2950066276992017231L;

	protected DAOFactory base;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		base = (DAOFactory)config.getServletContext().getAttribute("base");
	}
}

package biz.dealnote.web.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;












import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.datatable.DataTable;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class LocationGsonServlet
 */
public class LocationGsonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*	private DAOFactory base;

	public LocationGsonController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		base = (DAOFactory)config.getServletContext().getAttribute("base");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataTable locationDataTable = new LocationJQueryDataTable();
		locationDataTable.init(request);
		locationDataTable.setResultInResponse(response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
*/
}

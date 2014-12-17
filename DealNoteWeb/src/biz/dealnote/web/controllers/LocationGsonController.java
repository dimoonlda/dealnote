package biz.dealnote.web.controllers;

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







import biz.dealnote.web.beans.Location;
import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.dao.LocationDAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Servlet implementation class LocationGsonServlet
 */
public class LocationGsonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOFactory base;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationGsonController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		base = (DAOFactory)config.getServletContext().getAttribute("base");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");
		Integer agentid = Integer.valueOf(request.getParameter("agentid"));
		String agentdate = request.getParameter("agentdate");
		
		JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords; //value will be set when code filters companies by keyword
		JsonArray data = new JsonArray(); //data that will be shown in the table
		LocationDAO locationDAO = base.getLocationDAO();
		List<Location> locList = locationDAO.getLocationList(agentid, agentdate);

		iTotalRecords = locList.size();
		List<Location> loclinkList = new LinkedList<Location>();
		
		//Searching
		if(param.sSearch != null || param.sSearch.isEmpty()) {
			for(Location c : locList){
				if(	String.valueOf(c.getLatitude()).contains(param.sSearch.toLowerCase()) 
					|| String.valueOf(c.getLongitude()).contains(param.sSearch.toLowerCase())) {
					loclinkList.add(c); 
				}
			}
		}
		
		iTotalDisplayRecords = loclinkList.size(); // number of companies that match search criterion should be returned
		
		final int sortColumnIndex = param.iSortColumnIndex;
		final int sortDirection = param.sSortDirection.equals("asc") ? 1 : -1;
		
		//Sorting to column
		Collections.sort(loclinkList, new Comparator<Location>(){
			@Override
			public int compare(Location c1, Location c2) {	
				switch(sortColumnIndex){
				case 0:
					return Double.valueOf(c1.getLatitude()).compareTo(c2.getLatitude()) * sortDirection;
				case 1:
					return Double.valueOf(c1.getLongitude()).compareTo(c2.getLongitude()) * sortDirection;
				case 2:
					return c1.getClock().compareTo(c2.getClock()) * sortDirection;
				case 3:
					return c1.getSavestateToString().compareTo(c2.getSavestateToString()) * sortDirection;
				case 4:
					return c1.getProvider().compareTo(c2.getProvider()) * sortDirection;
				case 5:
					return Integer.valueOf(c1.getSearchtime()).compareTo(c2.getSearchtime()) * sortDirection;
				case 6:
					return Integer.valueOf(c1.getBattery()).compareTo(c2.getBattery()) * sortDirection;
				}
				return 0;
			}
		});
		
		if(loclinkList.size()< param.iDisplayStart + param.iDisplayLength) {
			loclinkList = loclinkList.subList(param.iDisplayStart, loclinkList.size());
		} else {
			loclinkList = loclinkList.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
	
		try {
			JsonObject jsonResponse = new JsonObject();			
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(Location c : loclinkList){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(c.getLongitude()));
				row.add(new JsonPrimitive(c.getLatitude()));
				row.add(new JsonPrimitive(c.getClockAsString()));
				row.add(new JsonPrimitive(c.getSavestateToString()));
				row.add(new JsonPrimitive(c.getProvider()));
				row.add(new JsonPrimitive(c.getSearchtime()));
				row.add(new JsonPrimitive(c.getBattery()));
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
			
		} catch (JsonIOException e) {
			//TODO: show error page
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

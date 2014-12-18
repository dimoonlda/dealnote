package biz.dealnote.web.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.beans.Location;
import biz.dealnote.web.dao.LocationDAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class LocationJQueryDataTable extends AbstractJQueryDataTable {
	private Integer agentid;
	private String agentdate;
	private List<Location> locSourceList;
	private List<Location> locResultList;
	
	@Override
	public void init(HttpServletRequest request) {
		super.init(request);
		agentid = Integer.valueOf(request.getParameter("agentid"));
		agentdate = request.getParameter("agentdate");
		
		LocationDAO locationDAO = base.getLocationDAO();
		locSourceList = locationDAO.getLocationList(agentid, agentdate);

		locResultList = new LinkedList<Location>();
	}
	
	@Override
	public void search() {
		if(param.sSearch != null || param.sSearch.isEmpty()) {
			for(Location c : locSourceList){
				if(	String.valueOf(c.getLatitude()).contains(param.sSearch.toLowerCase()) 
					|| String.valueOf(c.getLongitude()).contains(param.sSearch.toLowerCase())) {
					locResultList.add(c); 
				}
			}
		}
	}

	@Override
	public void sort() {
		super.sort();
		Collections.sort(locResultList, new Comparator<Location>(){
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
	}

	@Override
	public void setResultInResponse(HttpServletResponse response) throws IOException {
		search();
		sort();
		
		JsonArray data = new JsonArray(); //data that will be shown in the table
		
		if(locResultList.size()< param.iDisplayStart + param.iDisplayLength) {
			locResultList = locResultList.subList(param.iDisplayStart, locResultList.size());
		} else {
			locResultList = locResultList.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
		}
	
		try {
			JsonObject jsonResponse = new JsonObject();			
			jsonResponse.addProperty("sEcho", param.sEcho);
			jsonResponse.addProperty("iTotalRecords", locSourceList.size());
			jsonResponse.addProperty("iTotalDisplayRecords", locResultList.size());
			
			for(Location c : locResultList){
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

}

package biz.dealnote.web.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.beans.Client;
import biz.dealnote.web.dao.ClientDAO;

public class ClientsJQueryDataTable extends AbstractJQueryDataTable<Client> {
	private Integer agentid;
	
	@Override
	public void init(HttpServletRequest request) {
		super.init(request);
		agentid = Integer.valueOf(request.getParameter("agentid"));
		
		ClientDAO ClientDAO = base.getClientDAO();
		locSourceList = ClientDAO.getClientsByAgent(agentid);

		locResultList = new LinkedList<Client>();
	}
	
	@Override
	protected void buildTableRows() {
		for(Client c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive(c.getName()));
			row.add(new JsonPrimitive(c.getAddressLocation()));
			data.add(row);
		}	
	}

	@Override
	public void search() {
		if(param.sSearch != null || !param.sSearch.isEmpty()) {
			for(Client c : locSourceList){
				if(	String.valueOf(c.getId()).contains(param.sSearch.toLowerCase()) 
					|| c.getName().toLowerCase().contains(param.sSearch.toLowerCase())
					|| c.getAddressLocation().toLowerCase().contains(param.sSearch.toLowerCase())) {
					locResultList.add(c); 
				}
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<Client>(){
			@Override
			public int compare(Client c1, Client c2) {	
				switch(sortColumnIndex){
				case 0:
					return Integer.valueOf(c1.getId()).compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 2:
					return c1.getAddressLocation().compareTo(c2.getAddressLocation()) * sortDirection;
				}
				return 0;
			}
		});		
	}

}

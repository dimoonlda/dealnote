package biz.dealnote.web.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.dao.AgentDAO;

public class AgentJQueryDataTable extends AbstractJQueryDataTable<Agent> {

	@Override
	public void init(HttpServletRequest request) {
		super.init(request);

		AgentDAO agentDAO = base.getAgentDAO();
		locSourceList = agentDAO.getAgentsList();

		locResultList = new LinkedList<Agent>();
	}
	
	@Override
	protected void buildTableRows() {
		for(Agent c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive("<a href='../dictionaries/ShowAgentInfo.do?agentId=" + c.getId() + "'>" + c.getName() + "</a>"));
			row.add(new JsonPrimitive(c.getClients().size()));
			row.add(new JsonPrimitive(c.getActiveAsBoolean()));
			row.add(new JsonPrimitive("<a href='../dictionaries/DeleteAgent.do?agentId=" + c.getId() + "'>Delete</a>"));
			data.add(row);
		}	
	}

	@Override
	public void search() {
		if(param.sSearch != null || !param.sSearch.isEmpty()) {
			for(Agent c : locSourceList){
				if(	String.valueOf(c.getId()).contains(param.sSearch.toLowerCase()) 
					|| c.getName().toLowerCase().contains(param.sSearch.toLowerCase())) {
					locResultList.add(c); 
				}
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<Agent>(){
			@Override
			public int compare(Agent c1, Agent c2) {	
				switch(sortColumnIndex){
				case 0:
					return Integer.valueOf(c1.getId()).compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 2:
					return Integer.valueOf(c1.getClients().size()).compareTo(c2.getClients().size()) * sortDirection;
				}
				return 0;
			}
		});		
	}

}

package biz.dealnote.web.model.datatable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.Agent;

public class AgentJQueryDataTable extends AbstractJQueryDataTable<Agent> {

	public AgentJQueryDataTable(Collection<Agent> dataToShow, JQueryDataTableParamModel jQueryDataTableParamModel) {
		super(jQueryDataTableParamModel);
		locSourceList = new ArrayList<Agent>(dataToShow);
	}
	
	@Override
	protected void buildTableRows() {
		for(Agent c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive("<a href='../agents/" + c.getId() + "'>" + c.getName() + "</a>"));
			row.add(new JsonPrimitive(c.getClients().size()));
			row.add(new JsonPrimitive(c.getActiveAsBoolean()));
			row.add(new JsonPrimitive("<a href='../agents/" + c.getId() + "/delete'>D</a>" + " <a href='../agents/" + c.getId() + "/edit'>E</a>"));
			data.add(row);
		}	
	}

	@Override
	public void search() {
			for(Agent c : locSourceList){
				if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
					|| c.getName().toLowerCase().contains(param.getsSearch().toLowerCase())) {
					locResultList.add(c); 
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

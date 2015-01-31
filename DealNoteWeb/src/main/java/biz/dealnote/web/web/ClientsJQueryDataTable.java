package biz.dealnote.web.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.Client;
import biz.dealnote.web.utils.AbstractJQueryDataTable;
import biz.dealnote.web.utils.JQueryDataTableParamModel;

public class ClientsJQueryDataTable extends AbstractJQueryDataTable<Client> {
	
	public ClientsJQueryDataTable(Collection<Client> dataToShow, JQueryDataTableParamModel jQueryDataTableParamModel) {
		super(jQueryDataTableParamModel);
		locSourceList = new ArrayList<Client>(dataToShow);
	}
	
	@Override
	protected void buildTableRows() {
		for(Client c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive("<a href='../" 
					+ c.getId() + "'>" 
					+ c.getName() + "</a>"));
			row.add(new JsonPrimitive(c.getAddressLocation()));
			row.add(new JsonPrimitive("<a href='../" + c.getId() + "/delete'>D</a>" + " <a href='../" + c.getId() + "/edit'>E</a>"));
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

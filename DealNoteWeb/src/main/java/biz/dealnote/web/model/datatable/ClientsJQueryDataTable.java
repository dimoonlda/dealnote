package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.Client;

public class ClientsJQueryDataTable extends AbstractJQueryDataTable<Client> {

	public ClientsJQueryDataTable(Collection<Client> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}

	@Override
	protected void buildTableRows() {
		for(Client c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive(c.getName()));
			row.add(new JsonPrimitive(c.getAddressLocation()));
			row.add(new JsonPrimitive("")); //only for 'Action' column in the result table
			data.add(row);
		}	
	}

	@Override
	public void search() {
			for(Client c : locSourceList){
				if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
					|| c.getName().toLowerCase().contains(param.getsSearch().toLowerCase())
					|| c.getAddressLocation().toLowerCase().contains(param.getsSearch().toLowerCase())) {
					locResultList.add(c); 
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

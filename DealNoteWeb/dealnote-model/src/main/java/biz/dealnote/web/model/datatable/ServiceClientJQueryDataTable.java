package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.ServiceClient;

public class ServiceClientJQueryDataTable extends
		AbstractJQueryDataTable<ServiceClient> {

	public ServiceClientJQueryDataTable(Collection<ServiceClient> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}
	
	@Override
	protected void buildTableRows() {
		for(ServiceClient c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive(c.getTypeCode()));
			row.add(new JsonPrimitive(c.getName()==null ? "" : c.getName()));
			row.add(new JsonPrimitive(c.getUrl()==null ? "" : c.getUrl()));
			row.add(new JsonPrimitive(c.getFileName()==null ? "" : c.getFileName()));
			row.add(new JsonPrimitive(c.getVersion()==null ? "" : c.getVersion()));
			row.add(new JsonPrimitive("")); //only for 'Action' column in the result table
			data.add(row);
		}
	}

	@Override
	public void search() {
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<ServiceClient>(){
			@Override
			public int compare(ServiceClient c1, ServiceClient c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getId().compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getTypeCode().compareTo(c2.getTypeCode()) * sortDirection;
				case 2:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				}
				return 0;
			}
		});	
	}

}

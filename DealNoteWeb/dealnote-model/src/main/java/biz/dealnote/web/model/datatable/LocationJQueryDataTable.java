package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import biz.dealnote.web.model.Location;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class LocationJQueryDataTable extends AbstractJQueryDataTable<Location>{
	
	public LocationJQueryDataTable(Collection<Location> dataToShow, JQueryDataTableParamModel jQueryDataTableParamModel) {
		super(dataToShow, jQueryDataTableParamModel);
	}

	@Override
	protected void buildTableRows() {
		for(Location c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getLongitude()));
			row.add(new JsonPrimitive(c.getLatitude()));
			row.add(new JsonPrimitive(c.getCreationDateAsString("dd.MM.yyyy HH:mm:ss")));
			row.add(new JsonPrimitive(c.getSavestate().getName()));
			row.add(new JsonPrimitive(c.getProvider()));
			row.add(new JsonPrimitive(c.getSearchtime()));
			row.add(new JsonPrimitive(c.getBattery()));
			data.add(row);
		}
	}
		
	@Override
	public void search() {
		for(Location c : locSourceList){
			if(	String.valueOf(c.getLatitude()).contains(param.getsSearch().toLowerCase()) 
				|| String.valueOf(c.getLongitude()).contains(param.getsSearch().toLowerCase())) {
				locResultList.add(c); 
			}
		}
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<Location>(){
			@Override
			public int compare(Location c1, Location c2) {	
				switch(sortColumnIndex){
				case 0:
					return Double.valueOf(c1.getLatitude()).compareTo(c2.getLatitude()) * sortDirection;
				case 1:
					return Double.valueOf(c1.getLongitude()).compareTo(c2.getLongitude()) * sortDirection;
				case 2:
					return c1.getCreationDate().compareTo(c2.getCreationDate()) * sortDirection;
				case 3:
					return c1.getSavestate().compareTo(c2.getSavestate()) * sortDirection;
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
}

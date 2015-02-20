package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.Measure;

public class MeasureJQueryDataTable extends AbstractJQueryDataTable<Measure>{

	private static final String URL_PATH = "/measure/";
	
	public MeasureJQueryDataTable(Collection<Measure> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}

	@Override
	protected void buildTableRows() {
		for(Measure c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive("<a href='.." + URL_PATH + c.getId() + "'>" + c.getName() + "</a>"));
			row.add(new JsonPrimitive(c.getOuterId()));
			row.add(new JsonPrimitive("<a href='.." + URL_PATH + c.getId() + "/delete'>D</a>" 
					+ " <a href='.." + URL_PATH + c.getId() + "/edit'>E</a>"));
			data.add(row);
		}			
	}

	@Override
	public void search() {
		for(Measure c : locSourceList){
			if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
				|| c.getName().toLowerCase().contains(param.getsSearch().toLowerCase())
				|| String.valueOf(c.getOuterId()).contains(param.getsSearch().toLowerCase())) {
				locResultList.add(c); 
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<Measure>(){
			@Override
			public int compare(Measure c1, Measure c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getId().compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 2:
					return c1.getOuterId().compareTo(c2.getOuterId()) * sortDirection;
				}
				return 0;
			}
		});			
	}

}

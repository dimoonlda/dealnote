package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.PriorityColor;

public class PriorityColorJQueryDataTable extends AbstractJQueryDataTable<PriorityColor>{

	public PriorityColorJQueryDataTable(Collection<PriorityColor> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}

	@Override
	protected void buildTableRows() {
		for(PriorityColor c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive("<a href='../priorityColor/" + c.getId() + "'>" + c.getColorCode() + "</a>"));
			row.add(new JsonPrimitive(c.getDescription()));
			row.add(new JsonPrimitive("<a href='../priorityColor/" + c.getId() + "/delete'>D</a>" + " <a href='../priorityColor/" + c.getId() + "/edit'>E</a>"));
			data.add(row);
		}			
	}

	@Override
	public void search() {
		for(PriorityColor c : locSourceList){
			if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
				|| c.getColorCode().toLowerCase().contains(param.getsSearch().toLowerCase())
				|| c.getDescription().contains(param.getsSearch().toLowerCase())) {
				locResultList.add(c); 
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<PriorityColor>(){
			@Override
			public int compare(PriorityColor c1, PriorityColor c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getId().compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getColorCode().compareTo(c2.getColorCode()) * sortDirection;
				case 2:
					return c1.getDescription().compareTo(c2.getDescription()) * sortDirection;
				}
				return 0;
			}
		});			
	}
}

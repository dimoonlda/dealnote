package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.GoodsGroup;

public class GoodsGroupJQueryDataTable extends AbstractJQueryDataTable<GoodsGroup>{
	
	public GoodsGroupJQueryDataTable(Collection<GoodsGroup> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}

	@Override
	protected void buildTableRows() {
		for(GoodsGroup c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive(c.getName()));
			row.add(new JsonPrimitive(c.getOuterId()));
			row.add(new JsonPrimitive(c.getParentId()));
			row.add(new JsonPrimitive("")); //only for 'Action' column in the result table
			data.add(row);
		}			
	}

	@Override
	public void search() {
		for(GoodsGroup c : locSourceList){
			if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
				|| c.getName().toLowerCase().contains(param.getsSearch().toLowerCase())
				|| String.valueOf(c.getOuterId()).contains(param.getsSearch().toLowerCase())) {
				locResultList.add(c); 
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<GoodsGroup>(){
			@Override
			public int compare(GoodsGroup c1, GoodsGroup c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getId().compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 2:
					return c1.getOuterId().compareTo(c2.getOuterId()) * sortDirection;
				case 3:
					return c1.getParentId().compareTo(c2.getParentId()) * sortDirection;					
				}
				return 0;
			}
		});			
	}

}

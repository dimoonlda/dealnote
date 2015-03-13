package biz.dealnote.web.model.datatable;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import biz.dealnote.web.model.Goods;

public class GoodsJQueryDataTable extends AbstractJQueryDataTable<Goods>{

	public GoodsJQueryDataTable(Collection<Goods> dataToShow,
			JQueryDataTableParamModel params) {
		super(dataToShow, params);
	}

	@Override
	protected void buildTableRows() {
		for(Goods c : locResultList){
			JsonArray row = new JsonArray();
			row.add(new JsonPrimitive(c.getId()));
			row.add(new JsonPrimitive(c.getName()));
			row.add(new JsonPrimitive(c.getOuterId() == null ? 0 : c.getOuterId()));
			row.add(new JsonPrimitive(c.getMeasure().getName()));
			row.add(new JsonPrimitive(""));
			data.add(row);
		}			
	}

	@Override
	public void search() {
		for(Goods c : locSourceList){
			if(	String.valueOf(c.getId()).contains(param.getsSearch().toLowerCase()) 
				|| c.getName().toLowerCase().contains(param.getsSearch().toLowerCase())
				|| String.valueOf(c.getOuterId()).contains(param.getsSearch().toLowerCase())) {
				locResultList.add(c); 
			}
		}		
	}

	@Override
	public void sort() {
		Collections.sort(locResultList, new Comparator<Goods>(){
			@Override
			public int compare(Goods c1, Goods c2) {	
				switch(sortColumnIndex){
				case 0:
					return c1.getId().compareTo(c2.getId()) * sortDirection;
				case 1:
					return c1.getName().compareTo(c2.getName()) * sortDirection;
				case 2:
					return c1.getOuterId().compareTo(c2.getOuterId()) * sortDirection;
				case 3:
					return c1.getMeasure().getName().compareTo(c2.getMeasure().getName()) * sortDirection;
				}
				return 0;
			}
		});			
	}
}

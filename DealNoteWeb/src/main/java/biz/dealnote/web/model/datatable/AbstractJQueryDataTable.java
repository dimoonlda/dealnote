package biz.dealnote.web.model.datatable;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.service.DealNoteService;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class AbstractJQueryDataTable<T> implements DataTable {

	public JQueryDataTableParamModel param;
	protected HttpServletRequest request; 

	protected List<T> locSourceList;
	protected List<T> locResultList;
	protected JsonArray data = new JsonArray(); //data that will be shown in the table
	protected JsonObject jsonResponse = new JsonObject();
	
	protected int sortColumnIndex;
	protected int sortDirection;
	
	public AbstractJQueryDataTable(JQueryDataTableParamModel params) {
		this.param = params;
		locResultList = new LinkedList<T>();
	}
	
	protected void setSortParams(){
		sortColumnIndex = param.getiSortCol_0();
		sortDirection = param.getsSortDir_0().equals("asc") ? 1 : -1;	
	}
	
	/**
	 * Fill table with data from Result list
	 */
	abstract protected void buildTableRows();
	
	@Override
	public void processData() {
		if(param.getsSearch() != null && !param.getsSearch().isEmpty()) {
			search();
		}else{
			locResultList = new LinkedList<T>(locSourceList);
		}
		
		setSortParams();
		sort();

		int iTotalRecords = locSourceList.size();
		int iTotalDisplayRecords = locResultList.size();
		
		if (locResultList.size() < param.getiDisplayStart() + param.getiDisplayLength()) {
			locResultList = locResultList.subList(param.getiDisplayStart(),
					locResultList.size());
		} else {
			locResultList = locResultList.subList(param.getiDisplayStart(),
					param.getiDisplayStart() + param.getiDisplayLength());
		}

		jsonResponse = new JsonObject();
		jsonResponse.addProperty("sEcho", param.getsEcho());
		jsonResponse.addProperty("iTotalRecords", iTotalRecords);
		jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);

		buildTableRows();

		jsonResponse.add("aaData", data);
	}
	
	@Override
	abstract public void search();

	@Override
	abstract public void sort();

	@Override
	public JsonObject getDataTableAsJson() {
		return jsonResponse;
	}
}

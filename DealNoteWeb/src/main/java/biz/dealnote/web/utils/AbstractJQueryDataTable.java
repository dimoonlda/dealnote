package biz.dealnote.web.utils;

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
		sortColumnIndex = param.iSortColumnIndex;
		sortDirection = param.sSortDirection.equals("asc") ? 1 : -1;	
	}
	
	/**
	 * Fill table with data from Result list
	 */
	abstract protected void buildTableRows();
	
	@Override
	public void processData() {
		search();
		setSortParams();
		sort();

		int iTotalRecords = locSourceList.size();
		int iTotalDisplayRecords = locResultList.size();
		
		if (locResultList.size() < param.iDisplayStart + param.iDisplayLength) {
			locResultList = locResultList.subList(param.iDisplayStart,
					locResultList.size());
		} else {
			locResultList = locResultList.subList(param.iDisplayStart,
					param.iDisplayStart + param.iDisplayLength);
		}

		jsonResponse = new JsonObject();
		jsonResponse.addProperty("sEcho", param.sEcho);
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

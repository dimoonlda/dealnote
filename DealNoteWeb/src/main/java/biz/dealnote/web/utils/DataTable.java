package biz.dealnote.web.utils;

import javax.servlet.http.HttpServletRequest;
import com.google.gson.JsonObject;

public interface DataTable {
	/**
	 * Search data in table(filtering).
	 * Getting search parameters from request. 
	 */
	public void search();
	/**
	 * Sort data in table.
	 * Getting sort parameters from request. 
	 */	
	public void sort();
	/**
	 * Do a main work with table data.
	 */
	public void processData();
	/**
	 * Return DataTable data in JSON object
	 * @return
	 */
	public JsonObject getDataTableAsJson();
}

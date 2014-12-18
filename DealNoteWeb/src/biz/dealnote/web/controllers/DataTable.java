package biz.dealnote.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataTable {
	/**
	 * Initializing DataTable parameters from request.
	 * @param request - request from client.
	 */
	public void init(HttpServletRequest request);
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
	 * Set result as response out.
	 * @param response response to the client.
	 */
	public void setResultInResponse(HttpServletResponse response) throws IOException;
}

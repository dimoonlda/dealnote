package biz.dealnote.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.dealnote.web.dao.DAOFactory;

public abstract class AbstractJQueryDataTable implements DataTable {

	public class JQueryDataTableParamModel {
	    /// Request sequence number sent by DataTable, same value must be returned in response
	    public String sEcho;

	    /// Text used for filtering
	    public String sSearch;

	    /// Number of records that should be shown in table
	    public int iDisplayLength;

	    /// First record that should be shown(used for paging)
	    public int iDisplayStart;

	    /// Number of columns in table
	    public int iColumns;	

	    /// Number of columns that are used in sorting
	    public int iSortingCols;
	    
	    /// Index of the column that is used for sorting
	    public int iSortColumnIndex;
	    
	    /// Sorting direction "asc" or "desc"
	    public String sSortDirection;

	    /// Comma separated list of column names
	    public String sColumns;
	}
	
	public JQueryDataTableParamModel param = new JQueryDataTableParamModel();
	protected HttpServletRequest request; 
	protected DAOFactory base;
	
	protected int sortColumnIndex;
	protected int sortDirection;
	
	@Override
	public void init(HttpServletRequest request) {
		this.request = request;
		this.base = (DAOFactory)request.getServletContext().getAttribute("base");
		
		if(request.getParameter("sEcho")!=null && request.getParameter("sEcho")!= "")
		{
			param.sEcho = request.getParameter("sEcho");
			param.sSearch = request.getParameter("sSearch");
			param.sColumns = request.getParameter("sColumns");
			param.iDisplayStart = Integer.parseInt( request.getParameter("iDisplayStart") );
			param.iDisplayLength = Integer.parseInt( request.getParameter("iDisplayLength") );
			param.iColumns = Integer.parseInt( request.getParameter("iColumns") );
			param.iSortingCols = Integer.parseInt( request.getParameter("iSortingCols") );
			param.iSortColumnIndex = Integer.parseInt(request.getParameter("iSortCol_0"));
			param.sSortDirection = request.getParameter("sSortDir_0");
		}else
			param = null;
	}

	protected void setSortParams(){
		sortColumnIndex = param.iSortColumnIndex;
		sortDirection = param.sSortDirection.equals("asc") ? 1 : -1;	
	}
	
	@Override
	abstract public void search();

	@Override
	public void sort(){
		setSortParams();
	}

	@Override
	abstract public void setResultInResponse(HttpServletResponse response) throws IOException;

}

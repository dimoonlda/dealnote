package biz.dealnote.web.model.datatable;

import javax.servlet.http.HttpServletRequest;

public class JQueryDataTableParamModel {
    /// Request sequence number sent by DataTable, same value must be returned in response
    private String sEcho;

    /// Text used for filtering
    private String sSearch;

    /// Number of records that should be shown in table
    private int iDisplayLength;

    /// First record that should be shown(used for paging)
    private int iDisplayStart;

    /// Number of columns in table
    private int iColumns;	

    /// Number of columns that are used in sorting
    private int iSortingCols;
    
    /// Index of the column that is used for sorting
    private int iSortCol_0;
    
    /// Sorting direction "asc" or "desc"
    private String sSortDir_0;

    /// Comma separated list of column names
    private String sColumns;
/*
    public JQueryDataTableParamModel(HttpServletRequest request) {
		if(request.getParameter("sEcho")!=null && request.getParameter("sEcho")!= "")
		{
			this.sEcho = request.getParameter("sEcho");
			this.sSearch = request.getParameter("sSearch");
			this.sColumns = request.getParameter("sColumns");
			this.iDisplayStart = Integer.parseInt( request.getParameter("iDisplayStart") );
			this.iDisplayLength = Integer.parseInt( request.getParameter("iDisplayLength") );
			this.iColumns = Integer.parseInt( request.getParameter("iColumns") );
			this.iSortingCols = Integer.parseInt( request.getParameter("iSortingCols") );
			this.iSortCol_0 = Integer.parseInt(request.getParameter("iSortCol_0"));
			this.sSortDir_0 = request.getParameter("sSortDir_0");
		}
	}
	private String sEcho;
	private int iDisplayLength;
	private int iDisplayStart;
	private String iSortingCols;
	private int iSortCol_0;
	private String sSortDir_0;
	private String sSearch;
	private String iColumns;
	private String sColumns;
*/
	public int getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	@Override
	public String toString() {
		return "JQueryDataTableParamModel [sEcho=" + sEcho + ", iDisplayLength="
				+ iDisplayLength + ", iDisplayStart=" + iDisplayStart
				+ ", iSortingCols=" + iSortingCols + ", iSortCol_0="
				+ iSortCol_0 + ", sSortDir_0=" + sSortDir_0 + ", sSearch="
				+ sSearch + ", iColumns=" + iColumns + ", sColumns=" + sColumns
				+ "]";
	}
	
}

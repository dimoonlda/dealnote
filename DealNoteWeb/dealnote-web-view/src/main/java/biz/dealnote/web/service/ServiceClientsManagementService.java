package biz.dealnote.web.service;

import biz.dealnote.web.model.ServiceClient;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;

public interface ServiceClientsManagementService {
	public ServiceClient getServiceClientById(int id);
	public void saveServiceClient(ServiceClient client);
	public void deleteServiceClient(ServiceClient client);
	public ServiceClient getServiceClientByTypeCode(int code);
	public DataTable getServiceClientDataTable(JQueryDataTableParamModel params);
}

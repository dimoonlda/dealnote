package biz.dealnote.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.ServiceClientDao;
import biz.dealnote.web.model.ServiceClient;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.model.datatable.ServiceClientJQueryDataTable;

@Service
public class ServiceClientsManagementServiceImpl implements
		ServiceClientsManagementService {
	private ServiceClientDao serviceClientDao;
	
	@Autowired
	public ServiceClientsManagementServiceImpl(ServiceClientDao serviceClientDao) {
		this.serviceClientDao = serviceClientDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ServiceClient getServiceClientById(int id) {
		return serviceClientDao.getServiceClientById(id);
	}

	@Override
	@Transactional
	public void saveServiceClient(final ServiceClient client) {
		serviceClientDao.save(client);
	}

	@Override
	public DataTable getServiceClientDataTable(JQueryDataTableParamModel params) {
		DataTable dataTable = new ServiceClientJQueryDataTable(serviceClientDao.getServiceClients(), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	@Transactional(readOnly = true)
	public ServiceClient getServiceClientByTypeCode(int code) {
		return serviceClientDao.getServiceClientByTypeCode(code);
	}

	@Override
	@Transactional
	public void deleteServiceClient(ServiceClient client) {
		if(client==null) {
			throw new IllegalArgumentException();
		}
		serviceClientDao.delete(client);
	}
}

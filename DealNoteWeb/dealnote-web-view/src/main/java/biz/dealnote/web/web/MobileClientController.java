package biz.dealnote.web.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.ServiceClient;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.ServiceClientsManagementService;

@RequestMapping(value="/serviceClients")
@Controller
@SessionAttributes(types=ServiceClient.class)
public class MobileClientController {

	private final ServiceClientsManagementService serviceClientsManagementService;
	private DataTable dataTable;
	
	@Autowired
	public MobileClientController(ServiceClientsManagementService serviceClientsManagementService) {
		this.serviceClientsManagementService = serviceClientsManagementService;
	}

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowTableForm(){
		return "showMobileClients";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String getTableData(JQueryDataTableParamModel params){
		dataTable = serviceClientsManagementService.getServiceClientDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateForm(@PathVariable("clientId") int clientId){
		ServiceClient client = serviceClientsManagementService.getServiceClientById(clientId);
		return new ModelAndView("createOrUpdateServiceClientForm").
				addObject("serviceClient", client);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid @ModelAttribute("serviceClient") ServiceClient serviceClient, 
			BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateServiceClientForm";
		}else{
			serviceClientsManagementService.saveServiceClient(serviceClient);
			status.setComplete();
			return "redirect:/serviceClients/";	
		}
	}
	/**
	 * If an upload was successful then 1 else 0.
	 * @param serviceClient
	 * @param model
	 * @param request
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/uploadclientfile", method = RequestMethod.POST)
	public @ResponseBody String  updateGoodsImage(@ModelAttribute("serviceClient") ServiceClient serviceClient,
			Model model, HttpServletRequest request,
			@RequestParam("clientFile") MultipartFile file) throws IOException{
		if (!file.isEmpty()) {
			serviceClient.setFileName(file.getOriginalFilename().replace(" ", "_"));
			serviceClient.setFile(file.getBytes());
			model.addAttribute("serviceClient", serviceClient);
			return "1";
		}
		return "0";
	}
	
	/**
	 * Return client file from database using client type code
	 * @param clientTypeCode Service client type code
	 * @param response
	 */
	@RequestMapping(value = "/{clientTypeCode}/file", method = RequestMethod.GET)
	public HttpEntity<byte[]> getClientFile(@PathVariable int clientTypeCode, HttpServletResponse response){
		ServiceClient client = serviceClientsManagementService.getServiceClientByTypeCode(clientTypeCode);
		byte[] fileBody = client.getFile();
		
		if(fileBody == null){
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "octet-stream"));
	    header.set("Content-Disposition",
	                   "attachment; filename=" 
	                		   + (client.getFileName()!=null ? client.getFileName() : "file") ); 
	    header.setContentLength(fileBody.length);
	    
	    return new ResponseEntity<byte[]>(fileBody, header, HttpStatus.OK);
	}

	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public ModelAndView initShowForm(@PathVariable("clientId") int clientId){
		ServiceClient client = serviceClientsManagementService.getServiceClientById(clientId);
		return new ModelAndView("showServiceClientInfo").
				addObject("serviceClient", client);
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreateForm(){
		ServiceClient client = new ServiceClient();
		return new ModelAndView("createOrUpdateServiceClientForm").
				addObject("serviceClient", client);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@Valid @ModelAttribute("serviceClient") ServiceClient client, 
			BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateServiceClientForm";
		}else{
			serviceClientsManagementService.saveServiceClient(client);
			status.setComplete();
			return "redirect:/serviceClients/";	
		}
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/delete", method = RequestMethod.GET)
	public String processDelete(@PathVariable("clientId") int clientId){
		ServiceClient client = serviceClientsManagementService.getServiceClientById(clientId);
		serviceClientsManagementService.deleteServiceClient(client);
			return "redirect:/serviceClients/";	
	}	
}

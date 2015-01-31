package biz.dealnote.web.web;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.service.DealNoteService;
import biz.dealnote.web.utils.DataTable;
import biz.dealnote.web.utils.JQueryDataTableParamModel;

@RequestMapping(value = "/clients")
@Controller
@SessionAttributes(types = Client.class)
public class ClientController {
	DealNoteService dealNoteService;
	
	@Autowired
	public ClientController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@ModelAttribute(value = "agentsList")
	public Collection<Agent> populateAgents(){
		return this.dealNoteService.getActiveAgentsList();
	}
	
	@ModelAttribute(value = "routesList")
	public Collection<Route> populateRoutes(){
		return this.dealNoteService.getRoutes();
	}

	@ModelAttribute(value = "groupsList")
	public Collection<ClientGroup> populateClientGroups(){
		return this.dealNoteService.getGroups();
	}

	@RequestMapping(value = "/listClients", method = RequestMethod.GET)
	public String initShowClients(Model model){
		return "showClients";
	}
	
	@RequestMapping(value = "/listClients/{agentId}", method = RequestMethod.GET)
	public String processShowClients(@PathVariable(value="agentId") int agentId, Model model){
		model.addAttribute("agentId", agentId);
		return "showClients";
	}

	@ResponseBody
	@RequestMapping(value = "/listgrid/{agentId}", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListClientsForm(@PathVariable(value = "agentId") int agentId, 
			HttpServletRequest httpServletRequest){
		
		JQueryDataTableParamModel jQueryDataTableParamModel = 
				new JQueryDataTableParamModel(httpServletRequest);
		DataTable dataTable = new ClientsJQueryDataTable(dealNoteService.getClientsByAgent(agentId), 
				jQueryDataTableParamModel);
		dataTable.processData();
		return dataTable.getDataTableAsJson().toString();
	}
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public String initShowClientInfoForm(@PathVariable(value = "clientId") int clientId, Model model){
		Client client = dealNoteService.getClietnById(clientId);
		model.addAttribute("client", client);
		return "showClientInfo";
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model){
		Client client = new Client();
		model.addAttribute("client", client);
		return "createOrUpdateClientForm";
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Client client, BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateClientForm";
		}else{
			dealNoteService.saveClient(client);
			status.setComplete();
			return "redirect:/clients/listClients";
		}
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/edit", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable int clientId, Model model){
		Client client = dealNoteService.getClietnById(clientId);
		model.addAttribute("client", client);
		return "createOrUpdateClientForm";
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid Client client, BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateClientForm";
		}else{
			dealNoteService.saveClient(client);
			status.setComplete();
			return "redirect:/clients/listClients";
		}		
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{clientId}/delete", method = RequestMethod.GET)
	public String processDeleteClient(@PathVariable(value = "clientId") int clientId){
		dealNoteService.deleteClientById(clientId);
		return "redirect:/clients/listClients";
	}
}

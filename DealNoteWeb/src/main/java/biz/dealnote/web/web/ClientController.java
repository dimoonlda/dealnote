package biz.dealnote.web.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping(value = "/clients")
@Controller
@SessionAttributes(types = {Client.class})
public class ClientController {
	DealNoteService dealNoteService;
	
	@Autowired
	public ClientController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@ModelAttribute
	public void populateAgents(Model model){
		model.addAttribute("agentsList", this.dealNoteService.getActiveAgentsList());
		model.addAttribute("routesList", this.dealNoteService.getRoutes());
		model.addAttribute("groupsList", this.dealNoteService.getGroups());
		model.addAttribute("agent", new Agent());
	}
	
	@RequestMapping(value = "/listClients", method = RequestMethod.GET)
	public String initShowClients(Model model){
		return "showClients";
	}
	
	@RequestMapping(value = "/listClients/{agentId}", method = RequestMethod.GET)
	public String processShowClients(@PathVariable(value="agentId") int agentId, Model model){
		Agent redirectAgent = new Agent();
		redirectAgent.setId(agentId);
		model.addAttribute("agent", redirectAgent);
		return "showClients";
	}

	@RequestMapping(value = {"/listClients", "/listClients/{agentId}"}, method = RequestMethod.POST)
	public String processShowClients(@ModelAttribute Agent agent, RedirectAttributes redirectAttributes){
		return "redirect:/clients/listClients/" + agent.getId();
	}

	@ResponseBody
	@RequestMapping(value = "/listgrid/{agentId}", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListClientsForm(@PathVariable(value = "agentId") int agentId, 
			JQueryDataTableParamModel params){
		DataTable dataTable = dealNoteService.getClientDataTable(agentId, params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public String initShowClientInfoForm(@PathVariable(value = "clientId") int clientId, @ModelAttribute Agent agent, Model model){
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

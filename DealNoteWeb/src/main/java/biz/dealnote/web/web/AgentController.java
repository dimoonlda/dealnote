package biz.dealnote.web.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping(value = "/agents")
@Controller
@SessionAttributes(types = Agent.class)
public class AgentController{
	
	private final DealNoteService dealNoteService;
	private DataTable dataTable;
	
	@Autowired
	public AgentController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView showAgent(@PathVariable("id") Integer id){
		Agent agent = dealNoteService.getAgentById(id);
		ModelAndView model = new ModelAndView("showAgentInfo")
			.addObject("agent", agent);
		return model;
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreationForm(){
		Agent agent = new Agent();
		return new ModelAndView("createOrUpdateAgentForm")
			.addObject("agent", agent);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Agent agent, BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateAgentForm";
		}else{
			this.dealNoteService.saveAgent(agent);
			status.setComplete();
			return "redirect:/agents/listAgents";
		}
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{agentId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateAgentForm(@PathVariable("agentId") int agentId){
		Agent agent = this.dealNoteService.getAgentById(agentId);
		
		return new ModelAndView("createOrUpdateAgentForm")
			.addObject("agent", agent);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{agentId}/edit", method = RequestMethod.PUT)
	public String processUpdateAgentForm(@Valid Agent agent, BindingResult result, SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateAgentForm";
		}else{
			this.dealNoteService.saveAgent(agent);
			status.setComplete();
			return "redirect:/agents/listAgents";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListAgentForm(JQueryDataTableParamModel params){
		dataTable = dealNoteService.getAgentDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@RequestMapping(value = "/listAgents", method = RequestMethod.GET)
	public String initShowAgents(){
		return "showAgents";
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{agentId}/delete", method = RequestMethod.GET)
	public String initDeleteAgentForm(@PathVariable("agentId") int agentId){
		this.dealNoteService.deleteAgentById(agentId);
		
		return "redirect:/agents/listAgents";
	}
}

package biz.dealnote.web.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping(value="/prioritycolor")
@Controller
@SessionAttributes(types=PriorityColor.class)
public class PriorityColorController {

	private final DealNoteService dealNoteService;
	private DataTable dataTable;
	
	@Autowired
	public PriorityColorController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowListForm(){
		return "showPriorityColors";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListForm(JQueryDataTableParamModel params){
		dataTable = dealNoteService.getPriorityColorDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{priorityId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateForm(@PathVariable("priorityId") int priorityId){
		PriorityColor priorityColor = dealNoteService.getPriorityColorById(priorityId);
		return new ModelAndView("createOrUpdatePriorityColorForm").
				addObject("priorityColor", priorityColor);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{priorityId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid @ModelAttribute("priorityColor") PriorityColor priorityColor, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdatePriorityColorForm";
		}else{
			dealNoteService.save(priorityColor);
			status.setComplete();
			return "redirect:/prioritycolor/";	
		}
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreateForm(){
		PriorityColor priorityColor = new PriorityColor();
		return new ModelAndView("createOrUpdatePriorityColorForm").
				addObject("priorityColor", priorityColor);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@Valid @ModelAttribute("priorityColor") PriorityColor priorityColor, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdatePriorityColorForm";
		}else{
			dealNoteService.save(priorityColor);
			status.setComplete();
			return "redirect:/prioritycolor/";	
		}
	}
	
	@RequestMapping(value = "/{priorityId}", method = RequestMethod.GET)
	public ModelAndView initShowForm(@PathVariable("priorityId") int priorityId){
		PriorityColor priorityColor = dealNoteService.getPriorityColorById(priorityId);
		return new ModelAndView("showPriorityColorInfo").
				addObject("priorityColor", priorityColor);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{priorityId}/delete", method = RequestMethod.GET)
	public String processDelete(@PathVariable("priorityId") int priorityId){
		PriorityColor priorityColor = dealNoteService.getPriorityColorById(priorityId);
			dealNoteService.delete(priorityColor);
			return "redirect:/prioritycolor/";	
	}
}

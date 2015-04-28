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

import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping(value="/measure")
@Controller
@SessionAttributes(types = Measure.class)
public class MeasureController {

	private final DealNoteService dealNoteService;
	private DataTable dataTable;
	
	@Autowired
	public MeasureController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowListForm(){
		return "showMeasures";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListForm(JQueryDataTableParamModel params){
		dataTable = dealNoteService.getMeasureDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{measureId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateForm(@PathVariable("measureId") int measureId){
		Measure measure = dealNoteService.getMeasureById(measureId);
		return new ModelAndView("createOrUpdateMeasureForm").
				addObject("measure", measure);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{measureId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid @ModelAttribute("measure") Measure measure, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateMeasureForm";
		}else{
			dealNoteService.save(measure);
			status.setComplete();
			return "redirect:/measure/";	
		}
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreateForm(){
		Measure measure = new Measure();
		return new ModelAndView("createOrUpdateMeasureForm").
				addObject("measure", measure);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@Valid @ModelAttribute("measure") Measure measure, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateMeasureForm";
		}else{
			dealNoteService.save(measure);
			status.setComplete();
			return "redirect:/measure/";	
		}
	}
	
	@RequestMapping(value = "/{measureId}", method = RequestMethod.GET)
	public ModelAndView initShowForm(@PathVariable("measureId") int measureId){
		Measure measure = dealNoteService.getMeasureById(measureId);
		return new ModelAndView("showMeasureInfo").
				addObject("measure", measure);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{measureId}/delete", method = RequestMethod.GET)
	public String processDelete(@PathVariable("measureId") int measureId){
		Measure measure = dealNoteService.getMeasureById(measureId);
			dealNoteService.delete(measure);
			return "redirect:/measure/";	
	}	
}

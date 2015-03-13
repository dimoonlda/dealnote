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

import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping("/goodsgroup")
@Controller
@SessionAttributes(types = GoodsGroup.class)
public class GoodsGroupController {
	
	private DealNoteService dealNoteService;
	private DataTable dataTable;
	
	@Autowired
	public GoodsGroupController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowListForm(){
		return "showGoodsGroups";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListForm(JQueryDataTableParamModel params){
		dataTable = dealNoteService.getGoodsGroupDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsGroupId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateForm(@PathVariable("goodsGroupId") int goodsGroupId){
		GoodsGroup group = dealNoteService.getGoodsGroupById(goodsGroupId);
		return new ModelAndView("createOrUpdateGoodsGroupForm").
				addObject("goodsGroup", group);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsGroupId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid @ModelAttribute("goodsGroup") GoodsGroup goodsGroup, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateGoodsGroupForm";
		}else{
			dealNoteService.save(goodsGroup);
			status.setComplete();
			return "redirect:/goodsgroup/";	
		}
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreateForm(){
		GoodsGroup group = new GoodsGroup();
		return new ModelAndView("createOrUpdateGoodsGroupForm").
				addObject("goodsGroup", group);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@Valid @ModelAttribute("goodsGroup") GoodsGroup goodsGroup, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateGoodsGroupForm";
		}else{
			dealNoteService.save(goodsGroup);
			status.setComplete();
			return "redirect:/goodsgroup/";	
		}
	}
	
	@RequestMapping(value = "/{goodsGroupId}", method = RequestMethod.GET)
	public ModelAndView initShowForm(@PathVariable("goodsGroupId") int goodsGroupId){
		GoodsGroup group = dealNoteService.getGoodsGroupById(goodsGroupId);
		return new ModelAndView("showGoodsGroupInfo").
				addObject("goodsGroup", group);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsGroupId}/delete", method = RequestMethod.GET)
	public String processDelete(@PathVariable("goodsGroupId") int goodsGroupId){
		GoodsGroup group = dealNoteService.getGoodsGroupById(goodsGroupId);
			dealNoteService.delete(group);
			return "redirect:/goodsgroup/";	
	}	
}

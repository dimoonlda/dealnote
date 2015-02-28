package biz.dealnote.web.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;

@RequestMapping(value = "/goods")
@Controller
@SessionAttributes(types=Goods.class)
public class GoodsController {

	private DealNoteService dealNoteService;
	private DataTable dataTable;
	
	@Autowired
	public GoodsController(DealNoteService dealNoteService){
		this.dealNoteService = dealNoteService;
	}

	@ModelAttribute
	public void populateExtraData(Model model){
		model.addAttribute("goodsGroupList", dealNoteService.getAllGoodsGroups());
		model.addAttribute("priorityColorList", dealNoteService.getAllPriorityColors());
		model.addAttribute("measureList", dealNoteService.getAllMeasure());
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowListForm(){
		return "showGoods";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces="application/json; charset=utf-8;")
	public String initListForm(JQueryDataTableParamModel params){
		dataTable = dealNoteService.getGoodsDataTable(params);
		return dataTable.getDataTableAsJson().toString();
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsId}/edit", method = RequestMethod.GET)
	public ModelAndView initUpdateForm(@PathVariable("goodsId") int goodsId){
		Goods goods = dealNoteService.getGoodsById(goodsId);
		return new ModelAndView("createOrUpdateGoodsForm").
				addObject("goods", goods);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsId}/edit", method = RequestMethod.PUT)
	public String processUpdateForm(@Valid @ModelAttribute("goods") Goods goods, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateGoodsForm";
		}else{
			dealNoteService.save(goods);
			status.setComplete();
			return "redirect:/goods/";	
		}
	}
	
	@RequestMapping(value = "/{goodsId}/image", method = RequestMethod.GET)
	public void goodsImage(@PathVariable int goodsId, HttpServletResponse response){
		Goods goods = dealNoteService.getGoodsById(goodsId);
		try {
			OutputStream out = response.getOutputStream();
			response.setContentType("image/jpeg");
			out.write(goods.getGoodsImage());
			out.flush();
			out.close();
		} catch (IOException e) {
			//TODO: Log error
		}
	}

	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView initCreateForm(){
		Goods goods = new Goods();
		return new ModelAndView("createOrUpdateGoodsForm").
				addObject("goods", goods);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@Valid @ModelAttribute("goods") Goods goods, BindingResult result,
			SessionStatus status){
		if(result.hasErrors()){
			return "createOrUpdateGoodsForm";
		}else{
			dealNoteService.save(goods);
			status.setComplete();
			return "redirect:/goods/";	
		}
	}
	
	@RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
	public ModelAndView initShowForm(@PathVariable("goodsId") int goodsId){
		Goods goods = dealNoteService.getGoodsById(goodsId);
		return new ModelAndView("showGoodsInfo").
				addObject("goods", goods);
	}
	
	@PreAuthorize(value="hasRole('ROLE_USER')")
	@RequestMapping(value = "/{goodsId}/delete", method = RequestMethod.GET)
	public String processDelete(@PathVariable("goodsId") int goodsId){
		Goods goods = dealNoteService.getGoodsById(goodsId);
			dealNoteService.delete(goods);
			return "redirect:/goods/";	
	}	
	
}

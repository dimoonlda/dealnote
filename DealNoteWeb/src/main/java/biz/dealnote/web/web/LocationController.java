package biz.dealnote.web.web;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.service.DealNoteService;
import biz.dealnote.web.web.dto.AgentFilterDto;
import biz.dealnote.web.web.editors.DateTimeEditor;

@Controller
@RequestMapping(value = "/locations")
@SessionAttributes("agentFilterDto")
public class LocationController{
	
	private DealNoteService dealNoteService;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	@Autowired
	public LocationController(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    	dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    	dataBinder.registerCustomEditor(DateTime.class, new DateTimeEditor("dd.MM.yyyy"));
    }

	@ModelAttribute
	public void populateAgents(Model model){
		model.addAttribute("agentsList", this.dealNoteService.getActiveAgentsList());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initShowLocationForm(Model model){
		if(!model.containsAttribute("agentFilterDto")){
			model.addAttribute("agentFilterDto", new AgentFilterDto());
		}
		return "showAgentGps";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String processShowLocationForm(@ModelAttribute("agentFilterDto") AgentFilterDto agentFilterDto, 
			RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("agentFilterDto", agentFilterDto);
		return "redirect:/locations/";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listgrid", 
		method = RequestMethod.GET, 
		produces="application/json; charset=utf-8;")
	public String initListLocationsForm(@ModelAttribute("agentFilterDto") AgentFilterDto agentFilterDto, 
			JQueryDataTableParamModel params){
		DataTable dataTable = dealNoteService.getLocationDataTable(agentFilterDto.getAgent(),
				agentFilterDto.getLocationDate(), params);
		return dataTable.getDataTableAsJson().toString();
	}

	
/*	private DAOFactory base;

	public LocationGsonController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		base = (DAOFactory)config.getServletContext().getAttribute("base");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataTable locationDataTable = new LocationJQueryDataTable();
		locationDataTable.init(request);
		locationDataTable.setResultInResponse(response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
*/
}

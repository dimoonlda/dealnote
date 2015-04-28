package biz.dealnote.web.web.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import biz.dealnote.web.model.Location;

public class LocationSaveStateEditor extends PropertyEditorSupport {

	private MessageSourceAccessor messageSource;
	
	public LocationSaveStateEditor(MessageSource messageSource){
		this.messageSource = new MessageSourceAccessor(messageSource);
	}
	
	@Override
	public String getAsText() {
		String res = "";
/*		switch((Integer)getValue()){
		case Location.SAVESTATE_OK:
			res = messageSource.getMessage("label.field.savestate.ok");
			break;
		case Location.SAVESTATE_GPS_OFF:
			res = messageSource.getMessage("label.field.savestate.gpsOff");;
			break;
		case Location.SAVESTATE_SEARCH_TIME_OUT:
			res = messageSource.getMessage("label.field.savestate.searchTimeOut");;
			break;
		case Location.SAVESTATE_DEVICE_ON:
			res = messageSource.getMessage("label.field.savestate.deviceOn");;
			break;
		case Location.SAVESTATE_DEVICE_OFF:
			res = messageSource.getMessage("label.field.savestate.deviceOff");;
			break;
		default:
			res = messageSource.getMessage("label.field.savestate.none");;
			break;
		}*/
		return res;
	}
}

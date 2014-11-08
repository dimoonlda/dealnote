package org.dtrader.server.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.NONE)
public class LocationWrapper {

	@XmlElementWrapper(name="locVector", required=false)	
	@XmlElement(required=false, name="location")
	protected List<Location> locVector; 
 
    public LocationWrapper() {
    }
    
	public List<Location> getLocVector() {
		return this.locVector;
	}

	public void setLocVector(List<Location> locVector) {
		this.locVector = locVector;
	}
 
}

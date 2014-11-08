package org.dtrader.server.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.NONE)
public class DocumentDetWrapper {

	@XmlElementWrapper(name = "docDetVector", required = false)
	@XmlElement(required = false, name = "documentDet")
	protected List<DocumentDet> docDetVector;

	public DocumentDetWrapper() {
	}

	public List<DocumentDet> getDocDetVector() {
		return docDetVector;
	}

	public void setDocDetVector(List<DocumentDet> docDetVector) {
		this.docDetVector = docDetVector;
	}

}

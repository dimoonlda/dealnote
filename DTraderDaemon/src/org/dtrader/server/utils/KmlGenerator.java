package org.dtrader.server.utils;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.dtrader.server.beans.Location;

public class KmlGenerator {
	
	public static int OPEN_WITH_GOOGLE_EARTH = 1;
	public static int OPEN_WITH_GOOGLE_MAPS = 2;
	
	private static final Logger logger = Logger.getLogger(KmlGenerator.class);
	
	private Connection conn;
	
	ByteArrayOutputStream out = null;
	
	public KmlGenerator(Connection conn){
		this.conn = conn;
	}
	/**
	 * Generate kml-file in ByteArrayOutputStream
	 */
	public ByteArrayOutputStream genKmlToByteArrayOutputStream(List<Location> locList, int agentID) {
		org.w3c.dom.Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			doc = db.newDocument();

			org.w3c.dom.Element kmlElement = doc.createElementNS("http://earth.google.com/kml/2.2", "kml");
			kmlElement.setAttribute("xmlns","http://earth.google.com/kml/2.2");

			org.w3c.dom.Element dok_kml = doc.createElement("Document");
			org.w3c.dom.Element folder = doc.createElement("Folder");
			folder.appendChild(doc.createElement("name"))
				.appendChild(doc.createTextNode("Sales rep: "  + agentID));
			
			StringBuilder linePath = new StringBuilder();
				int count = 1;
				
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				
				Iterator<Location> iter = locList.iterator();
				while(iter.hasNext()){
					Location loc = iter.next();

					org.w3c.dom.Element placemark = doc.createElement("Placemark");
					placemark.appendChild(doc.createElement("description"))
						.appendChild(doc.createTextNode(loc.getClock()));
					placemark.appendChild(doc.createElement("name"))
						.appendChild(doc.createTextNode("Точка " + count));

					org.w3c.dom.Element point = doc.createElement("Point");
					double lat = loc.getLatitude();
					double lon = loc.getLongitude();
					
					DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
					unusualSymbols.setDecimalSeparator('.');
					
					DecimalFormat myFormatter = new DecimalFormat("###.######", unusualSymbols);
					
					point.appendChild(doc.createElement("coordinates"))
						.appendChild(doc.createTextNode(myFormatter.format(lon)+","+myFormatter.format(lat)));
					linePath.append(myFormatter.format(lon)+","+myFormatter.format(lat)+",0 ");
					placemark.appendChild(point);
					folder.appendChild(placemark);
					count++;
				}

			org.w3c.dom.Element placemark = doc.createElement("Placemark");
			placemark.appendChild(doc.createElement("name"))
				.appendChild(doc.createTextNode("Маршрут"));
			org.w3c.dom.Element line = doc.createElement("LineString");
			line.appendChild(doc.createElement("tessellate"))
				.appendChild(doc.createTextNode("1"));
			line.appendChild(doc.createElement("coordinates"))
				.appendChild(doc.createTextNode(linePath.toString()));
			
			placemark.appendChild(line);
			folder.appendChild(placemark);

			dok_kml.appendChild(folder);
			kmlElement.appendChild(dok_kml);
			doc.appendChild(kmlElement);
	  
		} catch (ParserConfigurationException e) {
			logger.error("Error when gegenerate kml-file. " + e);
		}
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			out = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(out));
		} catch (TransformerException e) {
			logger.error("Exception when gegenerate kml-file. " + e);
		} catch (TransformerFactoryConfigurationError e) {
			logger.error("Error when gegenerate kml-file. " + e);
		}
		return out;
	}

}

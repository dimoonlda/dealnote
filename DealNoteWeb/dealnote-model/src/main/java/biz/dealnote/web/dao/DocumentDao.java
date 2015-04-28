package biz.dealnote.web.dao;

import java.util.Collection;

import org.joda.time.DateTime;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Document;

public interface DocumentDao {
	//public Map<Integer, Integer> getDocCountByPeriod(DateTime dateStart, DateTime dateEnd);
	public Collection<Document> getDocumentsByAgentForPeriod(DateTime dateStart, DateTime dateEnd, Agent agent);
	public Collection<Document> getDocumentsByAgent(Agent agent);
	public Document getDocById(Integer id);
	public void save(Document document);
	public void delete(Document document);
}

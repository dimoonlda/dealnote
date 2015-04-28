package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.MeasureLink;

public interface MeasureLinkDao {

	public Collection<MeasureLink> getAllMeasureLinks();
	public Collection<MeasureLink> getMeasureLinkByGoods(Goods goods);
	public Collection<MeasureLink> getMeasureLinkByAgent(Agent agent);
	public MeasureLink getMeasureLinkById(int id);
	public void save(MeasureLink measureLink);
	public void delete(int measureLinkId);
}

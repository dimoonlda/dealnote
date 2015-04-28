package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.GoodsGroup;

public interface GoodsGroupDao {
	public Collection<GoodsGroup> getAllGoodsGroups();
	public GoodsGroup getGoodsGroupById(int id);
	public void save(GoodsGroup group);
	public void delete(GoodsGroup group);
}

package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;

public interface GoodsDao {
	public Collection<Goods> getAllGoods();
	public Goods getGoodsById(int id);
	public void save(Goods goods);
	public void delete(Goods goods);
	public Collection<Goods> getGoodsByAgent(Agent agent);
}

package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.MeasureLink;

@Repository
public class MeasureLinkDaoJpa implements MeasureLinkDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<MeasureLink> getAllMeasureLinks() {
		return this.em.createQuery("from MeasureLink m", MeasureLink.class)
				.getResultList();
	}

	@Override
	public Collection<MeasureLink> getMeasureLinkByGoods(Goods goods) {
		return this.em.createQuery("from MeasureLink m where m.goods = :goods", MeasureLink.class)
				.setParameter("goods", goods)
				.getResultList();
	}

	@Override
	public MeasureLink getMeasureLinkById(int id) {
		Query query = this.em.createQuery("from MeasureLink m where m.id = :id");
		query.setParameter("id", id);
		return (MeasureLink)query.getSingleResult();
	}

	@Override
	public void save(MeasureLink measureLink) {
		if(measureLink.getId()==null){
			this.em.persist(measureLink);
		}else{
			this.em.merge(measureLink);
		}
	}

	@Override
	public void delete(int measureLinkId) {
		this.em.createQuery("delete from MeasureLink m where m.id=:id")
		.setParameter("id", measureLinkId)
		.executeUpdate();
	}

	@Override
	public Collection<MeasureLink> getMeasureLinkByAgent(Agent agent) {
		return this.em
				.createQuery("select a.goods.measureLinks "
						+ "from AgentGoods a inner join a.goods "
						+ "where a.agent = :agent")
				.setParameter("agent", agent)
				.getResultList();
	}

}

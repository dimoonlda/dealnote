package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.WsServerDao;
import biz.dealnote.web.model.WsServer;

@Repository
public class WsServerDaoJpa implements WsServerDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<WsServer> getServers() {
		return em.createQuery("from WsServer", WsServer.class).getResultList();
	}

	@Override
	public Optional<WsServer> getServerById(Integer id) {
		return Optional.of(em.createQuery("from WsServer s where s.id=:id", WsServer.class)
				.setParameter("id", id)
				.getSingleResult());
	}

	@Override
	public void save(WsServer server) {
		if(server.isNew()){
			em.persist(server);
		}else{
			em.merge(server);
		}
	}

	@Override
	public void delete(WsServer server) {
		if (server!=null) {
			em.remove(server);
		}
	}

}

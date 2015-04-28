package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.PayFormDao;
import biz.dealnote.web.model.PayForm;

@Repository
public class PayFormDaoJpa implements PayFormDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<PayForm> getAllPayForms() {
		return em.createQuery("from PayForm p", PayForm.class)
				.getResultList();
	}

	@Override
	public PayForm getPayFormById(int id) {
		return (PayForm)em.createQuery("from PayForm p where p.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(PayForm payForm) {
		if(payForm.getId() == null){
			em.persist(payForm);
		}else{
			em.merge(payForm);
		}
	}

	@Override
	public void delete(PayForm payForm) {
		em.remove(payForm);
	}

}

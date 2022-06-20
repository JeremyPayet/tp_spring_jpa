package myapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myapp.entities.Emprunt;
import myapp.services.ICrud;

/**
 * C'est un @Service -> Bean de Spring -> Contexte de Spring et c'est chargé
 * automatiquement gràce à @ComponentScan de notre classe SpringConfiguration
 *
 * @Repository -> c'est de spécifier que cette classe va utiliser du JPA SPRING
 *
 * @Transactional -> C'est spécifier que nous allons faires des Transactions
 *                Managées en JPA SPRING
 *
 * @author chris
 *
 */

@Service
@Repository
@Transactional
@Qualifier("emprunt")
public class DaoEmprunt implements ICrud<Emprunt, Integer> {
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public Emprunt add(Emprunt e) {
		// TODO Auto-generated method stub
		em.persist(e);
		return e;
	}

	@Override
	public Emprunt update(Emprunt e) {
		// TODO Auto-generated method stub
		em.merge(e);
		return e;
	}

	@Override
	public void delete(Emprunt e) {
		// TODO Auto-generated method stub
		Emprunt o = em.find(Emprunt.class, e.getId());
		em.remove(o);
	}

	@Override
	public List<Emprunt> selectAll() {
		// TODO Auto-generated method stub
		TypedQuery<Emprunt> tq = em.createQuery("select e from Emprunt e", Emprunt.class);
		return tq.getResultList();
	}

	@Override
	public Emprunt selectOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Emprunt.class, id);
	}
}
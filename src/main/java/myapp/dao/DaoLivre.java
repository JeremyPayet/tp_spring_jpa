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

import myapp.entities.Livre;
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
@Qualifier("livre")
public class DaoLivre implements ICrud<Livre, Integer> {
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public Livre add(Livre l) {
		// TODO Auto-generated method stub
		em.persist(l);
		return l;
	}

	@Override
	public Livre update(Livre l) {
		// TODO Auto-generated method stub
		em.merge(l);
		return l;
	}

	@Override
	public void delete(Livre l) {
		// TODO Auto-generated method stub
		Livre o = em.find(Livre.class, l.getId());
		em.remove(o);
	}

	@Override
	public List<Livre> selectAll() {
		// TODO Auto-generated method stub
		TypedQuery<Livre> tq = em.createQuery("select l from Livre l", Livre.class);
		return tq.getResultList();
	}

	@Override
	public Livre selectOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Livre.class, id);
	}
}
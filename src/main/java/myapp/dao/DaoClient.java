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

import myapp.entities.Client;
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
@Qualifier("client")
public class DaoClient implements ICrud<Client, Integer> {
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	EntityManager em;

	@Override
	public Client add(Client c) {
		// TODO Auto-generated method stub
		em.persist(c);
		return c;
	}

	@Override
	public Client update(Client c) {
		// TODO Auto-generated method stub
		em.merge(c);
		return c;
	}

	@Override
	public void delete(Client c) {
		// TODO Auto-generated method stub
		Client o = em.find(Client.class, c.getId());
		em.remove(o);
	}

	@Override
	public List<Client> selectAll() {
		// TODO Auto-generated method stub
		TypedQuery<Client> tq = em.createQuery("select c from Client c", Client.class);
		return tq.getResultList();
	}

	@Override
	public Client selectOne(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Client.class, id);
	}
}
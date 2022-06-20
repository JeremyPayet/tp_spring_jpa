package myapp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import myapp.entities.Client;
import myapp.entities.Emprunt;
import myapp.entities.Livre;
import myapp.services.ICrud;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestSpringJpa {
	@Autowired
	@Qualifier("client")
	ICrud<Client, Integer> daoC;

	@Autowired
	@Qualifier("livre")
	ICrud<Livre, Integer> daoL;

	@Autowired
	@Qualifier("emprunt")
	ICrud<Emprunt, Integer> daoE;

	@Test
	public void addClient() {
		Client c = new Client();
		c.setNom("GERMAIN");
		c.setPrenom("Christophe");
		daoC.add(c);
	}

	@Test
	public void addLivre() {
		Livre l = new Livre();
		l.setAuteur("JPA");
		l.setTitre("JPA Test");
		daoL.add(l);
	}

	@Test
	public void addEmprunt() {
		Emprunt e = new Emprunt();
		e.setClientE(daoC.selectOne(1));
		Set<Livre> set = new HashSet<>();
		set.addAll(daoL.selectAll());
		e.setLivresE(set);
		e.setDatedebut(Calendar.getInstance().getTime());
		e.setDatefin(new Date(120, 3, 14));
		e.setDelai(0);
		daoE.add(e);
	}

	@Test
	public void updateClient() {
		Client c = new Client();
		c.setId(1);
		c.setNom("GERMAIN");
		c.setPrenom("Patrick");
		daoC.update(c);
	}

	@Test
	public void updateLivre() {
		Livre l = new Livre();
		l.setId(1);
		l.setAuteur("JPA");
		l.setTitre("JPA Update");
		daoL.update(l);
	}

	@Test
	public void getClient() {
		addClient();
		daoC.selectOne(1);
	}

	@Test
	public void getLivre() {
		addLivre();
		daoL.selectOne(1);
	}

	@Test
	public void getAllClient() {
		daoC.selectAll();
	}

	@Test
	public void getAllLivre() {
		daoL.selectAll();
	}

	@Test
	public void delClient() {
		Client c = new Client();
		c.setId(1);
		c.setNom("GERMAIN");
		c.setPrenom("Patrick");
		daoC.delete(c);
	}

	@Test
	public void delLivre() {
		Livre l = new Livre();
		l.setId(1);
		l.setAuteur("JPA");
		l.setTitre("JPA Update");
		daoL.delete(l);
	}

}
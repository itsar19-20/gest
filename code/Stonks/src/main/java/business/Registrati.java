package business;

import java.util.Date;

import javax.persistence.EntityManager;

import models.User;
import models.Utente;
import utils.JPAUtil;

public class Registrati {
	
	private User us;
	
	public Registrati(String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password,String metodoDiRegistrazione,EntityManager em){
		
		 us = new Utente(nome, cognome, pIVA, mail, indirizzo, telefono, username, password, metodoDiRegistrazione);
	
		 us.setDataOraUltimoLogin(new Date());
	em.getTransaction().begin();
	em.persist(us);
	em.getTransaction().commit();
	
	}
	
	public User getUtente() {
		return this.us;
	}
}

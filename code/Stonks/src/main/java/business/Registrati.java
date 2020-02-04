package business;

import java.util.Scanner;

import javax.persistence.EntityManager;
import models.Utente;
import models.Persona;
import utils.JPAUtil;

public class Registrati {
	
	public Utente registrati(String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password,String metodoDiRegistrazione){
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = new Utente(nome, cognome, pIVA, mail, indirizzo, telefono, username, password, metodoDiRegistrazione);
	
	em.getTransaction().begin();
	em.persist(u);
	em.getTransaction().commit();
	
	return u;
	}
}

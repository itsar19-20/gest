package business;

import javax.persistence.EntityManager;
import models.Utente;
import utils.JPAUtil;

public class Registrati {
	
	public Registrati(String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password,String metodoDiRegistrazione){
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente ut = new Utente(nome, cognome, pIVA, mail, indirizzo, telefono, username, password, metodoDiRegistrazione);
	
	em.getTransaction().begin();
	em.persist(ut);
	em.getTransaction().commit();
	em.close();
	}
}

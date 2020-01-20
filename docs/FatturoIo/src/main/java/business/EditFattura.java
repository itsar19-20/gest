package business;

import java.sql.Date;

import javax.persistence.EntityManager;

import model.After;
import model.Before;
import model.Fattura;
import model.Persona;
import model.Utente;
import utils.JPAUtil;

public class EditFattura {

	public Utente login(String username, String password) {
		Utente _return = null;
		// cerco l'utente nel DB
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.find(Utente.class, username);
		if (_return != null) {
			// utente trovato; controllo la password
			if (!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		} 
		em.close();
		return _return;
	}
	
	public void crea(Date data, Integer scadenza, boolean clienteFornitore, Persona persona, String nota, 
						String articoloDescrizione, Integer articoloQuantita, Integer artioloPrezzo) {
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		/*
		em.getTransaction().begin();

		Fattura f = new Fattura();
		f.setData = data;
		
		em.persist(f);
		em.getTransaction().commit();
		em.close();
		*/
		
		return f;
	}
	
}

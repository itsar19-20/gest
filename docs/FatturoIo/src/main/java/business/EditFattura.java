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

	public Fattura crea(Date data, Integer scadenza, boolean clienteFornitore, Persona persona, String nota, 
						String articoloDescrizione, Integer articoloQuantita, Integer artioloPrezzo) {
	
		Fattura _return = null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.
		
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
	
	public Fattura leggi(Integer id) {
		Fattura _return = null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		_return = em.find(Fattura.class, id);
		if (_return != null) return _return;
		else return _return;
	}
	
}

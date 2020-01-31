package business;

import java.awt.List;

import javax.persistence.EntityManager;

import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;

public class MenagementFattura {
	
	//protected Fattura _return = null;
	protected static EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	/*
	public void immettiFattura() {
		
	}
	public List<Fattura> mostraFattura() {
		return null;
		
	}
	public void modificaFattura() {
		
	}
	public void immettiNotaDiCredito() {
		
	}
	public Integer sommaParzialeArticoli() {
		return id;
		
	}
	*/
	
	public static Persona getPersona(Integer id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}	
	public static Conto getConto(Integer id) {
		Conto c = em.find(Conto.class, id);
		return c;
	}
	
	public static void crate(Fattura f) {
		em.getTransaction().begin();
		f.setNumeroFattura("Fat-" + "qualcosa");
		//	f.setNumeroFattura("Fat-" + f.getId());
		em.persist(f);
		em.getTransaction().commit();
	}
	public static void add(Articolo a) {
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
	}	
	public void read(Fattura f) {
		f = em.find(Fattura.class, 1);
	}	
	public void update(Fattura f) {
		
	}	
	public void delate(Fattura f) {
		
	}
	
	/*
	public Fattura guarda(Integer id) {
		
		_return = em.find(Fattura.class, id);
		em.close();
		if (_return != null) return _return;
		return null;
		
	}
	*/
	
}

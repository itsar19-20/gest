package business;

import java.awt.List;

import javax.persistence.EntityManager;

import models.Fattura;
import utils.JPAUtil;

public class MenagementFattura {
	
	protected Fattura _return = null;
	protected EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

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
	
	public Fattura crea() {
		
		Fattura f = new Fattura();
		
		//	ID lo genera automaticamente
		// 	la data la vediamo dopo
		
		
		return null;
		
	}
	
	public Fattura guarda(Integer id) {
		
		_return = em.find(Fattura.class, id);
		em.close();
		if (_return != null) return _return;
		return null;
		
	}
	
}

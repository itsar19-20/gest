package business;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;

public class MenagementFattura {

	protected static EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public static Persona getPersona(Integer id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}

	public static Conto getConto(Integer id) {
		Conto c = em.find(Conto.class, id);
		return c;
	}

	public static Fattura readById(Integer id) {
		Fattura f = em.find(Fattura.class, id);
		return f;
	}

	public static List<Fattura> tutteLeFatture() {
		// restituisce la lista di tutte le fatture nel database
		List<Fattura> l = em.createQuery("select f from Fattura f", Fattura.class).getResultList();
		return l;
	}

	// metodo ancora da testare
	public static List<Fattura> listaFatture(Integer id) {
		// restituisce la lista delle fatture create dell'utente che gli viene passato
		List<Fattura> l = em.createQuery("SELECT x FROM Fattura x WHERE x.conto.utente=:user").setParameter("user", id)
				.getResultList();
		return l;
	}

	public static List<Persona> listaPersone(Integer id) {
		// restituisce la lista delle presone create dell'utente che gli viene passato
		List<Persona> lp = em.createQuery("SELECT x FROM Persona x WHERE x.autore=:user").setParameter("user", id)
				.getResultList();
		return lp;
	}

	public static List<Conto> listaConti(Integer id) {
		// restituisce la lista delle presone create dell'utente che gli viene passato
		List<Conto> lc = em.createQuery("SELECT x FROM Conto x WHERE x.utente=:user").setParameter("user", id)
				.getResultList();
		return lc;
	}

	public static Integer getMinIdOfContiAndFatture(Integer id) {
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em
				.createQuery("SELECT min(x.id) FROM Conto x WHERE x.utente=:user").setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em
				.createQuery("SELECT min(x.id) FROM Persona x WHERE x.autore=:user").setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() < output)
			output = qp.getSingleResult();
		return output;
	}

	public static Integer getMaxIdOfContiAndFatture(Integer id) {
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em
				.createQuery("SELECT max(x.id) FROM Conto x WHERE x.utente=:user").setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em
				.createQuery("SELECT max(x.id) FROM Persona x WHERE x.autore=:user").setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() > output)
			output = qp.getSingleResult();
		return output;
	}

}

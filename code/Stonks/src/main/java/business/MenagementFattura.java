package business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;

public class MenagementFattura {

	// protected static EntityManager em =
	// JPAUtil.getInstance().getEmf().createEntityManager();

	public static Persona getPesonaById(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Persona p = em.find(Persona.class, id);
		em.close();
		return p;
	}

	public static Conto getContoById(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Conto c = em.find(Conto.class, id);
		em.close();
		return c;
	}

	public static Fattura getFatturaById(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Fattura f = em.find(Fattura.class, id);
		em.close();
		return f;
	}

	public static List<Fattura> tutteLeFatture() {
		// restituisce la lista di tutte le fatture nel database
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Fattura> _return = em.createQuery("SELECT f FROM Fattura f", Fattura.class).getResultList();
		em.close();
		return _return;
	}

	public static List<Fattura> listaFatture(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		// restituisce la lista delle fatture create dell'utente che gli viene passato
		List<Fattura> l = em.createQuery("SELECT x FROM Fattura x WHERE x.conto.utente=:user ORDER BY x.id DESC").setParameter("user", id)
				.getResultList();
		em.close();
		return l;
	}

	public static List<Persona> listaPersone(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		// restituisce la lista delle presone create dell'utente che gli viene passato
		List<Persona> lp = em.createQuery("SELECT x FROM Persona x WHERE x.autore=:user").setParameter("user", id)
				.getResultList();
		em.close();
		return lp;
	}

	public static List<Conto> listaConti(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		// restituisce la lista delle presone create dell'utente che gli viene passato
		List<Conto> lc = em.createQuery("SELECT x FROM Conto x WHERE x.utente=:user").setParameter("user", id)
				.getResultList();
		em.close();
		return lc;
	}

	public static Integer getMinIdOfContiAndPersone(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em
				.createQuery("SELECT min(x.id) FROM Conto x WHERE x.utente=:user").setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em
				.createQuery("SELECT min(x.id) FROM Persona x WHERE x.autore=:user").setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() < output)
			output = qp.getSingleResult();
		em.close();
		return output;
	}

	public static Integer getMaxIdOfContiAndPersone(Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em
				.createQuery("SELECT max(x.id) FROM Conto x WHERE x.utente=:user").setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em
				.createQuery("SELECT max(x.id) FROM Persona x WHERE x.autore=:user").setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() > output)
			output = qp.getSingleResult();
		em.close();
		return output;
	}

}

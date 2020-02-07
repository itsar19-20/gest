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
	/*
	public static void create(Fattura f) {
		f.setNumeroFattura("Fat-" + "qualcosa");
		//	f.setNumeroFattura("Fat-" + f.getId());
		trans("create", f);
	}
	public static void create(Articolo a) {
		trans("create", a);
	}
            var whatIWant = 'conti';
	*/
	public static void create(Object o) {
		trans("create", o);
		if (o instanceof Fattura) {
			Fattura f = (Fattura) o;
			f.setNumeroFattura("Fat-" + f.getId());
			update(f);
			}
	}	
	public static void update(Object o) {
		trans("update", o);
	}	
	public static void delate(Fattura f) {
		trans("delate", f);
	}
	public static void trans(String s, Object o) {
		em.getTransaction().begin();
		switch (s) {
		case "create":
			em.persist(o);
			break;
		case "update":
			em.merge(o);
			break;
		case "delate":
			em.remove(o);
			break;

		default:
			break;
		}
		em.getTransaction().commit();
	}
	public static Fattura readById(Integer id) {
		Fattura f = em.find(Fattura.class, id);
		return f;
	}
	
	public static List<Persona> listaPersone(Integer id) {
		//	restituisce la lista delle presone create dell'utente che gli viene passato
		TypedQuery<Persona> query = (TypedQuery<Persona>) em.createQuery("SELECT x FROM Persona x WHERE x.autore=:user");
		query.setParameter("user", id);
		List<Persona> lp = query.getResultList();
		return lp;
	}	
	public static List<Conto> listaConti(Integer id) {
		//	restituisce la lista delle presone create dell'utente che gli viene passato
		TypedQuery<Conto> query = (TypedQuery<Conto>) em.createQuery("SELECT x FROM Conto x WHERE x.utente=:user");
		query.setParameter("user", id);
		List<Conto> lc = query.getResultList();
		return lc;
	}
	
	public static Integer getMinIdOfContiAndFatture(Integer id) {
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em.createQuery("SELECT min(x.id) FROM Conto x WHERE x.utente=:user")
				.setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em.createQuery("SELECT min(x.id) FROM Persona x WHERE x.autore=:user")
				.setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() < output) output = qp.getSingleResult();
		return output;
	}	
	public static Integer getMaxIdOfContiAndFatture(Integer id) {
		TypedQuery<Integer> qc = (TypedQuery<Integer>) em.createQuery("SELECT max(x.id) FROM Conto x WHERE x.utente=:user")
				.setParameter("user", id);
		TypedQuery<Integer> qp = (TypedQuery<Integer>) em.createQuery("SELECT max(x.id) FROM Persona x WHERE x.autore=:user")
				.setParameter("user", id);
		Integer output = qc.getSingleResult();
		if (qp.getSingleResult() > output) output = qp.getSingleResult();
		return output;
	}

}

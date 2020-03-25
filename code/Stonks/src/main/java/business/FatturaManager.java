package business;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;

public class FatturaManager {

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
		// restituisce la lista delle fatture create dell'utente che gli viene passato
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Fattura> l = em
					.createQuery("SELECT x FROM Fattura x WHERE x.conto.utente=:user ORDER BY x.id DESC")
					.setParameter("user", id)
					.getResultList();
			em.close();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Conto> listaConti(Integer id) {
		// restituisce la lista delle presone create dell'utente che gli viene passato
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Conto> lc = em
					.createQuery("SELECT x FROM Conto x WHERE x.utente=:user")
					.setParameter("user", id)
					.getResultList();
			em.close();
			return lc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public static List<Fattura> getListByPersona(Persona p) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Fattura> list = em
					.createQuery("SELECT x FROM Fattura x WHERE x.persona=:p")
					.setParameter("p", p)
					.getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Fattura> getListByConto(Conto conto) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Fattura> list = em
					.createQuery("SELECT x FROM Fattura x WHERE x.conto=:c")
					.setParameter("c", conto)
					.getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Fattura setAnno(Fattura f) {
		try {
			f.setAnno(f.getData().getYear());
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Fattura setNumeroFattura(Fattura f) {
		try {
			Integer anno = new Date().getYear();
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Fattura> list = em
					.createQuery("SELECT x FROM Fattura x WHERE x.conto.id=:id AND x.anno=:anno")
					.setParameter("id", f.getConto().getId())
					.setParameter("anno", anno)
					.getResultList();
			em.close();
			int num = 1;
			for (Fattura fattura : list) { num++; }
			f.setNumeroFattura(f.getConto().getPrefisso() + num);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean add(Fattura f) {
		try {
			f = setAnno(f);
			f = setNumeroFattura(f);
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static Integer getUserId(Fattura f) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Integer contoId = f.getConto().getId();
			Integer id = (Integer) em
					.createQuery("SELECT x.utente FROM Conto x WHERE x.id=:contoId ")
					.setParameter("contoId", contoId)
					.getSingleResult();
			em.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Fattura getLastByUserId(Integer userId) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Fattura f = (Fattura) em
					.createQuery("SELECT max(x) FROM Fattura x WHERE x.conto.utente=:id ")
					.setParameter("id", userId)
					.getSingleResult();
			em.close();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

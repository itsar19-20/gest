package business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Persona;
import utils.JPAUtil;

public class PersonaManager {

	public static Integer getMaxId(Integer autore) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			TypedQuery<Integer> tq = (TypedQuery<Integer>) em
					.createQuery("SELECT max(x.id) FROM Persona x WHERE x.autore=:id")
					.setParameter("id", autore);
			Integer maxId = tq.getSingleResult();
			em.close();
			System.out.println(">>>>>>>>>>>> " + maxId);
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Persona getByIdI(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Persona p = em.find(Persona.class, id);
			em.close();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Boolean add(Persona p) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean update(Persona p) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Persona> getListByAuthorId(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Persona> lp = em
					.createQuery("SELECT x FROM Persona x WHERE x.autore=:user ORDER BY x.cognome")
					.setParameter("user", id)
					.getResultList();
			em.close();
			return lp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean delate(Persona p) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(p));
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Persona> listaEliminabiliENon(List<Persona> list) {
		try {
			for (Persona persona : list) {
				if (FatturaManager.getListByPersonaId(persona.getId()) == null)
					persona.setEliminabile(true);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

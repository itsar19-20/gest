package business;

import javax.persistence.EntityManager;

import models.Persona;
import utils.JPAUtil;

public class PersonaMenager {

	public static Integer getMaxId(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Integer maxId = (Integer) em.createQuery("SELECT max(x.id) FROM Persona WHERE x.autore:id")
					.setParameter("id", id)
					.getSingleResult();
			em.close();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	private static Persona getByIdI(Integer id) {
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
	*/
	
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
	
}

package utils;

import javax.persistence.EntityManager;

import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import models.User;

public class DataBase {
	
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
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
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
		em.close();
	}

	public static Object getObjectById(String classe, Integer id) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Object o = null;
		switch (classe) {
		case "a":
			o = em.find(Articolo.class, id);
			break;
		case "c":
			o = em.find(Conto.class, id);
			break;
		case "f":
			o = em.find(Fattura.class, id);
			break;
		case "p":
			o = em.find(Persona.class, id);
			break;
		case "u":
			o = em.find(User.class, id);
			break;

		default:
			System.out.println("[utils.DataBase.getObjectById(classe, id)] inserire un corretto riferimento alla classe");
			break;
		}
		em.close();
		return o;
	}
	
	public static User getUserById(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			User x = em.find(User.class, id);
			em.close();
			return x;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public static User getUserByUsername(String username) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			User x = (User) em
					.createQuery("SELECT x FROM User x WHERE x.username=:username")
					.setParameter("username", username)
					.getSingleResult();
			em.close();
			return x;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public static String getPasswordByUserId(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			String x = (String) em
					.createQuery("SELECT x.password FROM User x WHERE x.id=:id")
					.setParameter("id", id)
					.getSingleResult();
			em.close();
			return x;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static Persona getPersonaById(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Persona x = em.find(Persona.class, id);
			em.close();
			return x;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

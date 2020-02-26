package utils;

import javax.persistence.EntityManager;

import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import models.Users;

public class DataBase {

	protected static EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

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
		// em.close();
	}

	public static Object getObjectById(String classe, Integer id) {
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
			o = em.find(Users.class, id);
			break;

		default:
			System.out.println("[utils.DataBase.getObjectById(classe, id)] inserire un corretto riferimento alla classe");
			break;
		}
		// em.close();
		return o;
	}

}

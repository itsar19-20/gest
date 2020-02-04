package business;

import java.awt.List;

import javax.persistence.EntityManager;
import javax.persistence.Transient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import models.Articolo;
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
	
	/*
	public Fattura guarda(Integer id) {
		
		_return = em.find(Fattura.class, id);
		em.close();
		if (_return != null) return _return;
		return null;
		
	}
	*/
	
}

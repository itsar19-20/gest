package business;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Conto;
import models.Fattura;
import utils.JPAUtil;

public class ContoManager {
	
	public static Integer getMaxId(Integer utente) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			TypedQuery<Integer> tq = (TypedQuery<Integer>) em
					.createQuery("SELECT max(x.id) FROM Conto x WHERE x.utente=:id")
					.setParameter("id", utente);
			Integer maxId = tq.getSingleResult();
			em.close();
			return maxId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Conto getById(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Conto c = em.find(Conto.class, id);
			em.close();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Boolean add(Conto c) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.persist(em.merge(c));
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean update(Conto c) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Conto> getListByUtenteId(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Conto> list = em
					.createQuery("SELECT x FROM Conto x WHERE x.utente=:user ORDER BY x.nome")
					.setParameter("user", id)
					.getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean delate(Conto c) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(c));
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Conto> listaEliminabiliENon(List<Conto> list) {
		try {
			for (Conto conto : list) {
				List<Fattura> fatture = FatturaManager.getListByConto(conto);
				int num = 0;
				for (Fattura f: fatture) {
					num++;
				}
				if (!(num > 0))
					conto.setEliminabile(true);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean setPrefisso(Conto c, String prefisso) {
		try {
			Integer anno = new Date().getYear();
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Fattura> list = em
					.createQuery("SELECT x FROM Fattura x WHERE x.conto.id=:id AND x.anno=:anno")
					.setParameter("id", c.getId())
					.setParameter("anno", anno)
					.getResultList();
			em.close();
			if (list.isEmpty()) {
				c.setPrefisso(prefisso);
				update(c);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

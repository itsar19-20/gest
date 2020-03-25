package business;

import javax.persistence.EntityManager;

import models.User;
import utils.JPAUtil;

public class UserManager {
	
	public static User getById(Integer id) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			User u = em.find(User.class, id);
			em.close();
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean update(User u) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

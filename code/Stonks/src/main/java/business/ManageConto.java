package business;

import java.util.List;

import javax.persistence.EntityManager;

import models.Conto;
import utils.JPAUtil;

public class ManageConto {
	
	public static List<Conto> getConti(Integer idUser) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Conto> conti = em.createQuery("SELECT x FROM Conto x WHERE x.utente=:id")
					.setParameter("id",idUser).getResultList();
			em.close();
			return conti;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

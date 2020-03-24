package business;

import java.util.List;

import javax.persistence.EntityManager;

import models.Conto;
import models.Pagamento;
import utils.JPAUtil;

public class PagamentoManager {

	public static List<Pagamento> getListByConto(Conto conto) {
		try {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			List<Pagamento> list = em
					.createQuery("SELECT x FROM Pagamento x WHERE x.fattura.conto=:c")
					.setParameter("c", conto)
					.getResultList();
			em.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

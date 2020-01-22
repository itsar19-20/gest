package business;

import javax.persistence.EntityManager;

import model.Utente;
import utils.JPAUtil;

public class Login {

		public Utente login(String username, String password) {
			Utente _return = null;
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			
			_return = em.find(Utente.class, username);
			if (_return != null) {
				if (!password.contentEquals(_return.getPassword())) {
					_return = null;
				}
			} 
			em.close();
			return _return;
		}

}
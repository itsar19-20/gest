package business;

import java.util.Date;

import javax.persistence.EntityManager;

import models.Utente;
import utils.JPAUtil;

public class Login {

		public Utente login(String username, String password) {
			Utente _return = null;
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			
			_return = em.find(Utente.class, username);
			if (_return != null) {
				
				em.getTransaction().begin();
				_return.setDataOraUltimoLogin(new Date());
				em.getTransaction().commit();
				
				if (!password.contentEquals(_return.getPassword())) {
					_return = null;
				}
			} 
			em.close();
			return _return;
		}

}
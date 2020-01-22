package business;

import javax.persistence.EntityManager;

import models.ProfUtente;
import utils.JPAUtil;

public class AuthenticationManager {
	
	/**
	 * questo metodo autentica un utente; se user/password sono trovate
	 * nel DB restituisce l'utente, altrimenti restituisce null.
	 * @param username
	 * @param password
	 * @return
	 */
	
	public ProfUtente login(String username, String password) {
		ProfUtente _return = null;
		// cerco l'utente nel DB
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.find(ProfUtente.class, username);
		if (_return != null) {
			// utente trovato; controllo la password
			if (!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		} 
		em.close();
		return _return;
	}

}

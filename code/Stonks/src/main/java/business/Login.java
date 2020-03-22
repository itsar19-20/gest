package business;

import java.util.Date;

import javax.management.Query;
import javax.persistence.EntityManager;

import models.User;
import utils.JPAUtil;

public class Login {

		public User login(String username, String password) {
			User _return = null;
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			
			//	_return = em.find(Users.class, username);
			_return = (User) em.createQuery("Select x from Users x where x.username = :username")
					.setParameter("username", username).getSingleResult();
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
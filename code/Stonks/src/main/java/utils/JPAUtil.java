<<<<<<< HEAD
package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManagerFactory emf;
	
	private static JPAUtil instance;

	/**
	 * singleton che mette a disposizione dell'applicazione l'EntityManagerFactory
	 */
	private JPAUtil() {
		this.emf = Persistence.createEntityManagerFactory("bella");
	}

	public static JPAUtil getInstance() {
		if (instance == null)
			instance = new JPAUtil();
		return instance;
	}

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	
}
=======
package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManagerFactory emf;
	
	private static JPAUtil instance;

	/**
	 * singleton che mette a disposizione dell'applicazione l'EntityManagerFactory
	 */
	
	private JPAUtil() {
		this.emf = Persistence.createEntityManagerFactory("bella");
	}
	
	public static JPAUtil getInstance() {
		if (instance == null)
			instance = new JPAUtil();
		return instance;
	}

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	
}
>>>>>>> branch 'master' of https://github.com/itsar19-20/gest.git

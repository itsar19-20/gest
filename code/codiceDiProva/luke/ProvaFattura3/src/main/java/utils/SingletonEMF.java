package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEMF {
	
	
	
	private EntityManagerFactory emf;
	
	private static SingletonEMF instance;
	
	private SingletonEMF() {
		// modificare il nome del persistence se lo avete diverso.
		this.emf = Persistence.createEntityManagerFactory("ProvaFattura3");
		
		
	}
	
	
	
	public static SingletonEMF getInstance() {
		if (instance == null) {
			instance = new SingletonEMF();
		}
		return instance;
	}
	
	public EntityManagerFactory getEmf() {
		return this.emf;
	}
	

}

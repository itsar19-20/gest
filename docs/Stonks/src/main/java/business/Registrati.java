package business;

import java.util.Scanner;

import javax.persistence.EntityManager;
import models.Utente;
import utils.JPAUtil;

public class Registrati {
	
	public Utente registrati(){
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = new Utente(null, null, null, null, null, null, null, null, null);
	    Scanner s = null;
		System.out.println("Inserire nome");
		u.setNome(s.nextLine());
		System.out.println("Inserire cognome");
		u.setCognome(s.nextLine());
		System.out.println("Inserire Partita IVA");
		u.setpIVA(s.nextLine());
		System.out.println("Inserire E-mail ");
		u.setMail(s.nextLine());	
		System.out.println("Inserire Indirizzo");
		u.setIndirizzo(s.nextLine());
		System.out.println("Inserire Telefono");
		u.setTelefono(s.nextLine());
		System.out.println("Inserire Username");
		u.setUsername(s.nextLine());
		System.out.println("Inserire Password");
		u.setPassword(s.nextLine());
		System.out.println("Inserire il Metodo di Registrazione");
		u.setMetodoDiRegistrazione(s.nextLine());
	
	return u;
	}
}

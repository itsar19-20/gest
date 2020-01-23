package models;

<<<<<<< HEAD
import java.util.Date;
=======
import javax.persistence.Entity;
>>>>>>> 716088c9c768e2cc5a59bcb2b7cb318b3269310f

import models.Persona;

@Entity
public class Users extends Persona{
	
	private String username;
	
	private String password;
	
	private Date dataOraUltimoLogin;
	
//	private boolean attivita = false;
	
	public Users() {
		
	}

	public Users( String nome, String cognome, String pIVA, String mail, String indirizzo, 
				String telefono, String username, String password) {
		super( nome, cognome, pIVA, mail, indirizzo, telefono);
		this.username = username;
		this.password = password;
	}
	
	public void modificaPassword(String passwordVecchia, String pass1, String pass2) {
		if(this.password == passwordVecchia) {
			if(pass1 == pass2) {
				this.password = pass1;
			}else {System.out.println("Le due password non corrispondono");}
				
		}else{System.out.println("La vecchia password � errata, riprova");}
			
	}
	/*
	public boolean getAttivita() {
		if(getAttivita == false) {
			_return this.attivita = true;
		} else {
			return null;
		}
		
		return _return;
	}
	
	public void setAttivitaFalse() {
		this.attivita = false;
	}
	*/
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public Date getDataOraUltimoLogin() {
		return dataOraUltimoLogin;
	}

	public void setDataOraUltimoLogin(Date dataOraUltimoLogin) {
		this.dataOraUltimoLogin = dataOraUltimoLogin;
	}

}

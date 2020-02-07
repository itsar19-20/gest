package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import models.Users;

@Entity
@Table(name = "utente")
public class Utente extends Users {
	
	public Utente() {
		
	}
	
	private String metodoDiRegistrazione;
	
	public Utente( String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password,String metodoDiRegistrazione) {
		super( nome, cognome, pIVA, mail, indirizzo, telefono, username, password);
		
		this.metodoDiRegistrazione = metodoDiRegistrazione;
	}
	
	public int getUtente() {
		return id;
	}
	

	public String getMetodoDiRegistrazione() {
		return metodoDiRegistrazione;
	}

	public void setMetodoDiRegistrazione(String metodoDiRegistrazione) {
		this.metodoDiRegistrazione = metodoDiRegistrazione;
	}
	
}

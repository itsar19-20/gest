package models;

import javax.persistence.Entity;

import models.Users;

@Entity
public class Admin extends Users {

	private String codiceAdmin;
	
	public Admin() {
		
	}

	public Admin( String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password, String codiceAdmin) {
		super( nome, cognome, pIVA, mail, indirizzo, telefono, username, password);
		this.codiceAdmin = codiceAdmin;
	}

	public String getCodiceAdmin() {
		return codiceAdmin;
	}

	public void modificaCodiceAdmin(String codiceAdmin1, String password, String codAdmN1, String codAdmN2) {
		if (this.codiceAdmin == codiceAdmin1) {
			if (this.getPassword() == password) {
				if (codAdmN1 == codAdmN2) {
					this.codiceAdmin = codAdmN1;
				} else {
					System.out.println("I due codici nuovi non corrispondo.");
				}
			} else {
				System.out.println("La Password � errata.");
			}
		} else {
			System.out.println("Il codice admin non � corretto.");
		}
	}
}
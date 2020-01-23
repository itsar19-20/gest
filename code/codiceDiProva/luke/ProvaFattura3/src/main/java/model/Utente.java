package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity(name="Utente")
@NamedQuery(name="utente.findAll", query="SELECT ut FROM Utente ut")
public class Utente extends User {
	

	private String metodoRegistrazione;

	public String getMetodoRegistrazione() {
		return metodoRegistrazione;
	}

	public void setMetodoRegistrazione(String metodoRegistrazione) {
		this.metodoRegistrazione = metodoRegistrazione;
	}
	
	
	

}

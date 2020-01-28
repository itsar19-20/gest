package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer idPersona;
	
	private String nome;
	private String cognome;
	private String pIVA;
	private String mail;
	private String indirizzo;
	private String telefono;
	
	@OneToMany	//(mappedBy = "persona")
	private List<Fattura> fatture;
	
	public Persona() {
		
	}
	
	public Persona( String nome, String cognome, String pIVA, String mail, String indirizzo,String telefono) {
		this.nome = (nome == null ? null : nome.toLowerCase());
		this.cognome = (cognome == null ? null : cognome.toLowerCase());
		this.pIVA = pIVA;
		this.mail = mail;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
	}
	public void modificaPersona(String nome, String cognome, String pIVA, String mail, String indirizzo,String telefono) {
		if(this.nome.toLowerCase().contentEquals(nome.toLowerCase()))
			this.nome = nome.toLowerCase();
		if(this.cognome.toLowerCase().contentEquals(cognome.toLowerCase()))
			this.cognome = cognome.toLowerCase();
		if(this.pIVA.contentEquals(pIVA))
			this.pIVA = pIVA;
		if(this.mail.contentEquals(mail))
			this.mail = mail;
		if(this.indirizzo.contentEquals(indirizzo))
			this.indirizzo = indirizzo;
		if(this.telefono.contentEquals(telefono))
			this.telefono = telefono;
	}
	public void eliminaPersona(int idPersona) {
//		query delete persona idPersona
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getpIVA() {
		return pIVA;
	}
	public void setpIVA(String pIVA) {
		this.pIVA = pIVA;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Fattura> getFatture() {
		return fatture;
	}

	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}

}


package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "articolo")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@JsonProperty("descrizione")
	private String descrizione;
	
	@JsonProperty("quantita")	
	private Integer quantita;
	
	@JsonProperty("prezzo")
	private float prezzo;
	
	private float iva = 0.22f;
	
	@ManyToOne
	@JoinColumn(name = "fattura_id")
	@JsonIgnore
	private Fattura fattura;
	
	public Articolo() {
		
	}

	public Articolo(String descrizione, Integer quantita, float prezzo, Fattura fattura) {
		super();
		//	this.idArticolo = idArticolo;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.prezzo = prezzo;
		//	this.iva = iva;
		this.fattura = fattura;
	}
	
	public void aggiungi() {
		
	}
	public void elimina() {
		
	}
	
	public Integer getIdArticolo() {
		return id;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.id = idArticolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}
	public Fattura getFattura() {
		return fattura;
	}
	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
	
}

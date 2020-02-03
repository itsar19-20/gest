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

@Entity
@Table(name = "articolo")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idArticolo;
	private String descrizione;
	private Integer quantita;
	private float importoParziale;
	private float iva = 0.22f;
	
	@ManyToOne
	@JoinColumn(name = "fattura_id")
	@JsonIgnore
	private Fattura fattura;
	
	public Articolo() {
		
	}

	public Articolo(String descrizione, Integer quantita, float importoParziale, Fattura fattura) {
		super();
		//	this.idArticolo = idArticolo;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.importoParziale = importoParziale;
		//	this.iva = iva;
		this.fattura = fattura;
	}
	
	public void aggiungi() {
		
	}
	public void elimina() {
		
	}
	
	public Integer getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
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
	public float getImportoParziale() {
		return importoParziale;
	}
	public void setImportoParziale(float importoParziale) {
		this.importoParziale = importoParziale;
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

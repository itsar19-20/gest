package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="articolo")
@NamedQuery(name="articolo.findAll", query="SELECT ar FROM Articolo ar")
public class Articolo {
	//private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id")
	private Integer idArticolo;
	@ManyToOne
	@JoinColumn(name="fattura_id", insertable = false, updatable = false)
	private Fattura fattura;
	
	private String descrizione;
	private int quantita;
	private double importoParziale;
	
	private float iva;
	
	
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getImportoParziale() {
		return importoParziale;
	}

	public void setImportoParziale(double importoParziale) {
		this.importoParziale = importoParziale;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	

}

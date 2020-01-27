package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fattura")
@NamedQuery(name="fattura.findAll", query="SELECT f FROM Fattura f")
public class Fattura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id")
	private Integer idFattura;
	
	private Date data;
	private boolean fatturaCliente;
	private Integer scadenza;
	
	@ManyToOne
	@JoinColumn(name = "metodo_pagamento", nullable =false)
	private MetodoDiPagamento metodoDiPagamento;
	
	@ManyToOne
	@JoinColumn(name="fattura_cliente", nullable=false)
	private Persona persona;
	private String nota;
	@OneToMany
	@JoinColumn(name="fattura_id")
	private List<Articolo> articolo;
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "conto_id", referencedColumnName = "id")
	@ManyToOne
	@JoinColumn(name="conto_id",nullable=false)
	private Conto conto;
	private float IVA;
	private double lordo;
	private String numeroFattura;
	@OneToOne(mappedBy = "fattura")
	private Pagamento pagamento;
	
	public Integer getIdFattura() {
		return idFattura;
	}
	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}
	
	
	public Integer getScadenza() {
		return scadenza;
	}
	public void setScadenza(Integer scadenza) {
		this.scadenza = scadenza;
	}
	
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
	}
	public List<Articolo> getArticolo() {
		return articolo;
	}
	public void setArticolo(List<Articolo> articolo) {
		this.articolo = articolo;
	}
	public float getIVA() {
		return IVA;
	}
	public void setIVA(float iVA) {
		IVA = iVA;
	}
	public double getLordo() {
		return lordo;
	}
	public void setLordo(double lordo) {
		this.lordo = lordo;
	}
	public String getNumeroFattura() {
		return numeroFattura;
	}
	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}
	public boolean isFatturaCliente() {
		return fatturaCliente;
	}
	public void setFatturaCliente(boolean fatturaCliente) {
		this.fatturaCliente = fatturaCliente;
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona=persona;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public MetodoDiPagamento getMetodoDiPagamento() {
		return metodoDiPagamento;
	}
	public void setMetodoDiPagamento(MetodoDiPagamento metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
	}
	

}

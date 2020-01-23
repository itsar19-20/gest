package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="conto")
@NamedQuery(name="conto.findAll", query="SELECT c FROM Conto c")
public class Conto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id")
	private Integer idConto;
	private double saldoDisponibile;
	private double saldoUtile;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "metodo_di_pagamento_id", referencedColumnName = "id")
	private MetodoDiPagamento metodoDiPagamento;
	
	@OneToOne(mappedBy = "conto")
	private Fattura fattura;
	
	public Integer getIdConto() {
		return idConto;
	}
	public void setIdConto(Integer idConto) {
		this.idConto = idConto;
	}
	public double getSaldoDisponibile() {
		return saldoDisponibile;
	}
	public void setSaldoDisponibile(double saldoDisponibile) {
		this.saldoDisponibile = saldoDisponibile;
	}
	public double getSaldoUtile() {
		return saldoUtile;
	}
	public void setSaldoUtile(double saldoUtile) {
		this.saldoUtile = saldoUtile;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public MetodoDiPagamento getMetodoDiPagamento() {
		return metodoDiPagamento;
	}
	public void setMetodoDiPagamento(MetodoDiPagamento metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
	}

}

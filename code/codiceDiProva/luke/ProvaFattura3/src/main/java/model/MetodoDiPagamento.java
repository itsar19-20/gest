package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="metodo_di_pagamento")
@NamedQuery(name="metodoDiPagamento.findAll", query="SELECT m FROM MetodoDiPagamento m")
public class MetodoDiPagamento  {
	@Id
	@Column(name="id")
	private String metodo;
	private String nome;
	private String destinazione;
	@OneToOne(mappedBy = "metodoDiPagamento")
	private Conto conto;
	
	@OneToMany(mappedBy="metodoDiPagamento")
	private List<Fattura> fattura;
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

}

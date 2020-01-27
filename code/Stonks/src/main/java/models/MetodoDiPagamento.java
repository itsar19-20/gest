package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MetodoDiPagamento {

	public MetodoDiPagamento() {
		
	}
	
	@Id
	private Integer id;
	
	@OneToMany	//(mappedBy = "metodoDiPagamento")
	private List<Fattura> fattura;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*
	public List<Fattura> getFattura() {
		return fattura;
	}
	public void setFattura(List<Fattura> fattura) {
		this.fattura = fattura;
	}
	*/
	
}

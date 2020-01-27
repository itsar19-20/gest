package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conto {

	@Id
	private Integer id;
	
	public Conto() {
		
	}
	
	/*
	@OneToMany(mappedBy = "conto")
	private List<Fattura> fatture;
	*/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*
	public List<Fattura> getFatture() {
		return fatture;
	}
	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}
	*/
	
}

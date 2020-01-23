package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MetodoDiPagamento {

	public MetodoDiPagamento() {
		
	}
	
	@Id
	private Integer id;
	
}

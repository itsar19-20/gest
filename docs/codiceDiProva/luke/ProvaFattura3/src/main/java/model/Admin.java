package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity(name="Admin")
@NamedQuery(name="admin.findAll", query="SELECT a FROM Admin a")
public class Admin extends User{
	
	
	private Integer codiceAdmin;
	
	
	public Integer getCodiceAdmin() {
		return codiceAdmin;
	}
	public void setCodiceAdmin(Integer codiceAdmin) {
		this.codiceAdmin = codiceAdmin;
	}
	

}

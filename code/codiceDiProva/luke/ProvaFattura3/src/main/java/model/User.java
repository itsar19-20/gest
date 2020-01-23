package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;

@Entity(name="User")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="user.findAll", query="SELECT us FROM User us")
public class User extends Persona {
	
	private String username;
	
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

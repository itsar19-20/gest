package model;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fornitore_cliente")
@NamedQuery(name="fornitoreCliente.findAll", query="SELECT fo FROM FornitoreCliente fo")
public class FornitoreCliente extends Persona {
	
	@OneToOne(mappedBy = "destinatario")
	private Fattura fattura;
	
	@OneToOne(mappedBy = "mittente")
	private Fattura fattura2;
	
	

}

package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conto")
public class Conto {

	@Id
	private Integer id;
	
	public Conto() {
		
	}
	
	/*
	@OneToMany(mappedBy = "conto")
	private List<Fattura> fatture;
<<<<<<< HEAD
	private Integer idFatture = 0;
=======
	*/
>>>>>>> branch 'master' of https://github.com/itsar19-20/gest.git
	
	@OneToMany(mappedBy = "conto")
	private List<Pagamento> pagamenti;
	private Integer idPagamenti = 0;
	
	private float saldoDisponibile = 0f;
	private float saldoUtile = 0f;
	
	@OneToOne(mappedBy = "conto")
	private int utente = new Utente().getUtente();
	
	public Integer getIdFatture() {
		return idFatture;
	}
	public void setIdFatture(Integer idFatture) {
		this.idFatture = idFatture;
	}
	public List<Pagamento> getPagamenti() {
		return pagamenti;
	}
	public void setPagamenti(List<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}
	public Integer getIdPagamenti() {
		return idPagamenti;
	}
	public void setIdPagamenti(Integer idPagamenti) {
		this.idPagamenti = idPagamenti;
	}
	public int getUtente() {
		return utente;
	}
	public void setUtente(int utente) {
		this.utente = utente;
	}
	public float getSaldoDisponibile() {
		return saldoDisponibile;
	}
	public void setSaldoDisponibile(float saldoDisponibile) {
		this.saldoDisponibile = saldoDisponibile;
	}
	public float getSaldoUtile() {
		return saldoUtile;
	}
	public void setSaldoUtile(float saldoUtile) {
		this.saldoUtile = saldoUtile;
	}
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

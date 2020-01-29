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
	private String nome;
	
	public Conto() {
		
	}
	
	@OneToMany
	private List<Fattura> fatture;
	private Integer idFatture = 0;
	
	@OneToMany
	private List<Pagamento> pagamenti;
	private Integer idPagamenti = 0;
	
	private float saldoDisponibile = 0f;
	private float saldoUtile = 0f;
	
	@OneToOne
	private Utente utente = new Utente();
	
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
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
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
	public List<Fattura> getFatture() {
		return fatture;
	}
	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

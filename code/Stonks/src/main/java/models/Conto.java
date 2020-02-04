package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "conto")
public class Conto {

	public Conto(Integer id, String nome, List<Fattura> fatture, Integer idFatture, List<Pagamento> pagamenti,
			Integer idPagamenti, float saldoDisponibile, float saldoUtile, Utente utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.fatture = fatture;
		this.idFatture = idFatture;
		this.pagamenti = pagamenti;
		this.idPagamenti = idPagamenti;
		this.saldoDisponibile = saldoDisponibile;
		this.saldoUtile = saldoUtile;
		this.utente = utente;
	}

	@Id
	private Integer id;
	private String nome;
	
	public Conto() {
		
	}
	
	@OneToMany(mappedBy = "conto")
	@Transient
	private List<Fattura> fatture;
	
	@Transient
	private Integer idFatture = 0;
	
	@Transient
	@OneToMany
	private List<Pagamento> pagamenti;
	
	@Transient
	private Integer idPagamenti = 0;
	
	private float saldoDisponibile = 0f;
	private float saldoUtile = 0f;
	
	@Column(name = "utente_id")
	private Integer utente;
	
	
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
	public Integer getUtente() {
		return utente;
	}
	public void setUtente(Integer utente) {
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

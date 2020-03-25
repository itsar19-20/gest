package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import business.FatturaManager;

@Entity
@Table(name = "conto")
public class Conto {

	public Conto(String nome, float saldoDisponibile, float saldoUtile, Integer utente) {
		super();
		this.nome = nome;
		this.saldoDisponibile = saldoDisponibile;
		this.saldoUtile = saldoUtile;
		this.utente = utente;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("nome")
	private String nome;
	
	private String prefisso;

	public Conto() {

	}

	@OneToMany(mappedBy = "conto")
	// @Transient
	@JsonIgnore
	private List<Fattura> fatture;

	@Transient
	@JsonIgnore
	private Integer idFatture = 0;

	@Transient
	@OneToMany
	@JsonIgnore
	private List<Pagamento> pagamenti;

	@Transient
	@JsonProperty("idPagamenti")
	@JsonIgnore
	private Integer idPagamenti = 0;

	@JsonProperty("saldoDisponibile")
	@JsonIgnore
	private float saldoDisponibile = 0f;

	@JsonProperty("saldoUtile")
	@JsonIgnore
	private float saldoUtile = 0f;

	@Column(name = "utente_id")
	@JsonProperty("utente")
	private Integer utente;
	
	@Transient
	private boolean eliminabile = false;

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
		FatturaManager.getListByConto(this);
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

	public boolean isEliminabile() {
		return eliminabile;
	}

	public void setEliminabile(boolean eliminabile) {
		this.eliminabile = eliminabile;
	}

	public String getPrefisso() {
		return prefisso;
	}

	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}

}

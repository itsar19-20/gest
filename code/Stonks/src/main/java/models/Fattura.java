package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "fattura")
public class Fattura {

	public Fattura() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	@JsonProperty("data")
	private Date data;

	@Column(name = "scadenza")
	@JsonProperty("scadenza")
	private Integer scadenza;

	@Column(name = "e_una_fattura_cliente")
	@JsonProperty("eUnaFatturaCliente")
	private boolean eUnaFatturaCliente;
	// nel DB usiamo un dato di tipo 'BIT'
	// 0 = false
	// 1 = true

	@ManyToOne
	@JoinColumn(name = "persona_id")
	@JsonProperty("persona")
	private Persona persona;

	@Column(name = "nota")
	@JsonProperty("nota")
	private String nota;

	@OneToMany(mappedBy = "fattura", cascade = CascadeType.ALL)
	private List<Articolo> articoli;

	@Column(name = "numero_fattura")
	@JsonProperty("numeroFattura")
	private String numeroFattura = "bho";

	@Transient
	private float iva = 0.22F;

	@Column(name = "lordo")
	private float lordo = 0;

	@Column(name = "pagata")
	private boolean pagata = false;

	@ManyToOne
	private Conto conto;

	public Fattura(Integer id, Date data, Integer scadenza, boolean eUnaFatturaCliente, Persona persona, String nota,
			List<Articolo> articoli, String numeroFattura, float iva, float lordo, boolean pagata, Conto conto) {
		super();
		this.id = id;
		this.data = data;
		this.scadenza = scadenza;
		this.eUnaFatturaCliente = eUnaFatturaCliente;
		this.persona = persona;
		this.nota = nota;
		this.articoli = articoli;
		this.numeroFattura = numeroFattura;
		this.iva = iva;
		this.lordo = lordo;
		this.pagata = pagata;
		this.conto = conto;
	}

	public Fattura(Date data, Integer scadenza, boolean eUnaFatturaCliente, Persona persona, String nota,
			boolean pagata, Conto conto) {
		super();
		// this.id = id;
		this.data = data;
		this.scadenza = scadenza;
		this.eUnaFatturaCliente = eUnaFatturaCliente;
		this.persona = persona;
		this.nota = nota;
		// this.articoli = articoli;
		// this.numeroFattura = numeroFattura;
		// this.iva = iva;
		// this.lordo = lordo;
		this.pagata = pagata;
		this.conto = conto;
	}

	public Integer getIdFattura() {
		return id;
	}

	public void setIdFattura(Integer idFattura) {
		this.id = idFattura;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getScadenza() {
		return scadenza;
	}

	public void setScadenza(Integer scadenza) {
		this.scadenza = scadenza;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public List<Articolo> getArticolo() {
		return articoli;
	}

	public void setArticolo(List<Articolo> articolo) {
		this.articoli = articolo;
	}

	public String getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public float getIva() {
		return iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean iseUnaFatturaCliente() {
		return eUnaFatturaCliente;
	}

	public void seteUnaFatturaCliente(boolean eUnaFatturaCliente) {
		this.eUnaFatturaCliente = eUnaFatturaCliente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public float getLordo() {
		return lordo;
	}

	public void setLordo(float lordo) {
		this.lordo = lordo;
	}

	public List<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}

	public boolean isPagata() {
		return pagata;
	}

	public void setPagata(boolean pagata) {
		this.pagata = pagata;
	}

	public Conto getConto() {
		return conto;
	}

	public void setConto(Conto conto) {
		this.conto = conto;
	}

}

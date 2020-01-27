package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "fattura")
public class Fattura {
	
	public Fattura() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Date data;
	
	@Column(name = "scadenza")
	private Integer scadenza;
	
	@Column(name = "e_una_fattura_cliente")
	private boolean eUnaFatturaCliente;
	//	ne DB usiamo un dato di tipo 'BIT'
	//	0 = false
	//	1 = true
	
	@ManyToOne
	private Persona persona;
	
	@Column(name = "nota")
	private String nota;
	
	@OneToMany(mappedBy = "fattura")
	private List<Articolo> articolo;
	
	/*
	@ManyToOne
	private Conto conto;
	*/
	
	@Column(name = "numero_fattura")
	private String numeroFattura;
	
	@Transient
	private float iva = 0.22F;
	
	@Column(name = "lordo")
	private float lordo = 0;

	/*
	@ManyToOne
	private MetodoDiPagamento metodoDiPagamento;
	
	@OneToOne
	private Pagamento pagamento;
	*/
	
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
		return articolo;
	}
	public void setArticolo(List<Articolo> articolo) {
		this.articolo = articolo;
	}
	
	/*
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
	}
	*/
	
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
	public float getSaldoDovuto() {
		return lordo;
	}
	public void setSaldoDovuto(float saldoDovuto) {
		this.lordo = saldoDovuto;
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
	
	/*
	public MetodoDiPagamento getMetodoDiPagamento() {
		return metodoDiPagamento;
	}
	public void setMetodoDiPagamento(MetodoDiPagamento metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	*/
	
}

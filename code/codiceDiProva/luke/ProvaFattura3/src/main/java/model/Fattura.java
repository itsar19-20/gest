package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fattura")
@NamedQuery(name="fattura.findAll", query="SELECT f FROM Fattura f")
public class Fattura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id")
	private Integer idFattura;
	private String dataFattura;
	private double importo;
	private Integer scadenza;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destinatario_id", referencedColumnName = "id")
	private FornitoreCliente destinatario;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mittente_id", referencedColumnName = "id")
	private FornitoreCliente mittente;
	private String nota;
	@OneToMany
	@JoinColumn(name="fattura_id")
	private List<Articolo> articolo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conto_id", referencedColumnName = "id")
	private Conto conto;
	private float IVA;
	private float saldoDovuto;
	private String numeroFattura;
	@OneToOne(mappedBy = "fattura")
	private Pagamento pagamento;
	
	public Integer getIdFattura() {
		return idFattura;
	}
	public void setIdFattura(Integer idFattura) {
		this.idFattura = idFattura;
	}
	public String getDataFattura() {
		return dataFattura;
	}
	public void setDataFattura(String data) {
		this.dataFattura = data;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public Integer getScadenza() {
		return scadenza;
	}
	public void setScadenza(Integer scadenza) {
		this.scadenza = scadenza;
	}
	public FornitoreCliente getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(FornitoreCliente destinatario) {
		this.destinatario = destinatario;
	}
	public FornitoreCliente  getMittente() {
		return mittente;
	}
	public void setMittente(FornitoreCliente mittente) {
		this.mittente = mittente;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
	}
	public List<Articolo> getArticolo() {
		return articolo;
	}
	public void setArticolo(List<Articolo> articolo) {
		this.articolo = articolo;
	}
	public float getIVA() {
		return IVA;
	}
	public void setIVA(float iVA) {
		IVA = iVA;
	}
	public float getSaldoDovuto() {
		return saldoDovuto;
	}
	public void setSaldoDovuto(float saldoDovuto) {
		this.saldoDovuto = saldoDovuto;
	}
	public String getNumeroFattura() {
		return numeroFattura;
	}
	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}
	

}

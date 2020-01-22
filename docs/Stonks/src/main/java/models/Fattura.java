package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

@Entity
public class Fattura {
	
	public Fattura() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date data;
	private Integer scadenza;
	/*	Uilizzare solo l'attributo persona e ricavare se e' il mittente o il destinatario 
		della selezione di fattura cliente oppure fornitore

	private Persona mittente;
	private Persona destinatario;
	*/
	private boolean fatturaCliente;	//	true per cliente, false per fornitore
	private Persona persona;
	private String nota;
	private List<Articolo> articolo;
	private Conto conto;
	private String numeroFattura;
	private float iva = 0.22F;
	private float lordo;
	private MetodoDiPagamento metodoDiPagamento;
	
	public Fattura(Integer idFattura, Date data, Integer scadenza, Persona mittente, Persona destinatario, String nota,
			List<Articolo> articolo, Conto conto, String numeroFattura, float iva, float saldoDovuto,
			MetodoDiPagamento metodoDiPagamento) {
		super();

	}

	
	public void immettiFattura() {
		
	}
	public List<Fattura> mostraFattura() {
		return null;
		
	}
	public void modificaFattura() {
		
	}
	public void immettiNotaDiCredito() {
		
	}
	public Integer sommaParzialeArticoli() {
		return id;
		
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
		return articolo;
	}
	public void setArticolo(List<Articolo> articolo) {
		this.articolo = articolo;
	}
	public Conto getConto() {
		return conto;
	}
	public void setConto(Conto conto) {
		this.conto = conto;
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
	public float getSaldoDovuto() {
		return lordo;
	}
	public void setSaldoDovuto(float saldoDovuto) {
		this.lordo = saldoDovuto;
	}
	public MetodoDiPagamento getMetodoDiPagamento() {
		return metodoDiPagamento;
	}
	public void setMetodoDiPagamento(MetodoDiPagamento metodoDiPagamento) {
		this.metodoDiPagamento = metodoDiPagamento;
	}
}

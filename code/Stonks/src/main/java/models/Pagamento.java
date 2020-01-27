
package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pagamento")
@NamedQuery(name="pagamento.findAll", query="SELECT pa FROM Pagamento pa")
public class Pagamento {
	
	//private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name="id")
	private Integer idPagamento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fattura_id", referencedColumnName = "id")
	private Fattura fattura;
	
	private Date dataPagamento;
	private float giaPagato;
	private boolean pagato;
	//private Scadenziario scadenziario;
	
	
	public Pagamento() {
		this.giaPagato=0;
		this.pagato=false;
		
	}
	
	
	public Pagamento(Fattura fattura) {
		this.fattura=fattura;
		//this.dataPagamento="non pagata;
		this.giaPagato=0;
		this.pagato=false;
		
		
		
		}
	
	
	
	
	public Integer getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
	public Fattura getFattura() {
		return fattura;
	}
	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public float getGiaPagato() {
		return giaPagato;
	}
	public void setGiaPagato(float mancante) {
		this.giaPagato = mancante;
	}
	
	public boolean isPagato() {
		return pagato;
	}
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}
	
	
	//public Scadenziario getScadenziario() {
	//	return scadenziario;
	//}

}

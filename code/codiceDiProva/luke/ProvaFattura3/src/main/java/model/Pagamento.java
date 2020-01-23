package model;

import java.io.Serializable;

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
	private String dataPagamento;
	private float mancante;
	private boolean pagato;
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
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public float getMancante() {
		return mancante;
	}
	public void setMancante(float mancante) {
		this.mancante = mancante;
	}
	public boolean isPagato() {
		return pagato;
	}
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

}

package model;

import java.sql.Date;

public class Pagamento {

	private Integer idPagamento;
	private Fattura fattura;
	private Date data;
	private float mancante;
	private boolean pagato = false;

	public Pagamento(Integer idPagamento, Fattura fattura, Date data, float mancante, boolean pagato) {
		super();
		this.idPagamento = idPagamento;
		this.fattura = fattura;
		this.data = data;
		this.mancante = mancante;
		this.pagato = pagato;
	}
	
	public void parziale() {
		
	}
	public void saldato() {
		
	}
	public void annullato() {
		
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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

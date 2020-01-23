package entita;

import entita.Persona;
//@Entity
public class Conto {
//	@Id
	private int idConto;
	private Class<Persona> p = Persona.class;
	private float saldoUtile;
	private float saldoContabile;
	private MetodoDiPagamento mdp = new MetodoDiPagamento(null, null, null);
	
	
	
	
	
	public int getIdConto() {
		return idConto;
	}
	public void setIdConto(int idConto) {
		this.idConto = idConto;
	}
	public Class<Persona> getP() {
		return p;
	}
	public void setP(Class<Persona> p) {
		this.p = p;
	}
	public float getSaldoUtile() {
		return saldoUtile;
	}
	public void setSaldoUtile(float saldoUtile) {
		this.saldoUtile = saldoUtile;
	}
	public float getSaldoContabile() {
		return saldoContabile;
	}
	public void setSaldoContabile(float saldoContabile) {
		this.saldoContabile = saldoContabile;
	}
	public MetodoDiPagamento getMdp() {
		return mdp;
	}
	public void setMdp(MetodoDiPagamento mdp) {
		this.mdp = mdp;
	}
	
	
	
}

package fattura;

public class Descrizione {
	
	private String idDescrizione;
	private String descrizione;
	private Integer numPezzi;
	private Integer totParziale;
	
	public Descrizione(String idDescrizione, String descrizione, Integer numPezzi, Integer totParziale) {
		super();
		this.idDescrizione = idDescrizione;
		this.descrizione = descrizione;
		this.numPezzi = numPezzi;
		this.totParziale = totParziale;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getNumPezzi() {
		return numPezzi;
	}
	public void setNumPezzi(Integer numPezzi) {
		this.numPezzi = numPezzi;
	}
	public Integer getTotParziale() {
		return totParziale;
	}
	public void setTotParziale(Integer totParziale) {
		this.totParziale = totParziale;
	}
	public String getIdDescrizione() {
		return idDescrizione;
	}
	public void setIdDescrizione(String idDescrizione) {
		this.idDescrizione = idDescrizione;
	}

}

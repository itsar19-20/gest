package model;

@Entity
public class Articolo {

	@Id
	private Integer idArticolo;
	private String descrizione;
	private Integer quantita;
	private float importoParziale;
	private float iva = 0.22f;

	public Articolo(Integer idArticolo, String descrizione, Integer quantita, float importoParziale, float iva) {
		super();
		//	this.idArticolo = idArticolo;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.importoParziale = importoParziale;
		//	this.iva = iva;
	}
	
	public void aggiungi() {
		
	}
	public void elimina() {
		
	}
	
	public Integer getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public float getImportoParziale() {
		return importoParziale;
	}
	public void setImportoParziale(float importoParziale) {
		this.importoParziale = importoParziale;
	}
	public float getIva() {
		return iva;
	}
	public void setIva(float iva) {
		this.iva = iva;
	}
	
}

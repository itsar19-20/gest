package fattura;

import java.sql.Date;
import java.util.List;

public class Fattura {
	
	private String idFattura;
	private Date data;
	private Integer scadenza = 0;
	private float importo;
	private String mittente;
	private String destinatario;
	private String nota;
	private List<Descrizione> descrizione;
	private String idCassa;
	
	public Fattura(String idFattura, Date data, Integer scadenza, float importo, String mittente, String destinatario,
			String nota, List<Descrizione> descrizione, String idCassa) {
		super();
		this.idFattura = idFattura;
		this.data = data;
		this.scadenza = scadenza;
		this.importo = importo;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.nota = nota;
		this.descrizione = descrizione;
		this.idCassa = idCassa;
	}
	
	public Fattura fatturaCliente(Date data, Integer scadenza, float importo, String mittente, String nota, List<Descrizione> descrizione, String idCassa) {
		this.data = data;
		this.scadenza = scadenza;
		this.importo = importo;
		this.mittente = mittente;
		this.destinatario = idPersona;
		this.nota = nota;
		this.descrizione = descrizione;
		this.idCassa = idCassa;
		return null;
	}
	
	public Fattura fatturaFornitore(Date data, Integer scadenza, float importo, String destinatario, String nota, List<Descrizione> descrizione, String idCassa) {
		this.data = data;
		this.scadenza = scadenza;
		this.importo = importo;
		this.mittente = idPersona;
		this.destinatario = destinatario;
		this.nota = nota;
		this.descrizione = descrizione;
		this.idCassa = idCassa;
		return null;
	}
	
	public void fatturaFornitore() {
		
	}
	
	public String getIdFattura() {
		return idFattura;
	}
	public void setIdFattura(String idFattura) {
		this.idFattura = idFattura;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public String getMittente() {
		return mittente;
	}
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public List<Descrizione> getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(List<Descrizione> descrizione) {
		this.descrizione = descrizione;
	}
	public String getIdCassa() {
		return idCassa;
	}
	public void setIdCassa(String idCassa) {
		this.idCassa = idCassa;
	}
	public Integer getScadenza() {
		return scadenza;
	}
	public void setScadenza(Integer scadenza) {
		this.scadenza = scadenza;
	}
	
}
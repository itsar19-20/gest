package entita;
//@Entity
public class MetodoDiPagamento {
//	@Id
	private String metodo;
	private String nome;
	private Class<Conto> destinazione = Conto.class;
	
	public MetodoDiPagamento(String nome, String metodo, Class<Conto> destinazione) {
		this.nome = nome;
		this.metodo = metodo;
		this.destinazione = destinazione;
	}
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Class<Conto> getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(Class<Conto> destinazione) {
		this.destinazione = destinazione;
	}

}

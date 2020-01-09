package Users;

public class Utente extends persona {

	private String metodoRegistrazione;
	private String pIva;
	
	public void registrati() {
		System.out.println();
	}
	
	
	public String getMetodoRegistrazione() {
		return metodoRegistrazione;
	}
	public void setMetodoRegistrazione(String metodoRegistrazione) {
		this.metodoRegistrazione = metodoRegistrazione;
	}
	public String getpIva() {
		return pIva;
	}
	public void setpIva(String pIva) {
		this.pIva = pIva;
	}
}

package Users;

public class amministratore extends persona {

	private int codiceAdmin;
	private String telefono;
	
	
	public int getCodiceAdmin() {
		return codiceAdmin;
	}
	public void setCodiceAdmin(int codiceAdmin) {
		this.codiceAdmin = codiceAdmin;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}

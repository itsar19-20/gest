package entita;

public class Admin extends Users {
	
	private String codiceAdmin;
	public Admin(int idPersona, String nome, String cognome, String pIVA, String mail, String indirizzo,
			String telefono, String username, String password,String codiceAdmin) {
		super(idPersona, nome, cognome, pIVA, mail, indirizzo, telefono, username, password);
		this.codiceAdmin = codiceAdmin;
		// TODO Auto-generated constructor stub
	}
	public String getCodiceAdmin() {
		return codiceAdmin;
	}
	public void modificaCodiceAdmin(String codiceAdmin1, String password, String codAdmN1, String codAdmN2) {
		if(this.codiceAdmin == codiceAdmin1) {
			if(this.getPassword() == password) {
				if(codAdmN1 == codAdmN2) {
					this.codiceAdmin = codAdmN1;
				} else {System.out.println("I due codici nuovi non corrispondo.");}
		} else {System.out.println("La Password è errata.");}
	} else {System.out.println("Il codice admin non è corretto.");}

	}
}

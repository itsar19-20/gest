package entita;

//@Entity
public class Users extends Persona{
	
	//	@Id
	private String username;
	private String password;

	public Users(int idPersona, String nome, String cognome, String pIVA, String mail, String indirizzo, 
				String telefono, String username, String password) {
		super(idPersona, nome, cognome, pIVA, mail, indirizzo, telefono);
		this.username = username;
		this.password = password;
//		 TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void modificaPassword(String passwordVecchia, String pass1, String pass2) {
		if(this.password == passwordVecchia) {
			if(pass1 == pass2) {
				this.password = pass1;
			}else {System.out.println("Le due password non corrispondono");}
				
		}else{System.out.println("La vecchia password è errata, riprova");}
			
	}

}

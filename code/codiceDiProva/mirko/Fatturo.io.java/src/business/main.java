package business;

import java.util.Scanner;

import entita.Persona;
import entita.Utente;

public class main {
	
	public static void main(String[] args){
		
		String nome=null;
		String cognome=null;
		String email=null;
		String pIVA=null;
		String telefono=null;
		String indirizzo=null;

		String username=null;
		String password=null;
		
		Persona p = new Persona(0, nome, cognome, email, pIVA, telefono, indirizzo);
		Utente U = new Utente(0, nome, cognome, email, pIVA, telefono, indirizzo, username, password);
		Scanner s = new Scanner(System.in);
		String sc;
		
		do {
			System.out.println("scegli voce!");
			System.out.println("1 inserisci nuova persona");
			System.out.println("2 inserisci nuovo utente");
			System.out.println("3 modifica persona");
			System.out.println("0 exit");
			sc = s.nextLine();
			
		switch(sc) {
		case (String) "1" :{
			System.out.println("Inserisci nome");
			nome = s.nextLine();
			System.out.println("Inserisci cognome");
			cognome = s.nextLine();
			System.out.println("Inserisci email");
			email = s.nextLine();
			System.out.println("Inserisci Partita Iva");
			pIVA = s.nextLine();
			System.out.println("Inserisci telefono");
			telefono = s.nextLine();
			System.out.println("Inserisci indirizzo");
			indirizzo = s.nextLine();
			p = new Persona(0, nome, cognome, email, pIVA, indirizzo, telefono);
			System.out.println("Hai inserito la persona " + nome);			
		}break;
		case (String) "2": {
			System.out.println("Inserisci nome");
			nome = s.nextLine();
			System.out.println("Inserisci cognome");
			cognome = s.nextLine();
			System.out.println("Inserisci email");
			email = s.nextLine();
			System.out.println("Inserisci Partita Iva");
			pIVA = s.nextLine();
			System.out.println("Inserisci telefono");
			telefono = s.nextLine();
			System.out.println("Inserisci indirizzo");
			indirizzo = s.nextLine();
			System.out.println("Inserisci username");
			username = s.nextLine();
			System.out.println("Inserisci password");
			password = s.nextLine();
			U = new Utente(0, nome, cognome, email, pIVA,indirizzo, telefono, username, password);
			System.out.println("Hai inserito l'utente " + nome);
		}break;
		case (String) "3": {
			System.out.println("Inserisci nome");
			nome = s.nextLine();
			System.out.println("Inserisci cognome");
			cognome = s.nextLine();
			System.out.println("Inserisci email");
			email = s.nextLine();
			System.out.println("Inserisci Partita Iva");
			pIVA = s.nextLine();
			System.out.println("Inserisci telefono");
			telefono = s.nextLine();
			System.out.println("Inserisci indirizzo");
			indirizzo = s.nextLine();
			p.modificaPersona(nome, cognome, email, pIVA, indirizzo, telefono);
			System.out.println("Hai inserito la persona " + nome);
		}break;
		case (String) "0": {
			System.out.println("Ciao ci vediamo la prossima volta");
			
		}break;
		default: {
			System.out.println("Hai inserito un dato sbagliato");
			
		}
		}
		
		}while(sc != "0");

	}
	}



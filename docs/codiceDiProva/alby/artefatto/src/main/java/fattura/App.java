package fattura;

import java.util.Scanner;

public class App {
	
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        pl("Hello World!");
        
        l(); pl("CREA UNA NUOVA FATTURA"); l();
        Scanner input = new Scanner(System.in);
        pl("vuoi creare una fattura cliente (c) o una fattura fornitore (f)?");
        
        //	cliente o fornitore?
        //	costruire la fattura (senza idPersona)
        		//	aggiungere tre descrizioni
        //	aggiungere la fattura al database
        
        // stampare una fattura in basa all'id
    }
    
    private static void l() {
		System.out.println();
	}

	public static void pl(String str) {
    	System.out.println(str);
    }
    
    public static void p(String str) {
    	System.out.print(str);
    }
    
}

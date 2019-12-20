package fattura;

import java.util.Scanner;

public class App {
	
	private static String input;
	private String menuCerca = "menuCerca";
	private static String menuCrea = "Premi [c] per cliente o [f] per fornitore: ";
	private static boolean mCrea;
	
    public static void main( String[] args ) {
        Scanner s = new Scanner(System.in);
        menuCrea(s);
        //	cliente o fornitore?
        //	costruire la fattura (senza idPersona)
        		//	DAO?
        		//	aggiungere tre descrizioni
        //	aggiungere la fattura al database
        
        // stampare una fattura in base all'id
    }
    
    private static void menuCrea(Scanner s) {
    	l(); pl("CREA UNA NUOVA FATTURA"); l();
		pl("Vuoi creare una fattura cliente o una fattura fornitore?");
        p(menuCrea); in(s);
        mCrea = true;
        while (mCrea) {
        	
	        switch (input) {
		        case "c": cliente(); mCrea = false; break;
		        case "f": fornitore(); mCrea = false; break;
		        default: l(); pl("COMANDO [" +input+ "] NON TROVATO"); p(menuCrea); in(s);
	        }
        }

        l();
        pl(input);
	}

	private static void cliente() {
		// TODO Auto-generated method stub		
	}

	private static void fornitore() {
		// TODO Auto-generated method stub		
	}

	private static void in(Scanner s) {
    	input = s.next();
    }
    
    private static void l() {
		System.out.println();
	}

	private static void pl(String str) {
    	System.out.println(str);
    }
    
    private static void p(String str) {
    	System.out.print(str);
    }
    
}

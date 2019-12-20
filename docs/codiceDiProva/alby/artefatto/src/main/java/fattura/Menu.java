package fattura;

import java.sql.SQLException;

public class Menu extends Console {

    static void crea() {
    	String menu = "Premi [c] per cliente o [f] per fornitore: ";
    	l(); pl("CREA UNA NUOVA FATTURA"); l();
		pl("Vuoi creare una fattura cliente o una fattura fornitore?");
        p(menu); in();
        boolean m = true;
        while (m) {
	        switch (input) {
		        case "c": Fattura.immettiFCliente(); m = false; break;
		        case "f": Fattura.immettiFFornitore(); m = false; break;
		        default: cmdError(menu);
	        }
        }

        l();
        pl(input);
	}

	public static void cerca() throws ClassNotFoundException, SQLException {
    	String menu = "Premi [t] per vedere tutte le fatture, oppure [i] per cercarla per id o [m] per mittente: ";
    	l(); pl("CERCA UNA FATTURA"); l();
        p(menu); in();
        boolean m = true;
        while (m) {
	        switch (input) {
	        	case "t": Database.query("all"); m = false; break;
		        case "i": Database.query("id"); m = false; break;
		        case "m": Database.query("mittente"); m = false; break;
		        default: cmdError(menu);
	        }
        }

        l();
        pl(input);		
	}
	
	public static void generale() throws ClassNotFoundException, SQLException {
    	String menu = "Premi [m] per creare una nuova fattura o [f] per cercarne una esistente: ";
    	l(); pl("MENU FATTURE"); l();
        p(menu); in();
        boolean m = true;
        while (m) {
	        switch (input) {
	        	case "f": cerca(); m = false; break;
		        case "m": crea(); m = false; break;
		        default: cmdError(menu);
	        }
        }

        l();
        pl(input);		
	}
	
}

package fattura;

import java.util.Scanner;

public class Console {
	
	protected static String input;
    
	protected static void l() {
		pl("");
	}

	protected static void pl(String str) {
    	System.out.println(str);
    }
    
	protected static void p(String str) {
    	System.out.print(str);
    }
	
	protected static void in() {
        Scanner s = new Scanner(System.in);
    	input = s.next().toLowerCase();
    }
	
	protected static void cmdError(String menu) {
		l(); pl("COMANDO [" +input+ "] NON TROVATO"); p(menu); in();
	}
	
}

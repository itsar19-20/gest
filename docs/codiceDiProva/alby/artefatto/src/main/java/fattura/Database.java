package fattura;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	public static void connessione(String query) throws ClassNotFoundException, SQLException {
		
		//	caricare il driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//	configurare la connessione
		String host = "localhost";
		String door = "3306";
		String database = "fatturo_io";
		String user = "root";
		String password = "";
		
		//	creare la connessione
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+door+"/"+database, user, password);
		
		//	inviare comandi SQL
		Statement st = conn.createStatement();
		ResultSet dati = st.executeQuery(query);
		
		//	leggere i risultati
		while (dati.next()) {
			System.out.println(dati.getString("mittente") + "   " + dati.getString("importo"));
		}
		
		//	terminare la connessione
		conn.close();
		
	}

	public static void query(String input) throws ClassNotFoundException, SQLException {
		
		//	configurazione
		String tabella = " from fattura";
		String fai = "select ";
		String where = "";
		
		//comporre la query
		switch (input) {
			case "all":  fai += "*";break;
			case "id": fai += "*"; break;
			case "mittente": fai += "*"; break;
			default: 
		}
		String query = fai+tabella+where;
		Console.pl(query);
		
		//	 fare la query al database
		connessione(query);
		
	}
	
}

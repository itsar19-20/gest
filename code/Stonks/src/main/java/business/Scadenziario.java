package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import models.Conto;
import models.Pagamento;
import models.Persona;
import models.Utente;
import utils.DateUtil;
import utils.JPALuke;



public class Scadenziario {
	
	//private List<Pagamento> pagamentiDaConcludere;
	
	//private List<Pagamento> entrataDaConcludere;
	
	//private List<Pagamento> uscitaDaConcludere;
	
	private int anticipoNotifica;
	
	public Scadenziario() {
		
	}
	

	
	public int getAnticipoNotifica() {
		return 5;
	}

	public void setAnticipoNotifica(int anticipoNotifica) {
		
	}
	public static List<Pagamento> showFullScadenziario(Persona persona) {
		
		List<Pagamento> pagamentiDaConcludere=JPALuke.selectPagamenti(persona);
		
		return pagamentiDaConcludere;
	}
	
	public static List<Pagamento> showEntrataDaConcludere(Persona pers, boolean bol) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,bol);
		
		return entrataDaConcludere;
	}
	
	
	
	
	
	public static List<Pagamento> showScadenziarioMese(Persona pers,Integer mese) {
		
				List<Pagamento> scadenzaMese=JPALuke.selectPagamenti(pers);
				scadenzaMese=Scadenziario.searchByMese(mese, scadenzaMese);
				return scadenzaMese;
				
	}
	
	
	
	public static List<Pagamento> showScadenziarioSettimana(Persona pers,Integer settimana) {
		List<Pagamento> scadenzaSettimana= JPALuke.selectPagamenti(pers);
		scadenzaSettimana=Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		return scadenzaSettimana;
	}
	
	public static List<Pagamento> showMeseEntrata(Persona pers,Integer mese,boolean bol) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,bol);
		entrataDaConcludere=Scadenziario.searchByMese(mese, entrataDaConcludere);
		return entrataDaConcludere;
	}
	
	public static List<Pagamento> showSettimanaEntrata(Persona pers, Integer settimana,boolean bol) {
		List<Pagamento> scadenzaSettimana=JPALuke.selectParziale(pers,bol);
		scadenzaSettimana=Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		return scadenzaSettimana;
	}
	
	
	
	private static List<Pagamento> searchByMese(Integer mese, List<Pagamento> scadenzaMese) {
		Date dataCorrente=new Date();
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataCorrente);
		List<Pagamento> result=new ArrayList<>();
		
		// ricerca in base a N mesi successivi a quello corrente delle scadenze fatture
		for(Pagamento pag:scadenzaMese) {
			
			Calendar c=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			c.setTime(pag.getFattura().getData());
			
			Calendar d=(Calendar) cal.clone();
			c.add(Calendar.DAY_OF_YEAR,+pag.getFattura().getScadenza() );
			Scadenziario.calcolaMese(c, d, mese, pag,result);
		}
		return result;
	}
	
	private static List<Pagamento> searchBySettimana(Integer settimana,List<Pagamento> scadenzaSettimana) {
		Date dataCorrente=new Date();
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataCorrente);
		List<Pagamento> result=new ArrayList<>();
		for(Pagamento pag:scadenzaSettimana) {
			Calendar c=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			c.setTime(pag.getFattura().getData());
			Calendar d=(Calendar) cal.clone();
			c.add(Calendar.DAY_OF_YEAR, +pag.getFattura().getScadenza());
			Scadenziario.calcolaSettimana(c, d, settimana, pag,result);
		}
		
		return result;
		
	}
	
	
	private static void calcolaMese(Calendar c, Calendar d,Integer mese, Pagamento pag,List<Pagamento> result) {
		d.add(Calendar.MONTH , +mese);
		
		if(c.get(Calendar.MONTH)==d.get(Calendar.MONTH)) {
			result.add(pag);
	}
	}
	private static void calcolaSettimana(Calendar c,Calendar d, Integer settimana,Pagamento pag,List<Pagamento> result) {
		d.add(Calendar.WEEK_OF_YEAR, +settimana);
		if(c.get(Calendar.WEEK_OF_YEAR)==d.get(Calendar.WEEK_OF_YEAR)) {
			
			result.add(pag);
		}
	}
	
	
	private static void searchForNotifica(List<Pagamento> pagamentiDaConcludere) {

		Date dataOggi=new Date();
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataOggi);
		
		for(Pagamento pag:pagamentiDaConcludere) {
			Calendar calFattura=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			
			calFattura.setTime(pag.getFattura().getData());
			Calendar calCorrente=(Calendar) cal.clone();
			//
			// l'anticipo notifica va memorizzato nel local Storage, per ora uso 5
			calCorrente.add(Calendar.DAY_OF_YEAR, +5 ); 
			calFattura.add(Calendar.DAY_OF_YEAR, +pag.getFattura().getScadenza());
			if(calCorrente.getTime().after(calFattura.getTime())) {
				System.out.println("La fattura di id: "+pag.getFattura().getIdFattura()+" sta per scadere");
			}	
		}
	}
	
	
	
	
	public static void checkNotifica(Persona pers) {
		System.out.println("Entro dove non chiede boolean");
		List<Pagamento> pagamentiDaConcludere=JPALuke.selectPagamenti(pers);
		Scadenziario.searchForNotifica(pagamentiDaConcludere);	
		
		}
	
		public static void checkNotifica(Persona pers ,boolean entrataUscita) {
			System.out.println("Entro dove chiede boolean");
			List<Pagamento> listaPagamento=JPALuke.selectParziale(pers,entrataUscita);
			Scadenziario.searchForNotifica(listaPagamento);			
		}
	}




	
	



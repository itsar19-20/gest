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
		/*for(Pagamento p:pagamentiDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().iseUnaFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}*/
		return pagamentiDaConcludere;
	}
	
	public static List<Pagamento> showEntrataDaConcludere(Persona pers, boolean bol) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,bol);
		/*for(Pagamento p:entrataDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().iseUnaFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());		
		}*/
		return entrataDaConcludere;
	}
	
	
	
	
	
	public static void showScadenziarioMese(Persona pers,Integer mese) {
		//select tutti i pagamenti dell'utnte non ancora completati
				List<Pagamento> scadenzaMese=JPALuke.selectPagamenti(pers);
				Scadenziario.searchByMese(mese, scadenzaMese);
				
	}
	
	
	
	public static void showScadenziarioSettimana(Persona pers,Integer settimana) {
		List<Pagamento> scadenzaSettimana= JPALuke.selectPagamenti(pers);
		Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		
	}
	
	public static void showMeseEntrata(Persona pers,Integer mese,boolean bol) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,bol);
		Scadenziario.searchByMese(mese, entrataDaConcludere);
		
	}
	
	public static void showSettimanaEntrata(Persona pers, Integer settimana,boolean bol) {
		List<Pagamento> scadenzaSettimana=JPALuke.selectParziale(pers,bol);
		Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		
	}
	
	
	
	private static void searchByMese(Integer mese, List<Pagamento> scadenzaMese) {
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
			Scadenziario.calcolaMese(c, d, mese, pag);
		}
	}
	
	private static void searchBySettimana(Integer settimana,List<Pagamento> scadenzaSettimana) {
		Date dataCorrente=new Date();
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataCorrente);
		for(Pagamento pag:scadenzaSettimana) {
			Calendar c=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			c.setTime(pag.getFattura().getData());
			Calendar d=(Calendar) cal.clone();
			c.add(Calendar.DAY_OF_YEAR, +pag.getFattura().getScadenza());
			Scadenziario.calcolaSettimana(c, d, settimana, pag);
		}
		
	}
	
	
	private static void calcolaMese(Calendar c, Calendar d,Integer mese, Pagamento pag) {
		d.add(Calendar.MONTH , +mese);
		
		if(c.get(Calendar.MONTH)==d.get(Calendar.MONTH)) {
			System.out.println("id: "+pag.getIdPagamento()+"\nData Fattura: "+pag.getFattura().getData()+
					"\nScadenza fattura: "+ pag.getFattura().getScadenza());
		}	
	}
	
	private static void calcolaSettimana(Calendar c,Calendar d, Integer settimana,Pagamento pag) {
		d.add(Calendar.WEEK_OF_YEAR, +settimana);
		if(c.get(Calendar.WEEK_OF_YEAR)==d.get(Calendar.WEEK_OF_YEAR)) {
			System.out.println("id: "+pag.getIdPagamento()+"\nData Fattura: "+pag.getFattura().getData()+
					"\nScadenza fattura: "+ pag.getFattura().getScadenza());
			
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




	
	



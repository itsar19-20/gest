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
	
	
	
	
	
	
	
	
	
	
	public static void showEntrataDaConcludere(Persona pers) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,true);
		for(Pagamento p:entrataDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().iseUnaFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
		
		
		
		
	}

	

	public static void  showUscitaDaConcludere(Persona pers) {
		List<Pagamento> entrataDaConcludere=JPALuke.selectParziale(pers,false);
		for(Pagamento p:entrataDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().iseUnaFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
		
		
	}
	
	//   
	public static void mesePagamento(Integer mese) {
		
	}
	
	

	public int getAnticipoNotifica() {
		return 5;
	}


	public void setAnticipoNotifica(int anticipoNotifica) {
		
	}
	
	public static void showFullScadenziario(Persona persona) {
		
		List<Pagamento> pagamentiDaConcludere=JPALuke.selectPagamenti(persona);
		for(Pagamento p:pagamentiDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().iseUnaFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
	}
	
	public void showScadenziarioMese(Persona pers,Integer mese) {
		//select tutti i pagamenti dell'utnte non ancora completati
				List<Pagamento> scadenzaMese=JPALuke.selectPagamenti(pers);
				Date dataCorrente=new Date();
				Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
				cal.setTime(dataCorrente);
				
				// ricerca in base a N mesi successivi a quello corrente delle scadenze fatture
				for(Pagamento pag:scadenzaMese) {
					Date dataFattura=pag.getFattura().getData();
					int scadenza=pag.getFattura().getScadenza();
					Calendar c=Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
					c.setTime(dataFattura);
					
					Calendar d=(Calendar) cal.clone();
					c.add(Calendar.DAY_OF_YEAR,+scadenza );
					d.add(Calendar.MONTH , +mese);
					
					if(c.get(Calendar.MONTH)==d.get(Calendar.MONTH)) {
						System.out.println("id: "+pag.getIdPagamento()+"\nData Fattura: "+pag.getFattura().getData()+
								"\nScadenza fattura: "+ pag.getFattura().getScadenza());
					}
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
		
		
	}
	
	
	
	

	
	public void checkLoginNotifica() {
			
		}
		
	}




	
	



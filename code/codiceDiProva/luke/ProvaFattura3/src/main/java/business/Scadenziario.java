package business;

import java.util.ArrayList;
import java.util.List;

import model.Conto;
import model.Pagamento;
import model.Persona;
import model.Utente;
import utils.DateUtil;
import utils.JPAUtils;

public class Scadenziario {
	
	//private List<Pagamento> pagamentiDaConcludere;
	
	//private List<Pagamento> entrataDaConcludere;
	
	//private List<Pagamento> uscitaDaConcludere;
	
	private int anticipoNotifica;
	
	
	
	
	
	
	public Scadenziario() {
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void showEntrataDaConcludere(Persona pers) {
		List<Pagamento> entrataDaConcludere=JPAUtils.selectParziale(pers,true);
		for(Pagamento p:entrataDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().isFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
		
		
		
		
	}

	

	public static void  showUscitaDaConcludere(Persona pers) {
		List<Pagamento> entrataDaConcludere=JPAUtils.selectParziale(pers,false);
		for(Pagamento p:entrataDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().isFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
		
		
	}
	
	//   metodo provvisorio per provare la ricerca del mese
	public static void mesePagamento(Integer mese) {
		List<Pagamento> scadenzaMese=JPAUtils.
	}
	
	

	public int getAnticipoNotifica() {
		return 5;
	}


	public void setAnticipoNotifica(int anticipoNotifica) {
		
	}
	
	public static void showFullScadenziario(Persona persona) {
		
		List<Pagamento> pagamentiDaConcludere=JPAUtils.selectPagamenti(persona);
		for(Pagamento p:pagamentiDaConcludere) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().isFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		
		}
	}
	
	public void showScadenziarioMese() {
		
	}
	
	
	
	

	
	public void checkLoginNotifica() {
			
		}
		
	}




	
	



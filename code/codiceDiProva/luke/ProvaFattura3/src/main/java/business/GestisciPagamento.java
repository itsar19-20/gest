package business;


import javax.persistence.EntityManager;

import model.Fattura;
import model.Pagamento;
import model.Utente;
import utils.JPAUtils;


public class GestisciPagamento {
	
	
	public static void segnalaPagamento(Pagamento p, double valoreEntrata, EntityManager em,Scadenziario s) {
		
		if(p.isPagato()==false) {
			
			if(p.getGiaPagato()+ valoreEntrata <p.getFattura().getLordo()) {
				JPAUtils.aggiornaGiaPagato(p,valoreEntrata,em);
			}else {
				JPAUtils.setCompletato(p,em);
				
				
				s.removePagamento(p);
			
				
				}
			}
			
			
			
		}
		
		
	public static void addNewPagamento(Pagamento p,Scadenziario s) {
		JPAUtils.persistPagamento(p);
		
		s.addPagamento(p);
	}
	

}

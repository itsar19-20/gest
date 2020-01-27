package business;


import javax.persistence.EntityManager;

import model.Fattura;
import model.Pagamento;
import model.Utente;
import utils.JPAUtils;


public class GestisciPagamento {
	
	
	public static void segnalaPagamento(Pagamento p, double valoreEntrata, EntityManager em) {
		
		if(p.isPagato()==false) {
			
			if(p.getGiaPagato()+ valoreEntrata <p.getFattura().getLordo()) {
				JPAUtils.aggiornaGiaPagato(p,valoreEntrata,em);
			}else {
				JPAUtils.setCompletato(p,em);
				
				
				
			
				
				}
			}
			
			
			
		}
		
		
	public static void addNewPagamento(Pagamento p) {
		JPAUtils.persistPagamento(p);
		
		
	}
	

}

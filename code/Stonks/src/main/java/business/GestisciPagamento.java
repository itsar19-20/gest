package business;


import javax.persistence.EntityManager;

import models.Fattura;
import models.Pagamento;
import models.Utente;
import utils.JPALuke;


public class GestisciPagamento {
	
	
	public static void segnalaPagamento(Pagamento p, double valoreEntrata, EntityManager em) {
		
		if(p.isPagato()==false) {
			
			if(p.getGiaPagato()+ valoreEntrata <p.getFattura().getLordo()) {
				JPALuke.aggiornaGiaPagato(p,valoreEntrata,em);
			}else {
				JPALuke.setCompletato(p,em);
				
				
				
			
				
				}
			}
			
			
			
		}
		
		
	public static void addNewPagamento(Pagamento p) {
		JPALuke.persistPagamento(p);
		
		
	}
	

}
package business;


import javax.persistence.EntityManager;

import models.Fattura;
import models.Pagamento;
import models.Utente;
import utils.JPALuke;


public class GestisciPagamento {
	
	
	public static void segnalaPagamento(Pagamento p, float valoreEntrata) {
		
		//if(p.isPagato()==false) {
			
			if(p.getGiaPagato()+ valoreEntrata <p.getFattura().getLordo()) {
				JPALuke.aggiornaGiaPagato(p,valoreEntrata);
			}else {
				JPALuke.setCompletato(p);
				
				
				}
			}
			
			
			
		//}
		
		
	public static void addNewPagamento(Integer idFattura, float valoreEntrata) {
		Fattura f=JPALuke.searchFattura(idFattura);
		Pagamento p=new Pagamento(f);
		//JPALuke.persistPagamento(p);
		segnalaPagamento(p,valoreEntrata);
		
	}
	

	
	
	

}

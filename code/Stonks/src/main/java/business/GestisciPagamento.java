package business;


import javax.persistence.EntityManager;

import models.Fattura;
import models.Pagamento;
import models.Utente;
import utils.JPALuke;


public class GestisciPagamento {
	
	
	public static Pagamento segnalaPagamento(Pagamento p, float valoreEntrata,EntityManager emTemp) {
		
		//if(p.isPagato()==false) {
			
			if(p.getGiaPagato()+ valoreEntrata <p.getFattura().getLordo()) {
				JPALuke.aggiornaGiaPagato(p,valoreEntrata,emTemp);
			}else {
				JPALuke.setCompletato(p,emTemp);
				
				
				}
			
				return p;
			}
			
			
			
		//}
		
		
	public static Pagamento addNewPagamento(Integer idFattura, float valoreEntrata,EntityManager emTemp) {
		Fattura f=JPALuke.searchFattura(idFattura);
		Pagamento p=new Pagamento(f);
		//JPALuke.persistPagamento(p);
		p=segnalaPagamento(p,valoreEntrata,emTemp);
		return p;
		
	}
	

	
	
	

}

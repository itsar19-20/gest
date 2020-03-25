package business;
 

import java.util.Date;

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
		
		
	public static Pagamento addNewPagamento(Integer idFattura, float valoreEntrata,EntityManager emTemp,EntityManager emFattura) {
		Fattura f=JPALuke.searchFattura(idFattura,emFattura);
		Pagamento p=new Pagamento(f);
		//JPALuke.persistPagamento(p);
		p=segnalaPagamento(p,valoreEntrata,emTemp);
		return p;
	}
	
	public static void modificaPagamento(Pagamento pagamento,float nuovoImporto,EntityManager em) {
		if(nuovoImporto>=pagamento.getFattura().getLordo() && pagamento.isPagato()==false) {
			pagamento.setPagato(true);
			pagamento.getFattura().setPagata(true);
			pagamento.setDataPagamento(new Date());
			pagamento.setGiaPagato(pagamento.getFattura().getLordo());
			} else if(nuovoImporto<pagamento.getFattura().getLordo() && pagamento.isPagato()==true) {
			pagamento.setPagato(false);
			pagamento.getFattura().setPagata(false);
			pagamento.setDataPagamento(null);
			pagamento.setGiaPagato(nuovoImporto);
			
			}else {
				pagamento.setGiaPagato(nuovoImporto);
				
			}
		em.getTransaction().begin();
		em.merge(pagamento);
		em.getTransaction().commit();
	}
	
	public static void cancellaPagamento(Pagamento pagamento,EntityManager em) {
		
		if(pagamento.isPagato()==true)
		{
			pagamento.getFattura().setPagata(false);
		}
		em.getTransaction().begin();
		em.remove(pagamento);
		em.getTransaction().commit();
		
	}

	
	
	

}

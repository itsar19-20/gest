package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.JPAUtil;
import utils.JPALuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import models.Admin;

import models.Articolo;
import models.Conto;
import models.Fattura;

import models.MetodoDiPagamento;
import models.Pagamento;
import models.Persona;
import models.Users;
import models.Utente;

public class ProvaMain {

	public static void main(String[] args) {
	

		
		
		
	
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		
		Utente u=em.find(Utente.class, 1);
		em.close();
		
		//Scadenziario s=new Scadenziario(u);
		//u.setScadenziario(s);
		//Scadenziario.showFullScadenziario(u);
		
		
		Scadenziario.showEntrataDaConcludere(u);
		
		Scadenziario.showUscitaDaConcludere(u);
		/*
		EntityManager em2=SingletonEMF.getInstance().getEmf().createEntityManager();
		
		Pagamento p= em2.find(Pagamento.class, 3);
		
		GestisciPagamento.segnalaPagamento(p, 100, em2);
		
		em2.close();
		Scadenziario.showFullScadenziario(u);
		
		EntityManager em3=SingletonEMF.getInstance().getEmf().createEntityManager();
		Conto c=em3.find(Conto.class, 1);
		em3.close();
		*/
		//EntityManager em4=SingletonEMF.getInstance().getEmf().createEntityManager();
		/*Fattura f=new Fattura();
		f.setConto(c);
		f.setPersona(u);
		f.setData(new Date());
		f.setFatturaCliente(true);
		f.setMetodoDiPagamento(p.getFattura().getMetodoDiPagamento());
		f.setLordo(100);
		
		
		
		//em4.getTransaction().begin();
		//em4.persist(f);
		//em4.getTransaction().commit();
		//em4.close();
		
		Pagamento p2=new Pagamento(f);
		GestisciPagamento.addNewPagamento(p2);
		
		
		Scadenziario.showFullScadenziario(u);
		
		*/
		
		
		
		
	
		
		
		
		/*for(Pagamento p2:s.getPagamentiDaConcludere()) {
			System.out.println("id: "+p2.getIdPagamento()+"\nData: "+p2.getFattura().getData()+
					"\nLordo: "+p2.getFattura().getLordo());
		}*/
		

		
		

	}

}

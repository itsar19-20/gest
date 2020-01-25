package utils;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Conto;
import model.Pagamento;
import model.Persona;
import model.Utente;

public class JPAUtils {
	
	//private List<Pagamento> listPagamento;
	
	
	
	public static List<Pagamento> selectPagamenti(Persona p) {
		EntityManager em=SingletonEMF.getInstance().getEmf().createEntityManager();
		TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.pagato = FALSE and pa.fattura.conto.persona.id=:id",Pagamento.class);
		//TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.pagato = FALSE and pa.fattura.conto.persona.id=:id and pa.fattura.destinatario.id=:id",Pagamento.class);
		query.setParameter("id", p.getIdPersona());
		List<Pagamento> listPagamento=query.getResultList();
		return listPagamento;
	}
	
	public static void aggiornaGiaPagato(Pagamento p,double daAggiornare, EntityManager em) {
		
		
		
		em.getTransaction().begin();
		p.setGiaPagato(p.getGiaPagato()+daAggiornare);
		em.getTransaction().commit();
	}
	
	public static void setCompletato(Pagamento p, EntityManager em) {
		
		Date dataPagamento=new Date();
		
		em.getTransaction().begin();
		p.setGiaPagato(p.getFattura().getLordo());
		p.setPagato(true);
		p.setDataPagamento(dataPagamento);
		em.getTransaction().commit();
	}
	public static void persistPagamento(Pagamento p) {
		EntityManager em=SingletonEMF.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}
	

}

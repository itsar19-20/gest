package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.Conto;
import models.Fattura;
import models.Pagamento;
import models.Persona;
import models.Utente;

public class JPALuke {
	
	//private List<Pagamento> listPagamento;
	
	
	
	public static List<Pagamento> selectPagamenti(Persona p) {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.pagato = FALSE and pa.fattura.conto.persona.id=:id",Pagamento.class);
		//TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.pagato = FALSE and pa.fattura.conto.persona.id=:id and pa.fattura.destinatario.id=:id",Pagamento.class);
		query.setParameter("id", p.getId());
		List<Pagamento> listCompleta=query.getResultList();
		return listCompleta;
	}
	
	public static List<Pagamento> selectParziale(Persona p,boolean x){
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.pagato = FALSE"
				+ " and pa.fattura.conto.persona.id=:id and pa.fattura.fatturaCliente=:si",Pagamento.class);
		query.setParameter("id",p.getId());
		query.setParameter("si",x);
		List<Pagamento> listEntrata=query.getResultList();
		
		
		return listEntrata;
	}
	public static List<Pagamento> selectMese(Integer mese){
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.fattura.data=:data",Pagamento.class);
		//	query.setParameter("data", value);
		List<Pagamento> risultato=query.getResultList();
		return risultato;
	}
	
	
	public static void aggiornaGiaPagato(Pagamento p,float valoreEntrata, EntityManager em) {
		
		
		
		em.getTransaction().begin();
		p.setGiaPagato(p.getGiaPagato()+valoreEntrata);
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
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}
	public static Fattura searchFattura(Integer idFattura) {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		Fattura f= em.find(Fattura.class, idFattura);
		
		return f;
	}
	

}

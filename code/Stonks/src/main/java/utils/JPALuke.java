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
import models.User;
import models.Utente;

public class JPALuke {

	// private List<Pagamento> listPagamento;

	public static List<Fattura> selectPagamenti(Integer idPersona, EntityManager em) {
		// EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Fattura> query = em
				.createQuery("SELECT f FROM Fattura f WHERE f.pagata = FALSE AND f.conto.utente=:id", Fattura.class)
				.setParameter("id", idPersona);
		// TypedQuery<Fattura> query = em.createQuery("SELECT f FROM Fattura f WHERE
		// f.pagata = FALSE", Fattura.class);

		// TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE
		// pa.pagato = FALSE and pa.fattura.conto.persona.id=:id and
		// pa.fattura.destinatario.id=:id",Pagamento.class);
		List<Fattura> listCompleta = query.getResultList();

		for (Fattura fattura : listCompleta) {
			System.out.println(fattura);
		}

		return listCompleta;
	}

	public static List<Fattura> selectParziale(Integer idPersona, boolean x, EntityManager em) {
		// EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Fattura> query = em.createQuery("SELECT f FROM Fattura f WHERE f.pagata = FALSE"
				+ " and f.conto.utente=:id and f.eUnaFatturaCliente=:si", Fattura.class);

		query.setParameter("id", idPersona);
		query.setParameter("si", x);
		List<Fattura> listEntrata = query.getResultList();
		// em.close();

		return listEntrata;
	}

	public static void aggiornaGiaPagato(Pagamento p, float valoreEntrata, EntityManager em) {
		// EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		p.setGiaPagato(p.getGiaPagato() + valoreEntrata);
		p.setDataPagamento(new Date());

		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}

	public static void setCompletato(Pagamento p, EntityManager em) {
		
	
		p.setGiaPagato(p.getFattura().getLordo());
		p.setPagato(true);
		p.setDataPagamento(new Date());
		p.getFattura().setPagata(true);

		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
	}

	public static void persistPagamento(Pagamento p) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();

	}

	public static Fattura searchFattura(Integer idFattura,EntityManager em) {
		//EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Fattura f = em.find(Fattura.class, idFattura);
		//em.close();
		return f;
	}

	public static Pagamento searchPagamento(Integer idFattura, EntityManager em) {
		Pagamento pagamento = null;
		// EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Pagamento> query = em.createQuery("SELECT pa FROM Pagamento pa WHERE pa.fattura.id = :idFattura",
				Pagamento.class);

		query.setParameter("idFattura", idFattura);
		try {
			pagamento = query.getSingleResult();
			// em.close();
		} catch (Exception e) {
			System.out.println("Pagamento non trovato");
		}

		return pagamento;

	}

	public static List<Pagamento> searchAllPagamenti(Integer idPersona, EntityManager em) {
		TypedQuery<Pagamento> query = em
				.createQuery("SELECT pa FROM Pagamento pa where pa.fattura.conto.utente=:idPersona", Pagamento.class);
		query.setParameter("idPersona", idPersona);
		List<Pagamento> listaPagamento = query.getResultList();
		return listaPagamento;
	}
	
	public static List<Pagamento> searchPagamentiConto(Integer idConto, EntityManager em) {
		TypedQuery<Pagamento> query = em
				.createQuery("SELECT pa FROM Pagamento pa where pa.fattura.conto.id=:idConto", Pagamento.class);
		query.setParameter("idConto", idConto);
		List<Pagamento> listaPagamento = query.getResultList();
		return listaPagamento;
	}
	
	public static Persona searchPersona(String partitaIVA) {
		Persona persona=null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p WHERE p.pIVA = :partitaIVA",
				Persona.class);
		query.setParameter("partitaIVA", partitaIVA);
		try {
		 persona=query.getSingleResult();
		}catch(Exception e) {
			
		}
		em.close();
		
		return persona;
	}
	public static Conto searchConto(Integer idConto) {
		Conto conto=null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Conto> query = em.createQuery("SELECT c FROM Conto c WHERE c.id = :idConto",
				Conto.class);
		query.setParameter("idConto", idConto);
		try {
		 conto=query.getSingleResult();
		}catch(Exception e) {
			
		}
		em.close();
		
		return conto;
		
		
	}
	
	public static boolean isNewUsername(String username) {
		User user=null;
		boolean isNew=true;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username",
				User.class);
		query.setParameter("username", username);
		try {
		 user=query.getSingleResult();
		}catch(Exception e) {
			
		}
		em.close();
		if(user!=null) {
			isNew=false;
		}else {
			isNew=true;
		}
		return isNew;
	}
	
}

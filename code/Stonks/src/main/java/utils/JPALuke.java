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

	// private List<Pagamento> listPagamento;

	public static List<Fattura> selectPagamenti(Persona p, EntityManager em) {
		// EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		 TypedQuery<Fattura> query=em.createQuery("SELECT f FROM Fattura f WHERE f.pagata = FALSE AND f.conto.utente=:id",Fattura.class);
		//TypedQuery<Fattura> query = em.createQuery("SELECT f FROM Fattura f WHERE f.pagata = FALSE", Fattura.class);

		// TypedQuery<Pagamento> query=em.createQuery("SELECT pa FROM Pagamento pa WHERE
		// pa.pagato = FALSE and pa.fattura.conto.persona.id=:id and
		// pa.fattura.destinatario.id=:id",Pagamento.class);
		query.setParameter("id", p.getId());
		List<Fattura> listCompleta = query.getResultList();

		for (Fattura fattura : listCompleta) {
			System.out.println(fattura);
		}

		return listCompleta;
	}

	public static List<Fattura> selectParziale(Persona p, boolean x, EntityManager em) {
		// EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		TypedQuery<Fattura> query = em.createQuery("SELECT f FROM Fattura f WHERE f.pagata = FALSE"
				+ " and f.conto.utente=:id and f.eUnaFatturaCliente=:si", Fattura.class);

		query.setParameter("id", p.getId());
		query.setParameter("si", x);
		List<Fattura> listEntrata = query.getResultList();
		// em.close();

		return listEntrata;
	}

	public static void aggiornaGiaPagato(Pagamento p, float valoreEntrata,EntityManager em) {
		//EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		p.setGiaPagato(p.getGiaPagato() + valoreEntrata);

		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}

	public static void setCompletato(Pagamento p,EntityManager em) {
		//EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Date dataPagamento = new Date();
		p.setGiaPagato(p.getFattura().getLordo());
		p.setPagato(true);
		p.setDataPagamento(dataPagamento);
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

	public static Fattura searchFattura(Integer idFattura) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Fattura f = em.find(Fattura.class, idFattura);
		em.close();
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

}

package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Admin;

import model.Articolo;
import model.Conto;
import model.Fattura;
import model.FornitoreCliente;
import model.MetodoDiPagamento;
import model.Pagamento;
import model.Persona;
import model.User;
import model.Utente;

public class ProvaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProvaFattura3");
		EntityManager em = emf.createEntityManager();
		
		Persona p =new Persona();
		p.setNome("pino");
		p.setCognome("totti");
		p.setIndirizzo("Via dalle palle");
		p.setMail("pino.mail");
		p.setpIVA("1234");
		p.setTelefono("0391111111");
		
		User us=new User();
		
		us.setNome("pino2");
		us.setCognome("totti2");
		us.setIndirizzo("Via dalle palle2");
		us.setMail("pino.mail2");
		us.setpIVA("12342");
		us.setTelefono("03911111112");
		us.setUsername("UserPippo2");
		us.setPassword("Pippo123");
		
		Admin a =new Admin();
		
		a.setNome("pino");
		a.setCognome("totti");
		a.setIndirizzo("Via dalle palle");
		a.setMail("pino.mail");
		a.setpIVA("1234");
		a.setTelefono("03911111113");
		a.setCodiceAdmin(123456);
		a.setUsername("AdminUser");
		a.setPassword("admin_pass");
		
		FornitoreCliente fc =new FornitoreCliente();
		
		fc.setNome("pinof");
		fc.setCognome("tottif");
		fc.setIndirizzo("Via dalle palle f");
		fc.setMail("pino.mailf");
		fc.setpIVA("1234f");
		fc.setTelefono("0391111111f");
		
		FornitoreCliente fc2=new FornitoreCliente();
		
		fc2.setNome("tipo2");
		fc2.setCognome("cognome2");
		fc2.setIndirizzo("via 2");
		fc2.setMail("mail2");
		fc2.setpIVA("fkck494");
		fc2.setTelefono("123");
		
		Utente ut = new Utente();
		
		ut.setNome("pino2u");
		ut.setCognome("totti2u");
		ut.setIndirizzo("Via dalle palle2u");
		ut.setMail("pino.mail2uu");
		ut.setpIVA("12342u");
		ut.setTelefono("039111111125u");
		ut.setUsername("UserPippo2u");
		ut.setPassword("Pippo123u");
		ut.setMetodoRegistrazione("metodo1");
		
		Conto c=new Conto();
		c.setSaldoDisponibile(103.5);
		c.setSaldoUtile(123.3);
		c.setPersona(ut);
		
		
		MetodoDiPagamento m =new MetodoDiPagamento();
		m.setNome("metodo1");
		m.setMetodo("m1");
		m.setDestinazione("destinazione1");
		c.setMetodoDiPagamento(m);
		
		Articolo ar=new Articolo();
		ar.setDescrizione("ciao");
		ar.setImportoParziale(34.6);
		ar.setIva(2);
		ar.setQuantita(7);
		
		Articolo ar2=new Articolo();
		
		ar2.setDescrizione("ciao2");
		ar2.setImportoParziale(77.6);
		ar2.setIva(6);
		ar2.setQuantita(5);
		
		Articolo ar3=new Articolo();
		
		ar3.setDescrizione("ciao3");
		ar3.setImportoParziale(88.6);
		ar3.setIva(8);
		ar3.setQuantita(4);
		
		List<Articolo> lista = new ArrayList<Articolo>();
		lista.add(ar);
		lista.add(ar2);
		lista.add(ar3);
		
		Fattura f=new Fattura();
		f.setConto(c);
		f.setDataFattura("12/12/3000");
		f.setDestinatario(fc);
		f.setMittente(fc2);
		f.setImporto(34.5);
		f.setIVA(2);
		f.setNota("nota1");
		f.setNumeroFattura("numero fattura");
		f.setSaldoDovuto(23);
		f.setScadenza(3);
		f.setArticolo(lista);
		
		Pagamento pag=new Pagamento();
		pag.setDataPagamento("23");
		pag.setFattura(f);
		pag.setMancante(33.6f);
		pag.setPagato(true);
		
		
		em.getTransaction().begin();
		em.persist(us);
		em.persist(p);
		em.persist(a);
		em.persist(ut);
		em.persist(fc);
		em.persist(fc2);
		em.persist(c);
		em.persist(m);
		em.persist(f);
		em.persist(pag);
		em.persist(ar);
		em.persist(ar2);
		em.persist(ar3);
		
		em.getTransaction().commit();

	}

}

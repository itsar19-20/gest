package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;

import models.Conto;
import models.Fattura;
import models.Pagamento;
import models.Persona;
import models.Utente;
import utils.DateUtil;
import utils.JPALuke;

public class Scadenziario {

	// private List<Pagamento> pagamentiDaConcludere;

	// private List<Pagamento> entrataDaConcludere;

	/// private List<Pagamento> uscitaDaConcludere;

	private int anticipoNotifica;

	public Scadenziario() {

	}

	public int getAnticipoNotifica() {
		return 5;
	}

	public void setAnticipoNotifica(int anticipoNotifica) {

	}

	public static List<Fattura> showFullScadenziario(Integer idPersona, EntityManager em) {

		List<Fattura> pagamentiDaConcludere = JPALuke.selectPagamenti(idPersona, em);
		for (Fattura f : pagamentiDaConcludere) {
			System.out.println("Fattura: " + f.getIdFattura());

		}
		return pagamentiDaConcludere;
	}

	public static List<Fattura> showEntrataDaConcludere(Integer idPersona, boolean bol, EntityManager em) {
		List<Fattura> entrataDaConcludere = JPALuke.selectParziale(idPersona, bol, em);
		for (Fattura f : entrataDaConcludere) {
			System.out.println("Id" + f.getIdFattura());
		}

		return entrataDaConcludere;
	}

	public static List<Fattura> showScadenziarioMese(Integer idPersona, Integer mese, EntityManager em) {
		// select tutti i pagamenti dell'utnte non ancora completati
		List<Fattura> scadenzaMese = JPALuke.selectPagamenti(idPersona, em);
		scadenzaMese = Scadenziario.searchByMese(mese, scadenzaMese);
		return scadenzaMese;
	}

	public static List<Fattura> showScadenziarioSettimana(Integer idPersona, Integer settimana, EntityManager em) {
		List<Fattura> scadenzaSettimana = JPALuke.selectPagamenti(idPersona, em);
		scadenzaSettimana = Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		return scadenzaSettimana;
	}

	public static List<Fattura> showMeseEntrata(Integer idPersona, Integer mese, boolean bol, EntityManager em) {
		List<Fattura> entrataDaConcludere = JPALuke.selectParziale(idPersona, bol, em);
		entrataDaConcludere = Scadenziario.searchByMese(mese, entrataDaConcludere);
		return entrataDaConcludere;

	}

	public static List<Fattura> showSettimanaEntrata(Integer idPersona, Integer settimana, boolean bol, EntityManager em) {
		List<Fattura> scadenzaSettimana = JPALuke.selectParziale(idPersona, bol, em);
		scadenzaSettimana = Scadenziario.searchBySettimana(settimana, scadenzaSettimana);
		return scadenzaSettimana;

	}

	private static List<Fattura> searchByMese(Integer mese, List<Fattura> scadenzaMese) {
		Date dataCorrente = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataCorrente);
		Calendar cal2=(Calendar)cal.clone();
		cal2.add(Calendar.MONTH,+mese);
		int annoRicerca=cal2.get(Calendar.YEAR);
		cal2.clear();
		List<Fattura> result = new ArrayList<>();
		// ricerca in base a N mesi successivi a quello corrente delle scadenze fatture
		for (Fattura f : scadenzaMese) {

			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			c.setTime(f.getData());

			Calendar d = (Calendar) cal.clone();
			c.add(Calendar.DAY_OF_YEAR, +f.getScadenza());
			int annoScadenza=c.get(Calendar.YEAR);
			if(annoScadenza==annoRicerca) {
				Scadenziario.calcolaMese(c, d, mese, f, result);
			}
		}
		return result;
	}
	private static List<Fattura> searchBySettimana(Integer settimana, List<Fattura> scadenzaSettimana) {
		Date dataCorrente = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataCorrente);
		Calendar cal2=(Calendar)cal.clone();
		cal2.add(Calendar.WEEK_OF_YEAR,+settimana);
		int annoRicerca=cal2.get(Calendar.YEAR);
		cal2.clear();
		List<Fattura> result = new ArrayList<>();
		for (Fattura f : scadenzaSettimana) {
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
			c.setTime(f.getData());
			Calendar d = (Calendar) cal.clone();
			c.add(Calendar.DAY_OF_YEAR, +f.getScadenza());
			int annoScadenza=c.get(Calendar.YEAR);
			if(annoScadenza==annoRicerca) {
				Scadenziario.calcolaSettimana(c, d, settimana, f, result);
			}
		}
		return result;

	}

	private static void calcolaMese(Calendar c, Calendar d, Integer mese, Fattura f, List<Fattura> result) {
		d.add(Calendar.MONTH, +mese);

		if (c.get(Calendar.MONTH) == d.get(Calendar.MONTH)) {
			result.add(f);
		}
	}

	private static void calcolaSettimana(Calendar c, Calendar d, Integer settimana, Fattura f, List<Fattura> result) {
		d.add(Calendar.WEEK_OF_YEAR, +settimana);
		if (c.get(Calendar.WEEK_OF_YEAR) == d.get(Calendar.WEEK_OF_YEAR)) {
			result.add(f);

		}
	}

	private static List<Fattura> searchForNotifica(List<Fattura> pagamentiDaConcludere, Integer anticipoNotifica) {

		Date dataOggi = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.setTime(dataOggi);
		List<Fattura> listaFatture = new ArrayList<>();
		Calendar cal2=(Calendar)cal.clone();
		cal2.add(Calendar.DAY_OF_YEAR, + anticipoNotifica);
		int annoRicerca=cal2.get(Calendar.YEAR);
		cal2.clear();
		
		for (Fattura f : pagamentiDaConcludere) {
			Calendar calFattura = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);

			calFattura.setTime(f.getData());
			Calendar calCorrente = (Calendar) cal.clone();
			//
			// l'anticipoNotifica � un valore memorizzat-+o in localStorage con chiave
			// "anticipo"
			calCorrente.add(Calendar.DAY_OF_YEAR, +anticipoNotifica);
			calFattura.add(Calendar.DAY_OF_YEAR, +f.getScadenza());
			int annoScadenza=calFattura.get(Calendar.YEAR);
			
			if(annoScadenza!=annoRicerca) {
				continue;
			}
			
			if (calCorrente.getTime().after(calFattura.getTime()) && cal.getTime().before(calFattura.getTime())) {
				System.out.println("La fattura di id: " + f.getIdFattura() + " sta per scadere");
				listaFatture.add(f);
			}
		}
		return listaFatture;
	}

	public static List<Fattura> checkNotifica(Integer idPersona, Integer anticipoNotifica, EntityManager em) {
		// System.out.println("Entro dove non chiede boolean");
		List<Fattura> pagamentiDaConcludere = JPALuke.selectPagamenti(idPersona, em);
		return Scadenziario.searchForNotifica(pagamentiDaConcludere, anticipoNotifica);

	}

	public static List<Fattura> checkNotifica(Integer idPersona, Integer anticipoNotifica, boolean entrataUscita,
			EntityManager em) {
		// System.out.println("Entro dove chiede boolean");
		List<Fattura> listaPagamento = JPALuke.selectParziale(idPersona, entrataUscita, em);
		return Scadenziario.searchForNotifica(listaPagamento, anticipoNotifica);
	}
}

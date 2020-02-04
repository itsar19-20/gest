package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import sun.font.MFontConfiguration;
import utils.JPAUtil;

/**
 * Servlet implementation class FatturaCreaController
 */
@WebServlet("/fattura/salva")
public class FatturaSalvaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FatturaSalvaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	data
		String dataString = request.getParameter("data");
		Date data = null;
		try {
			data = new SimpleDateFormat("yyyy-mm-dd").parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("errore nella conversione della data -> non è stata inserita correttamente");
		}
		
		//	scadenza
		String scadenzaString = request.getParameter("scadenza");
		Integer scadenza = Integer.parseInt(scadenzaString);
		
		//	pagata
		boolean pagata = false;
		if (scadenza == 0) pagata = true;
		
		//	cliente o fornitore
		String tipoFatturaString = request.getParameter("tipoFattura");
		boolean eUnaFatturaCliente;
		if(tipoFatturaString == "cliente") eUnaFatturaCliente = true;
		else eUnaFatturaCliente = false;
		
		//	persona
		String personaString = request.getParameter("persona");
		Integer personaInteger = 3;	//	id provvisrio
		Persona persona = MenagementFattura.getPersona(personaInteger);
		
		//	conto
		String contoString = request.getParameter("conto");
		Integer contoInteger = Integer.parseInt(contoString);
		Conto conto = MenagementFattura.getConto(contoInteger);

		String nota = request.getParameter("note");
		
		
		//	articoli
		String descrizione = request.getParameter("descrizione");
		String quantitaString = request.getParameter("quantita");
			Integer quantita = Integer.parseInt(quantitaString);
		String prezzoString = request.getParameter("prezzo");
			Integer prezzo = Integer.parseInt(prezzoString);
		
		if (data == null || scadenza == null || persona == null || conto == null) {
			doGet(request, response);
			response.sendError(400, "Inserire tutti i campi !!");
			response.getWriter().append("alert('Inserire tutti i campi !!')");
			return;
		}
		
		//	creo l'oggetto fattura
		Fattura f = new Fattura(data, scadenza, eUnaFatturaCliente, persona, nota, pagata, conto);
		// lo salvo nel database
		MenagementFattura.create(f);
		ObjectMapper om = new ObjectMapper();
		out("");
		// cerco la fattura per id nel database e la stampo in stringa json
		System.out.println(om.writeValueAsString(MenagementFattura.readById(f.getId())));
		//	MenagementFattura.delate(f);
		
		//	test conversione tipi
		String g = "\n", s = " -> ", t = "    ", 
				bl = g+"--------------------------------------------------"+g;
		System.out.print(
				bl+
				"tipo fattura"+s+tipoFatturaString+s+eUnaFatturaCliente+g+
				"conto"+s+contoString+s+contoInteger+s+conto+g+
				"persona"+s+personaString+s+personaInteger+s+persona+g+
				"data"+s+dataString+s+data+g+
				"scadenza"+s+scadenzaString+s+scadenza+g+
				"pagata"+s+pagata+g+
				"nota"+s+nota+g+
				g+
				"articoli:"+g
		);
		
		
		//	dichiaro una stringa contenente una lista oggetti articolo in json
		String articoliJsonString = request.getParameter("articoli");
		out(articoliJsonString);
		out(om.writeValueAsString(articoliJsonString));
		
		//	cerco di convertire la stringa json in degli oggetti veri e propri
		/*
		List<Articolo> articoli = om.readValue(articoliJsonString, new TypeReference<List<Articolo>>() {});
		out("creata la lista");
		for (Articolo a : articoli) {
			//	colleggo il singolo oggetto alla rispettiva fattura
			a.setFattura(f); out("set fattura");
			//	salvo l'oggetto nel database
			MenagementFattura.create(a); out("persist nel db");
			//	stampo a console l'articolo in formato json
			System.out.println(om.writeValueAsString(a));
		}
		*/
		
		
		/*
		String[] articoli = request.getParameterValues("articoliDiProva");
		for (String art : articoli) {
			System.out.println(art);
			/*
			a.setFattura(f);
			MenagementFattura.create(a);
			System.out.println("l'articolo "+a+" è stato aggiunto al database");
			System.out.println(
					t+a.getDescrizione()+g+
					t+a.getQuantita()+g+
					t+a.getImportoParziale()+
					bl
			);
			
		}
		*/
		//	for each elemento of article class
		//	Articolo a = new Articolo(descrizione, quantita, prezzo, f);
		
		
	}
	
	public void out(String s) {
		System.out.println(s);
	}

}

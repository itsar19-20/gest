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

import business.MenagementFattura;
import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;

/**
 * Servlet implementation class FatturaCreaController
 */
@WebServlet("/fattura/crea")
public class FatturaCreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FatturaCreaController() {
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
		
		/*
		 * per gli articoli:
		 * 1. assegnare ad ogni articolo la stessa classe
		 * 2. con js contare quanti elementi appartengono a quella classe
		 * 3. passare il valore al controller
		 * 
		 * sistemare il meccanismo per assegnare id sequenziali agli articoli
		 * 
		 * oppure usare un ciclo for each nel controller
		 * 
		 * ci devo pensare ...
		 * 
		 */
		
		//	articoli
		String descrizione = request.getParameter("descrizione");
		String quantitaString = request.getParameter("quantita");
			Integer quantita = Integer.parseInt(quantitaString);
		String prezzoString = request.getParameter("prezzo");
			Integer prezzo = Integer.parseInt(prezzoString);
		
		List<Articolo> articoli;
		
		if (data == null || scadenza == null || persona == null || conto == null) {
			doGet(request, response);
			response.sendError(400, "Inserire tutti i campi !!");
			response.getWriter().append("alert('Inserire tutti i campi !!')");
			return;
		}
		
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
				"articoli:"+g+
				g+
				t+"descrizione"+s+descrizione+g+
				t+"quantita"+s+quantitaString+s+quantita+g+
				t+"prezzo"+s+prezzoString+s+prezzo+
				bl
		);
		
		Fattura f = new Fattura(data, scadenza, eUnaFatturaCliente, persona, nota, pagata, conto);
		System.out.println(g+f.toString()+g);
		MenagementFattura.crate(f);
		System.out.println("la fattura è stata aggiunta al database");
		
		// for each elemento of article class
		Articolo a = new Articolo(descrizione, quantita, prezzo, f);
		MenagementFattura.add(a);
		System.out.println("l'articolo "+a+" è stato aggiunto al database");
		
	}

}

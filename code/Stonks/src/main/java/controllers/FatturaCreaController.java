package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Fattura;
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
		
		//	cliente o fornitore
		String tipoFatturaString = request.getParameter("tipoFattura");
		boolean tipoFattura;
		if(tipoFatturaString == "cliente") tipoFattura = true;
		else tipoFattura = false;
		
		//	conto
		String contoString = request.getParameter("conto");
		Integer conto = Integer.parseInt(contoString);
		
		//	persona
		String personaString = request.getParameter("persona");
		//	Integer persona = Integer.parseInt(personaString);
		
		//	data
		String dataString = request.getParameter("data");
		Date data = null;
		try {
			data = new SimpleDateFormat("yyyy-mm-dd").parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("errore nella conversione della data");
		}
		
		//	scadenza
		String scadenzaString = request.getParameter("scadenza");
		Integer scadenza = Integer.parseInt(scadenzaString);
		
		String note = request.getParameter("note");
		
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

		//	test conversione tipi
		String a = "\n", s = " -> ";
		System.out.print(
				"tipo fattura"+s+tipoFatturaString+s+tipoFattura+a+
				"conto"+s+contoString+s+conto+a+
				"persona"+s+personaString+a+
				"data"+s+dataString+s+data+a+
				"scadenza"+s+scadenzaString+s+scadenza+a+
				"note"+s+note);
		
		//Fattura fattura = new 

		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}

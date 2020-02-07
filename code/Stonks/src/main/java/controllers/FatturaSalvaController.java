package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import utils.JsonUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * // data String dataString = request.getParameter("data"); Date data = null;
		 * try { data = new SimpleDateFormat("yyyy-mm-dd").parse(dataString); } catch
		 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * System.out.
		 * println("errore nella conversione della data -> non è stata inserita correttamente"
		 * ); }
		 * 
		 * // scadenza String scadenzaString = request.getParameter("scadenza"); Integer
		 * scadenza = Integer.parseInt(scadenzaString);
		 * 
		 * // pagata boolean pagata = false; if (scadenza == 0) pagata = true;
		 * 
		 * // cliente o fornitore String tipoFatturaString =
		 * request.getParameter("tipoFattura"); boolean eUnaFatturaCliente; if
		 * (tipoFatturaString.contentEquals("cliente")) eUnaFatturaCliente = true; else
		 * eUnaFatturaCliente = false;
		 * 
		 * // persona String personaString = request.getParameter("persona"); Integer
		 * personaInteger = Integer.parseInt(personaString); Persona persona =
		 * MenagementFattura.getPersona(personaInteger);
		 * 
		 * // conto String contoString = request.getParameter("conto"); Integer
		 * contoInteger = Integer.parseInt(contoString); Conto conto =
		 * MenagementFattura.getConto(contoInteger);
		 * 
		 * // nota String nota = request.getParameter("note");
		 * 
		 * if (data == null || scadenza == null || persona == null || conto == null) {
		 * doGet(request, response); response.sendError(400,
		 * "Inserire tutti i campi !!");
		 * response.getWriter().append("alert('Inserire tutti i campi !!')"); return; }
		 * 
		 * // creo l'oggetto fattura Fattura f = new Fattura(data, scadenza,
		 * eUnaFatturaCliente, persona, nota, pagata, conto); // lo salvo nel database
		 * MenagementFattura.create(f); // MenagementFattura.delate(f); ObjectMapper om
		 * = new ObjectMapper();
		 * 
		 * // cerco la fattura per id nel database e la stampo in stringa json
		 * System.out.println(om.writeValueAsString(MenagementFattura.readById(f.getId()
		 * )));
		 */

		ObjectMapper om = new ObjectMapper();

		// intercetto la stringa json con dell'oggeto fattura
		String fatturaJsonString = request.getParameter("fattura");
		// creo l'oggetto fattura
		Fattura f = om.readValue(JsonUtil.getJsonFromAnObject(fatturaJsonString), Fattura.class);
		// la fattura è gia stata pagata?
		if (f.getScadenza() == 0)
			f.setPagata(true);
		else
			f.setPagata(false);

		// lo salvo nel database
		MenagementFattura.create(f);

		// dichiaro una stringa contenente una lista oggetti di tipo articolo in json
		String articoliJsonString = request.getParameter("articoli");
		// la converto in una reale stringa json rappresentante una collezione di oggetti

		// converto la stringa json in degli oggetti veri e propri
		TypeReference<List<Articolo>> listType = new TypeReference<List<Articolo>>() {
		};
		List<Articolo> articoli = om.readValue(JsonUtil.getJsonFromAnObjectOfObjects(articoliJsonString), listType);
		for (Articolo a : articoli) {
			// colleggo il singolo articolo alla rispettiva fattura
			a.setFattura(f);
			// salvo l'articolo nel database
			MenagementFattura.create(a);
		}

	}

	public void out(String s) {
		System.out.println(s);
	}

}

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
import utils.DataBase;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		DataBase.create(f);

		// dichiaro una stringa contenente una lista oggetti di tipo articolo in json
		String articoliJsonString = request.getParameter("articoli");
		// la converto in una reale stringa json rappresentante una collezione di
		// oggetti

		// converto la stringa json in degli oggetti veri e propri
		TypeReference<List<Articolo>> listType = new TypeReference<List<Articolo>>() {
		};
		List<Articolo> articoli = om.readValue(JsonUtil.getJsonFromAnObjectOfObjects(articoliJsonString), listType);
		for (Articolo a : articoli) {
			// colleggo il singolo articolo alla rispettiva fattura
			a.setFattura(f);
			// salvo l'articolo nel database
			DataBase.create(a);
		}

	}

	public void out(String s) {
		System.out.println(s);
	}

}

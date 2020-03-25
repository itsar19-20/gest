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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.FatturaManager;
import business.GestisciPagamento;
import models.Articolo;
import models.Conto;
import models.Fattura;
import models.Persona;
import models.User;
import utils.DataBase;
import utils.God;
import utils.JPAUtil;
import utils.JsonUtil;

@WebServlet("/fattura/salva")
public class FatturaSalvaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FatturaSalvaController() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		Fattura f = om.readValue(JsonUtil.getJsonFromAnObject(request.getParameter("fattura")), Fattura.class);

		FatturaManager.add(f);
		
		if (f.getScadenza() == 0) {
			try {
				EntityManager em_1 = JPAUtil.getInstance().getEmf().createEntityManager(), 
						em_2 = JPAUtil.getInstance().getEmf().createEntityManager();
				GestisciPagamento.addNewPagamento(FatturaManager.getLastByUserId(f.getConto().getUtente()).getId(), f.getLordo(), em_1, em_2);
				em_1.close();
				em_2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// dichiaro una stringa contenente una lista oggetti di tipo articolo in json
		String articoliJsonString = request.getParameter("articoli");
		// la converto in una reale stringa json rappresentante una collezione di
		// oggetti

		// converto la stringa json in degli oggetti veri e propri
		TypeReference<List<Articolo>> listType = new TypeReference<List<Articolo>>() {};
		List<Articolo> articoli = om.readValue(JsonUtil.getJsonFromAnObjectOfObjects(articoliJsonString), listType);
		for (Articolo a : articoli) {
			// colleggo il singolo articolo alla rispettiva fattura
			a.setFattura(f);
			// salvo l'articolo nel database
			DataBase.create(a);
		}
		God.seesEverythings(request, response, null);

	}

	public void out(String s) {
		System.out.println(s);
	}

}

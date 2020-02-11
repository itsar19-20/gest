package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Persona;
import utils.DataBase;
import utils.JsonUtil;

/**
 * Servlet implementation class AggiungiPersona
 */
@WebServlet("/fattura/aggiungi-persona")
public class AggiungiPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiPersona() {
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

		// ricevo la stringa json rapppresentante l'oggetto persona
		String input = request.getParameter("persona");
		// creo l'oggetto persona
		Persona p = om.readValue(JsonUtil.getJsonFromAnObject(input), Persona.class);
		// ricevo le istruzioni su cosa fare
		String istruzioni = request.getParameter("istruzioniPerIlController");
		// le leggo per capire cosa fare
		if (istruzioni.contentEquals("crea")) {
			// lo salvo nel database
			DataBase.create(p);
		} else if (istruzioni.contentEquals("modifica")) {
			// aggiorno il database
			DataBase.update(p);
		} else {
			System.out.println("[controllers.AggiungiPersona.doPost] istruzioni errate");
		}
		// invio la persona appena aggiunta al browser
		response.getWriter().append(om.writeValueAsString(DataBase.getObjectById("p", p.getId())));

	}

}

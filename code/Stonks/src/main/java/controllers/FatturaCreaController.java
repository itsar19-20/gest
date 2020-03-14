package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import models.Conto;
import models.Persona;

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
		ObjectMapper om = new ObjectMapper();
		// ajax passa l'id dell'utente loggato come stringa
		String userIdString = request.getParameter("user");
		// la stringa diventa un numero
		Integer id = Integer.parseInt(userIdString);
		// capisco cosa mi sta richiedendo il client
		String whatIWant = request.getParameter("whatIWant");
		response.setContentType("application/json");
		if (whatIWant.contentEquals("conti")) {
			// richiedo la lista dei conti collegati all'utente loggato
			List<Conto> lc = MenagementFattura.listaConti(id);
			// passo queste informazioni al client
			response.getWriter().append(om.writeValueAsString(lc));
		} else if (whatIWant.contentEquals("persone")) {
			// richiedo la lista delle persone create da questo utente
			List<Persona> lp = MenagementFattura.listaPersone(id);
			// passo queste informazioni al client
			response.getWriter().append(om.writeValueAsString(lp));
		} else if (whatIWant.contentEquals("minMax")) {
			int min = MenagementFattura.getMinIdOfContiAndFatture(id),
					max = MenagementFattura.getMaxIdOfContiAndFatture(id);
			String output = "{\"min\":" + min + ",\"max\":" + max + "}";
			response.getWriter().append(output);
		}
	}

}

package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.FatturaManager;
import business.PersonaManager;
import models.Conto;
import models.Persona;
import utils.God;

@WebServlet("/fattura/crea")
public class FatturaCreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FatturaCreaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Integer id = Integer.parseInt(request.getParameter("user"));
		// capisco cosa mi sta richiedendo il client
		String whatIWant = request.getParameter("whatIWant");
		response.setContentType("application/json");
		if (whatIWant.contentEquals("conti")) {
			// richiedo la lista dei conti collegati all'utente loggato
			List<Conto> lc = FatturaManager.listaConti(id);
			// passo queste informazioni al client
			response.getWriter().append(om.writeValueAsString(lc));
		} else if (whatIWant.contentEquals("persone")) {
			// richiedo la lista delle persone create da questo utente
			List<Persona> lp = PersonaManager.getListByAuthorId(id);
			// passo queste informazioni al client
			response.getWriter().append(om.writeValueAsString(lp));
		} else if (whatIWant.contentEquals("minMax")) {
			int min = FatturaManager.getMinIdOfContiAndPersone(id),
					max = FatturaManager.getMaxIdOfContiAndPersone(id);
			String output = "{\"min\":" + min + ",\"max\":" + max + "}";
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().append(output);
		}
		God.seesEverythings(request, response, null);
	}

}

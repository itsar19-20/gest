package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import models.Fattura;
import utils.JPAUtil;
import utils.SizeOf;

/**
 * Servlet implementation class FatturaController
 */
@WebServlet("/archivio/getAllMineInvoices")
public class FatturaArchivioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FatturaArchivioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ajax passa l'id dell'utente loggato come stringa
		String userIdString = request.getParameter("user");
		// la stringa diventa un numero
		Integer id = Integer.parseInt(userIdString);
		List<Fattura> fatture = MenagementFattura.listaFatture(id);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(fatture));
		System.out.println(SizeOf.getSizeInByte(om.writeValueAsString(fatture)) + " byte");
		System.out.println(SizeOf.getSizeInMegabyte(om.writeValueAsString(fatture)) + " megabyte");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

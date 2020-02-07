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

import models.Fattura;
import utils.JPAUtil;

/**
 * Servlet implementation class FatturaController
 */
@WebServlet("/fattura/guarda")
public class FatturaGuardaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FatturaGuardaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		// em.createQuery utilizza il linguaggio JPQL
		List<Fattura> fatture = em.createQuery("select f from Fattura f", Fattura.class).getResultList();
		ObjectMapper om = new ObjectMapper()/* .setSerializationInclusion(Include.NON_NULL) */;

		for (Fattura fattura : fatture) {
			System.out.println(om.writeValueAsString(fattura));
		}

		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(fatture));

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

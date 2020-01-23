package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Fattura;
import utils.JPAUtil;

/**
 * Servlet implementation class FatturaController
 */
@WebServlet("/fattura/guarda")
public class FatturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FatturaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Fattura> fatture = em.createQuery("select c from fattura c", Fattura.class).getResultList();
		ObjectMapper om = new ObjectMapper();
		response.getWriter().append(om.writeValueAsString(fatture));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

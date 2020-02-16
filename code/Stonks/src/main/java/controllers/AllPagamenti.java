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

import business.Scadenziario;
import models.Fattura;
import models.Pagamento;
import models.Persona;
import utils.DataBase;
import utils.JPALuke;
import utils.JPAUtil;

@WebServlet("/allPagamenti")
public class AllPagamenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllPagamenti() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String idUserString = request.getParameter("userID");
		System.out.println(idUserString);
		Integer idUser = Integer.parseInt(idUserString);
		
		EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		List<Pagamento> listaPagamenti=JPALuke.searchAllPagamenti(idUser, emTemp);
		
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaPagamenti));

		emTemp.close();

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

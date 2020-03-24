package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.ContoManager;
import models.Conto;
import utils.God;

@WebServlet("/profilo/conti")
public class ProfiloConti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfiloConti() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idContoString = request.getParameter("idConto");
		Integer idConto = null;
		if (idContoString != null) idConto = Integer.valueOf(idContoString);
		String output;
		if (idConto == null) {			
			List<Conto> list = 
					ContoManager.listaEliminabiliENon(
							ContoManager.getListByUtenteId(
									Integer.valueOf(request.getParameter("user"))));
			output = new ObjectMapper().writeValueAsString(list);
		} else {
			output = new ObjectMapper().writeValueAsString(ContoManager.getById(idConto));
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = null;
		Conto c = new ObjectMapper().readValue(request.getParameter("conto"), Conto.class);
		if (ContoManager.add(c));
			output = "ok";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = null;
		Conto c = ContoManager.getById(Integer.valueOf(request.getParameter("idConto")));
		c.setNome(request.getParameter("nome"));
		if (ContoManager.update(c)) output = "ok";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("idConto"));
		Conto c = ContoManager.getById(id);
		String output = null;
		if (ContoManager.delate(c)) output = "ok";
		else output = "Il conto non è stato eliminato";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

}
	
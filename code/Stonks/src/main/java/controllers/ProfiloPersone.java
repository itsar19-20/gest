package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import utils.DataBase;
import utils.God;

@WebServlet("/profilo/persone")
public class ProfiloPersone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfiloPersone() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPersonaString = request.getParameter("idPersona");
		Integer idPersona = null;
		if (idPersonaString != null) idPersona = Integer.valueOf(idPersonaString);
		String output;
		if (idPersona == null) {
			output = new ObjectMapper().writeValueAsString(MenagementFattura.listaPersone(Integer.valueOf(request.getParameter("user"))));
		} else {
			output = new ObjectMapper().writeValueAsString(DataBase.getPersonaById(idPersona));
		}
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		God.seesEverythings(request, response, null);
	}

}
	
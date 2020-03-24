package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.MenagementFattura;
import business.PersonaMenager;
import models.Persona;
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
		// response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = null;
		if (PersonaMenager.add(new ObjectMapper().readValue(request.getParameter("persona"), Persona.class)))
			output = "ok";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("idPersona"));
		Persona p = (Persona) DataBase.getObjectById("p", id);
		//DataBase.delatePersona(id);
		//DataBase.trans("delete", p);
		String output = null;
		if (DataBase.getObjectById("p", id) == null) output = "ok";
		else output = "La persona non è stata eliminata";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

}
	
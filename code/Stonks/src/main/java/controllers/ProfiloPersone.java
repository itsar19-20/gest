package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.PersonaManager;
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
			
			List<Persona> list = 
					PersonaManager.listaEliminabiliENon(
							PersonaManager.getListByAuthorId(
									Integer.valueOf(request.getParameter("user"))));
			output = new ObjectMapper().writeValueAsString(list);
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
		ObjectMapper om = new ObjectMapper();
		Persona p = om.readValue(request.getParameter("persona"), Persona.class);
		if (PersonaManager.add(p));
			output = new ObjectMapper().writeValueAsString(PersonaManager.getByIdI(PersonaManager.getMaxId(p.getAutore())));
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = null;
		if (PersonaManager.update(new ObjectMapper().readValue(request.getParameter("persona"), Persona.class)))
			output = "ok";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("idPersona"));
		Persona p = PersonaManager.getByIdI(id);
		String output = null;
		if (PersonaManager.delate(p)) output = "ok";
		else output = "La persona non è stata eliminata";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

}
	
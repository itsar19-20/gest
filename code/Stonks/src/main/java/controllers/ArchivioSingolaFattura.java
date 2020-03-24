package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.FatturaManager;
import models.Fattura;
import utils.DataBase;
import utils.God;

@WebServlet("/archivio/SingleInvoice")
public class ArchivioSingolaFattura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArchivioSingolaFattura() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		ObjectMapper om = new ObjectMapper();
		String output = om.writeValueAsString(FatturaManager.getFatturaById(id));
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doPut(req, resp);
		Integer id = Integer.valueOf(req.getParameter("id"));
		Fattura f = FatturaManager.getFatturaById(id);
		f.setNotaDiCredito(true);
		DataBase.update(f);
		God.seesEverythings(req, resp, null);
	}

}

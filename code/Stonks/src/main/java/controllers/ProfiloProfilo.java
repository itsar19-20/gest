package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import utils.DataBase;
import utils.God;

@WebServlet("/profilo/profilo")
public class ProfiloProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfiloProfilo() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = new ObjectMapper().writeValueAsString(DataBase.getUserById(Integer.valueOf(request.getParameter("user"))));
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, null);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("user"));
		String put = request.getParameter("put");
		String output = null;
		switch (put) {
		case "username":
			String nuovoNomeUtente = request.getParameter("nuovoNomeUtente");
			if (DataBase.getUserByUsername(nuovoNomeUtente) == null) {
				User u = DataBase.getUserById(id);
				u.setUsername(nuovoNomeUtente);
				DataBase.update(u);
				output = "ok";
			} else output = "no";
			break;
		case "password":
			String vecchiaPassword = request.getParameter("vecchiaPassword");
			String nuovaPassword = request.getParameter("nuovaPassword");
			if (vecchiaPassword.contentEquals(DataBase.getPasswordByUserId(id))) {
				User u = DataBase.getUserById(id);
				u.setPassword(nuovaPassword);
				DataBase.update(u);
				output = "ok";
			} else output = "no";
			break;
		case "anagrafe":
			User updatedUser = new ObjectMapper().readValue(request.getParameter("updatedUser"), User.class);
			User oldUser = DataBase.getUserById(id);
			updatedUser.setPassword(oldUser.getPassword());
			updatedUser.setDataOraUltimoLogin(oldUser.getDataOraUltimoLogin());
			DataBase.update(updatedUser);
			// User actualUser = DataBase.getUserById(id);
			// if (updatedUser.equals(actualUser)) output = "ok";
			// else output = "no";
			output = "ok";
			break;
		default:
			break;
		}
		response.setContentType("charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);		
	}

}

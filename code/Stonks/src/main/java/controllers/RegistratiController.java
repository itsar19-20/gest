package controllers;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import business.Registrati;
import business.Saldo;
import models.User;
import utils.God;
import utils.JPALuke;
import utils.JPAUtil;

@WebServlet("/registrati")
public class RegistratiController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public RegistratiController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String pIVA = request.getParameter("pIVA");
		String mail = request.getParameter("mail");
		String indirizzo = request.getParameter("indirizzo");
		String telefono = request.getParameter("telefono");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String metodoDiRegistrazione = request.getParameter("metodoDiRegistrazione");
		
		
		if(JPALuke.isNewUsername(username)) {
			EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
			Registrati r = new Registrati(nome, cognome, pIVA, mail, indirizzo, telefono, username, password, metodoDiRegistrazione,em);
			
			User user=r.getUtente();
			
			
			
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/json");
			response.getWriter().append(om.writeValueAsString(user));
			God.seesEverythings(request, response, om.writeValueAsString(user));
			em.close();
			
			
		}else {
			
			response.getWriter().append("giaUsato");
			God.seesEverythings(request, response,"giaUsato");
		}
		
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
}
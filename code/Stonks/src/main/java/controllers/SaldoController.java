package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import business.Login;
import business.Saldo;
import models.Utente;
import business.Saldo;
import models.Conto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/saldo")
public class SaldoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaldoController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Conto c = new Conto();
    	request.getSession().getAttribute("conto");
		Saldo s = new Saldo();
		request.getSession().setAttribute("conto", c);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(c));
	}

}
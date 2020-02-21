package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import business.Login;
import business.Saldo;
import business.TipoSaldo;
import models.Utente;
import utils.DataBase;
import business.Saldo;
import models.Conto;
import models.Fattura;
import models.Pagamento;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/conto")
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String contoStr = request.getParameter("conto");
    	TipoSaldo tS;
		Conto c = (Conto) DataBase.getObjectById("c", Integer.parseInt(contoStr));
    	Date date = new Date();
    	response = (HttpServletResponse) Saldo.saldo(c, date, tS);
	}

}
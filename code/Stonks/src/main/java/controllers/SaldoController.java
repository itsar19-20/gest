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
    	String scelta = request.getParameter("scelta");
    	String contoStr = request.getParameter("conto");
    	TipoSaldo tS;
    	if(scelta == "disponibile") {
    		tS = TipoSaldo.disponibile;
    	}else {
    		tS = TipoSaldo.utile;
    	}
    	Integer contoInt = Integer.parseInt(contoStr);
    	List<Fattura> fatture = null;
		Integer idFatture = null;
		List<Pagamento> pagamenti = null;
		Integer idPagamenti = null;
		float saldoDisponibile = 0;
		float saldoUtile = 0;
		Utente utente = null;
		Conto c = new Conto(contoInt, "polenta", fatture, idFatture, pagamenti, idPagamenti, saldoDisponibile, saldoUtile, utente);
    	Date date = new Date();
    	response = (HttpServletResponse) Saldo.saldo(c, date, tS);
	}

}
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.GestisciPagamento;
import models.Pagamento;

@WebServlet("/paga")
public class EseguiPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EseguiPagamento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Float newImporto=Float.parseFloat(request.getParameter("nuovoImporto")) ;
		
		Pagamento pagamento=(Pagamento) request.getSession().getAttribute("pagamento");
		
		if(pagamento==null) {
			
			Integer idFattura=(Integer) request.getSession().getAttribute("fatturaId");
			GestisciPagamento.addNewPagamento(idFattura,newImporto);
		}else {
			GestisciPagamento.segnalaPagamento(pagamento,newImporto);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.GestisciPagamento;
import models.Pagamento;
import utils.JPAUtil;

@WebServlet("/paga")
public class EseguiPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EseguiPagamento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Float newImporto=Float.parseFloat(request.getParameter("nuovoImporto")) ;
		
		Pagamento pagamento=(Pagamento) request.getSession().getAttribute("pagamento");
		EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		
		if(pagamento==null) {
			
			Integer idFattura=(Integer) request.getSession().getAttribute("fatturaId");
			pagamento=GestisciPagamento.addNewPagamento(idFattura,newImporto,emTemp);
		}else {
			pagamento=GestisciPagamento.segnalaPagamento(pagamento,newImporto,emTemp);
		}
		
		System.out.println("id: "+pagamento.getIdPagamento()+"\ngia pagato:  "+pagamento.getGiaPagato());
		
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(pagamento));

		emTemp.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

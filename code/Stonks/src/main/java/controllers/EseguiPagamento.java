package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.IdUniquenessPolicyValue;

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
		
		System.out.println("[pagamento 1]   "+pagamento);
		
		EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		EntityManager emFattura=null;
		
		if(pagamento==null) {
			emFattura=JPAUtil.getInstance().getEmf().createEntityManager();
			Integer idFattura=(Integer) request.getSession().getAttribute("fatturaId");
			System.out.println("[idFattura]   "+idFattura);
			pagamento=GestisciPagamento.addNewPagamento(idFattura,newImporto,emTemp,emFattura);
			System.out.println("[pagamento 2]   "+pagamento);
		}else {
			pagamento=GestisciPagamento.segnalaPagamento(pagamento,newImporto,emTemp);
			System.out.println("[pagamento 3]   "+pagamento);
		}
		
		System.out.println("id: "+pagamento.getIdPagamento()+"\ngia pagato:  "+pagamento.getGiaPagato());
		
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		System.out.println("[pre write]");
		System.out.println(pagamento);
		System.out.println(om.writeValueAsString(pagamento));
		response.getWriter().append(om.writeValueAsString(pagamento));
		System.out.println("[post write]");

		emTemp.close();
		System.out.println("[emTemp.close()]");
		if(emFattura!=null) {
			emFattura.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

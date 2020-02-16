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
import utils.JPALuke;
import utils.JPAUtil;

@WebServlet("/cancellaPagamento")
public class CancellaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CancellaPagamento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("sono in cancella pagamento");
		 Integer idFattura=Integer.parseInt(request.getParameter("pagamento"));
		 EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		 Pagamento pagamento= JPALuke.searchPagamento(idFattura, emTemp);
		 GestisciPagamento.cancellaPagamento(pagamento, emTemp);
		 
		 emTemp.close();
		 
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

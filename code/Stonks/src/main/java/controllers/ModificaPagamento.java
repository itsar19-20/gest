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
import models.Persona;
import utils.DataBase;
import utils.JPALuke;
import utils.JPAUtil;

@WebServlet("/modificaPagamento")
public class ModificaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ModificaPagamento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ho raggiunto la servlet modifica pagamento");
		
		Float importo =Float.parseFloat(request.getParameter("importoModifica"));
		Integer  fatturaId=Integer.parseInt(request.getParameter("pagamento"));
		EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		Pagamento pagamento=JPALuke.searchPagamento(fatturaId, emTemp);
		System.out.println("Pagamento: "+pagamento.getIdPagamento()+" "+pagamento.getGiaPagato());
		GestisciPagamento.modificaPagamento(pagamento, importo, emTemp);
		
		
		
		
		
		//System.out.println("Il pagamento preso id: "+pagamento.getIdPagamento()+" "+pagamento.getGiaPagato());
		emTemp.close();
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Pagamento;
import utils.God;
import utils.JPALuke;
import utils.JPAUtil;

@WebServlet("/cercaPagamento")
public class CercaPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CercaPagamento() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ho raggiunto la servlet cercaPagamento");
		Integer idFattura=Integer.parseInt(request.getParameter("fattura"));
		System.out.println("Ho ricevuto la fattura di id: "+idFattura);
		EntityManager emTemp=JPAUtil.getInstance().getEmf().createEntityManager();
		Pagamento pagamento=JPALuke.searchPagamento(idFattura,emTemp);
		
		
		
		if(pagamento!=null) {
		System.out.println("il gia pagato �: "+pagamento.getGiaPagato());
		request.getSession().removeAttribute("pagamento");
		request.getSession().setAttribute("pagamento", pagamento);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(pagamento));
		
		God.seesEverythings(request, response, om.writeValueAsString(pagamento));
		
		emTemp.close();
		}else {
			request.getSession().setAttribute("pagamento", null);
			request.getSession().removeAttribute("fatturaId");
			request.getSession().setAttribute("fatturaId", idFattura);
			
			God.seesEverythings(request, response, null);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.Scadenziario;
import models.Fattura;
import models.Persona;
import utils.JPAUtil;


@WebServlet("/notifica")
public class NotificaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public NotificaController() {
        super();
        

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Integer anticipoNotifica=Integer.parseInt(request.getParameter("listaNotifica"));
		
		
		
		 
		Persona persona= (Persona) request.getSession().getAttribute("user");
		 
		EntityManager emTemp= JPAUtil.getInstance().getEmf().createEntityManager();
		
		List<Fattura> listaFatture=Scadenziario.checkNotifica(persona, anticipoNotifica,emTemp);
		
		 
		 ObjectMapper om = new ObjectMapper();
		 response.setContentType("application/json");
		 response.getWriter().append(om.writeValueAsString(listaFatture));
		 
		 emTemp.close();
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

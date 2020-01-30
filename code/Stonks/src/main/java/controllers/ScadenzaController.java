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
import models.Pagamento;
import models.Persona;
import utils.JPAUtil;


@WebServlet("/scadenza")
public class ScadenzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ScadenzaController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer mesiSuccessivi;
		Integer settimaneSuccessive;
		Boolean entrataOUscita;
			
		try {
			mesiSuccessivi=Integer.parseInt(request.getParameter("numMesi"));
		}catch(Exception e) {
			mesiSuccessivi=null;
		}
		try {
		  settimaneSuccessive=Integer.parseInt(request.getParameter("numSettimane"));
		}catch(Exception e) {
			settimaneSuccessive=null;
		}
		try {
		  entrataOUscita=Boolean.parseBoolean(request.getParameter("entrataUscita"));
		}catch(Exception e) {
			entrataOUscita=null;
		}
		 
		 
		 EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		 
		 // L'id dovrà poi essere preso dall'utente che vuole vedere lo scadenziario
		 Persona persona =em.find(Persona.class , 1 );
		 
		 List<Pagamento> scadenzeOttenute=null;
		 
		 if ( mesiSuccessivi==null && settimaneSuccessive== null) {
			 if(entrataOUscita==null) {
				 //System.out.println("eseguo fullscadenziario");
				scadenzeOttenute = Scadenziario.showFullScadenziario(persona);
			 }else {
				 //System.out.println("eseguo entrata concludere");
				scadenzeOttenute = Scadenziario.showEntrataDaConcludere(persona, entrataOUscita);
			 }
			 
		 }else if(mesiSuccessivi!=null) {
			// System.out.println("eseguo mese");
			 if(entrataOUscita ==null) {
				 Scadenziario.showScadenziarioMese(persona, mesiSuccessivi);
			 }else {
				 Scadenziario.showMeseEntrata(persona, mesiSuccessivi,entrataOUscita);
			 }
		 }else {
			 if(entrataOUscita== null) {
				// System.out.println("eseguo settimana");
				 Scadenziario.showScadenziarioSettimana(persona, settimaneSuccessive);
			 }else {
				 Scadenziario.showSettimanaEntrata(persona, settimaneSuccessive, entrataOUscita);
			 }
		 }
		/*     Incompleto
		 * 
		 * 
		 * 
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(scadenzeOttenute));
		*/
		
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

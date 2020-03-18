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
import models.Pagamento;
import models.Persona;
import utils.DataBase;
import utils.God;
import utils.JPAUtil;

@WebServlet("/scadenza")
public class ScadenzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScadenzaController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer mesiSuccessivi;
		Integer settimaneSuccessive;
		String entrataOUscitaS;
		Boolean entrataOUscita;

		try {
			mesiSuccessivi = Integer.parseInt(request.getParameter("numMesi"));
		} catch (Exception e) {
			mesiSuccessivi = null;
		}
		try {
			settimaneSuccessive = Integer.parseInt(request.getParameter("numSettimane"));
		} catch (Exception e) {
			settimaneSuccessive = null;
		}

		entrataOUscitaS = request.getParameter("entrataUscita");
		System.out.println(entrataOUscitaS);

		if (entrataOUscitaS == null || entrataOUscitaS.contentEquals("null")) {
			entrataOUscita = null;

		} else {
			entrataOUscita = Boolean.parseBoolean(entrataOUscitaS);

		}

		String idUserString = request.getParameter("user");
		Integer idUser = Integer.parseInt(idUserString);
		//Persona persona = (Persona) DataBase.getObjectById("p", idUser);

		// Persona persona = (Persona) request.getSession().getAttribute("user");

		List<Fattura> scadenzeOttenute = null;
		EntityManager emTemp = JPAUtil.getInstance().getEmf().createEntityManager();

		if (mesiSuccessivi == null && settimaneSuccessive == null) {
			if (entrataOUscita == null) {

				scadenzeOttenute = Scadenziario.showFullScadenziario(idUser, emTemp);
			} else {

				scadenzeOttenute = Scadenziario.showEntrataDaConcludere(idUser, entrataOUscita, emTemp);
			}

		} else if (mesiSuccessivi != null) {

			if (entrataOUscita == null) {
				scadenzeOttenute = Scadenziario.showScadenziarioMese(idUser, mesiSuccessivi, emTemp);
			} else {
				scadenzeOttenute = Scadenziario.showMeseEntrata(idUser, mesiSuccessivi, entrataOUscita, emTemp);
			}
		} else {

			if (entrataOUscita == null) {
				scadenzeOttenute = Scadenziario.showScadenziarioSettimana(idUser, settimaneSuccessive, emTemp);
			} else {
				scadenzeOttenute = Scadenziario.showSettimanaEntrata(idUser, settimaneSuccessive, entrataOUscita,
						emTemp);
			}
		}

		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(scadenzeOttenute));
		God.seesEverythings(request, response, om.writeValueAsString(scadenzeOttenute));
		emTemp.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

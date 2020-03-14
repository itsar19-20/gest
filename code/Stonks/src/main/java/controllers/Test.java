package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Text;

/**
 * Servlet implementation class FatturaController
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	String message = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("Richiesta get");
		String parameter = request.getParameter("x");
		if (parameter == null) {
			System.out.println(" simple");
			message = "{\"alfa\":\"Questa stringa arriva dalla Servlet.\"}";
		} else {
			System.out.println(" special");
			message = "{\"alfa\":\"Questa stringa arriva dalla Servlet.\","
					+ "\"bravo\":\"Hai scritto '" + parameter + "' alla Servlet.\","
							+ "\"charlie\":11}";
		}
		response.setContentType("application/json");
		response.getWriter().append(message);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Richiesta post");
		String alfa = request.getParameter("alfa");
		String bravo = request.getParameter("bravo");
		String charlie = request.getParameter("charlie");
		if (alfa.isEmpty())
			doGet(request, response);
		else if (bravo.isEmpty())
			message = "Hai scritto '" + alfa + "' alla Servlet.";
		else
			message = "Hai scritto '" + alfa + "' + '" + bravo + "' + '" + charlie + "' alla Servlet.";
		response.getWriter().append(message);

		System.out.println("Risposta post");
	}

}

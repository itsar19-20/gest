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
		String alfa = request.getParameter("alfa");
		String bravo = request.getParameter("bravo");
		String charlie = request.getParameter("charlie");
		if (alfa == null && bravo == null && charlie == null) {
			message = "{\"alfa\":\"Questa stringa arriva dalla Servlet.\"}";
		} else {
			int num = Integer.valueOf(charlie) * 3;
			System.out.print(" special");
			message = "{\"alfa\":\"alfa\" -> "+ alfa +","
					+ "\"bravo\":\"bravo\" -> " + bravo + ","
					+ "\"charlie\":" + num +"}";
		}
		response.setContentType("application/json");
		response.getWriter().append(message);
		System.out.println();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("Richiesta post ");
		String alfa = request.getParameter("alfa");
		String bravo = request.getParameter("bravo");
		String charlie = request.getParameter("charlie");
		if (alfa == null) {
			// doGet(request, response);
			System.out.println();
			System.out.println(request.getPart("alfa"));
			System.out.println(request.getPart("bravo"));
		} else if (bravo == null) {
			message = "{\"alfa\":\"Hai scritto '" + alfa + "' alla Servlet.\"}";
		} else {
			int num = Integer.valueOf(charlie);
			if (num > 0) num = num * 3;
 			message = "{\"alfa\":\"" + alfa + "\",\"bravo\":\"" + bravo + "\",\"charlie\":" + String.valueOf(num) + "}";
		}
		
		response.setContentType("applcation/json");
		response.getWriter().append(message);
	}

}









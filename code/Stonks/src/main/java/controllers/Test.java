package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.log.Log;

import antlr.StringUtils;
import models.TTTesttt;
import utils.God;
import utils.JsonUtil;

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
		God.seesEverythings(request);
		System.out.print("Richiesta get");
		String x = request.getParameter("x");
		String alfa = request.getParameter("alfa");
		String bravo = request.getParameter("bravo");
		String charlie = request.getParameter("charlie");
		if (alfa == null && bravo == null && charlie == null) {
			message = "{\"alfa\":\"Questa stringa arriva dalla Servlet.\"}";
		} else {
			int num = 0;
			if (charlie != null)
				num = Integer.valueOf(charlie) * 3;
			System.out.print(" special");
			message = "{\"alfa\":"+ alfa +","
					+ "\"bravo\":" + bravo + ","
					+ "\"charlie\":" + num +"}";
		}
		response.setContentType("application/json");
		response.getWriter().append(message);
		System.out.println();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		String line, requestString = null;
		try {
			BufferedReader br = request.getReader();
			while ((line = br.readLine()) != null)
				requestString += line;
			requestString = JsonUtil.getRealJsonFromAndroidJson(requestString);
			System.out.println(requestString);
			response.getWriter().append(requestString);
		} catch (Exception e) {
			System.out.println(e.toString());
			response.getWriter().append("Problema");
		}
	}

}









package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import utils.God;

@WebServlet("/aaa")
public class AAA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AAA() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("user"));
		String output = new ObjectMapper().writeValueAsString(null);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().append(output);
		God.seesEverythings(request, response, output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		God.seesEverythings(request, response, null);
	}

}

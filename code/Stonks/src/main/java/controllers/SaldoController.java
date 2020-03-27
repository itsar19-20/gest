package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.ManageConto;
import business.Saldo;
import business.TipoSaldo;
import utils.DataBase;
import utils.God;
import models.Conto;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/conto")
public class SaldoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaldoController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userIdStr = request.getParameter("user");
    	Integer userId = Integer.valueOf(userIdStr);
    	List<Conto> contitutti = ManageConto.getConti(userId);
    	response.setContentType("application/json;charset=utf-8");
    	response.getWriter().append(new ObjectMapper().writeValueAsString(contitutti));
	}
    
    

}
package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import utils.God;
import utils.XMLParser;


@WebServlet("/leggiXML")
public class LeggiXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LeggiXML() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String stringaXML = request.getParameter("fileXML");
		String userStringa = request.getParameter("idUser");
		String contoStringa=request.getParameter("idConto");
		
		Integer idUser=Integer.parseInt(userStringa);
		
		//Integer idUser=58;
		Integer idConto=Integer.parseInt(contoStringa);
		
		System.out.println(stringaXML);
		File myObj=new File("filename.xml");
		
		 try {
		      //File myObj = new File("filename.xml");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        FileWriter myWriter = new FileWriter(myObj);
		        myWriter.write(stringaXML);
		        myWriter.close();
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
		 XMLParser.parseXML("filename.xml",idUser,idConto);
		 
		 myObj.delete();
		 
		 
		 
		 
		 

		
		//God.seesEverythings(request, response, null);
			
		
		/*
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(listaPagamenti));
		
		God.seesEverythings(request, response, om.writeValueAsString(listaPagamenti));

		
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}


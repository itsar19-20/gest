
package pacco;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;







public class Main {

	public static void main(String[] args) {
		
		  PDFManager pdfManager=new PDFManager();
		  pdfManager.setFilePath("fatturePdf/fat5.pdf");
		  
		  try{
			  String text=pdfManager.toText();
			  System.out.println(text);
			  
			  
		  }catch(IOException ex) {
			  System.out.println(ex.getMessage());
			  
		  }
		
		
		
		}
		

	}



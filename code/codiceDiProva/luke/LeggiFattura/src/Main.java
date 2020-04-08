import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc=builder.parse("fattura1.xml");
			String data=Main.getStringAttr("Data", doc);
			String dataScadenza=Main.getStringAttr("DataScadenzaPagamento", doc);
			String nota=Main.getStringAttr("Descrizione", doc);
			String lordo=Main.getStringAttr("ImportoPagamento", doc);
			System.out.println(data);
			System.out.println(dataScadenza);
			System.out.println(nota);
			System.out.println(lordo);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private static String getStringAttr(String attr, Document doc) {
		NodeList nodeList =doc.getElementsByTagName(attr);
		Node nodo=nodeList.item(0);
		String result="";
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			result=dataElement.getTextContent();
			
		}else {
			System.out.println("Qualcosa non quadra");
		}
		
		return result;
	}
}

package utils;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.Articolo;
import models.ArticoloItem;
import models.Conto;
import models.Fattura;
import models.Persona;

import java.util.HashMap;

public class XMLParser {

	//public static void main(String[] args) {
	static Persona clienteFornitore=null;
	
	public static void parseXML(String fileName,Integer idUser,Integer idConto) {
		DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		
		
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc=builder.parse(fileName);
			
			//Numero della fattura(Nell'xml possono essere piu di 1
			
			int numFatture=XMLParser.getNumFatture(doc);
			
			
			//System.out.println("Numero fatture: "+numFatture);
			
			
			EntityManager emPersona=JPAUtil.getInstance().getEmf().createEntityManager();
			
			
			Conto conto=JPALuke.searchConto(idConto);
			
			
			
			HashMap<String,Boolean> mapBool=XMLParser.isFatturaCliente(doc, conto);
			boolean entrataUscita;
			
			if(mapBool.get("entrata").booleanValue()==false && mapBool.get("uscita").booleanValue()==false) {
				System.out.println("Errore utente con partita iva non trovato");
				emPersona.close();
				return;
			}else if(mapBool.get("entrata").booleanValue()==true) {
				entrataUscita=true;
			}else {
				entrataUscita=false;
			}
			
			
			
			System.out.println("EntrataUscita: "+entrataUscita);
			
			Persona persona;
			if(clienteFornitore==null) {
				
				
			HashMap<String,String> listAnagrafica=XMLParser.getDatiAnagrafici(doc, entrataUscita);
			
			persona =new Persona();
			
			persona.setEliminabile(true);
			persona.setAutore(idUser);
			persona.setCognome("");
			persona.setNome(listAnagrafica.get("nomeAzienda"));
			persona.setIndirizzo(listAnagrafica.get("sede"));
			persona.setMail(listAnagrafica.get("email"));
			persona.setTelefono(listAnagrafica.get("numeroTelefono"));
			if(listAnagrafica.get("partitaIVA")!=null) {
				persona.setpIVA(listAnagrafica.get("partitaIVA"));
			}else {
				persona.setpIVA(listAnagrafica.get("codiceFiscale"));
			}
			
			
			emPersona.getTransaction().begin();
			emPersona.persist(persona);
			emPersona.getTransaction().commit();
			emPersona.close();
			
			}else {
				persona=clienteFornitore;
			}
			
			for(int i=0;i<numFatture;i++) {
				
				EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
				
				Fattura fat=new Fattura();
				//Persona persona=JPALuke.searchPersona(idUser);
				
				
				if(mapBool.get("entrata")==true) {
					fat.seteUnaFatturaCliente(true);
				}else {
					fat.seteUnaFatturaCliente(false);
				}
				//fat.setPersona(persona);
				fat.setConto(conto);
				
				//fat.seteUnaFatturaCliente(true);
				fat.setPagata(false);
				fat.setNotaDiCredito(false);
				
				
				//fat.setAnno(2000);
				
				//fat.setLordo(33.0f);
				//fat.setData(new Date());
				
				//fat.setNumeroFattura("cia");
				//fat.setScadenza(11);
				//fat.setNota("Niente");
				
				
				
				
				
			
				List<ArticoloItem> listaArticoli=XMLParser.getNumArticoli(doc,i);
				//System.out.println("numero articoli: "+listaArticoli.size());
				
				
				
				List<String> values=XMLParser.getData(doc, i);
				//System.out.println("Data Fattura: "+values.get(0));
				String lordo=XMLParser.getLordo(doc, i);
				
				//float lordoFloat=floatFormat(lordo);
				float lordoFloat;
				try {
					
				
				 lordoFloat = Float.parseFloat(lordo);
				}catch(Exception e) {
					lordoFloat=0.00f;
				}
				fat.setLordo(lordoFloat);
				
				if(values.get(2).length()<=250) {
					fat.setNota(values.get(2));
				}else {
					fat.setNota(values.get(2).substring(0,250));
				}
				
				
				
				
				fat.setNumeroFattura(values.get(1));
				
				 //Imposto data anno e scadenza
			
				String scadenza=XMLParser.getScadenza(doc,i);
				toDate(values.get(0),scadenza,fat);
				//System.out.println("Scadenza Fattura: "+scadenza);
				//String numero=XMLParser.getNumeroFattura(doc,i);
				//System.out.println("Numero Fattura: "+values.get(1));
				//String causale=XMLParser.getCausale(doc,i);
				//System.out.println("Causale: "+values.get(2));
				
				
				// Dati Anagrafici
				
				//persona.setId(cPers);
				//cPers++;
			
				
				fat.setPersona(persona);
				
				
				
				
				List<Articolo> articoliFattura=new ArrayList<>();
				for(int j=0;j<listaArticoli.size();j++) {
					
					
					
					Articolo articolo=new Articolo();
					
					ArticoloItem a=listaArticoli.get(j);
					
					//System.out.println("Descrizione :"+a.getDescrizione());
					//System.out.println("Quantita :"+a.getQuantita());
					//System.out.println("PrezzoUnitario :"+a.getPrezzoUnitario());
					//System.out.println("PrezzoTotale :"+a.getPrezzoTotale());
					
					if(a.getDescrizione().length()<=250) {
						articolo.setDescrizione(a.getDescrizione());
					}else {
						
						articolo.setDescrizione(a.getDescrizione().substring(0,250));
					}
					
					float quantita;
					float parziale;
					float totale;
					
					//parte try catch
					try {
						quantita=Float.parseFloat(a.getQuantita());
						
					}catch(Exception e) {
						quantita=0f;
					}
					
					try {
						totale=Float.parseFloat(a.getPrezzoTotale());
					}catch(Exception e) {
						totale=0f;
					}
					
					
					
					
					articolo.setQuantita(quantita);
					articolo.setPrezzo(totale);
					articolo.setFattura(fat);
					
					
					articoliFattura.add(articolo);
					
					
					
					
				}
				fat.setArticoli(articoliFattura);
				
				em.getTransaction().begin();
				em.persist(fat);
				em.getTransaction().commit();
				em.close();
				
			}
			
			
			//List data=Main.getStringAttr("Data", doc);
			//List dataScadenza=Main.getStringAttr("DataScadenzaPagamento", doc);
			//List descrizione=Main.getStringAttr("Descrizione", doc);
			//List lordo=Main.getStringAttr("ImportoPagamento", doc);
			//List quantita =Main.getStringAttr("Quantita", doc);
			//List prezzoUnitario=Main.getStringAttr("PrezzoUnitario", doc);
			//List prezzoTotale=Main.getStringAttr("PrezzoTotale", doc);
			
			
			//System.out.println("Data: "+ data);
			////System.out.println("Data scadenza: "+dataScadenza);
			//System.out.println("Lordo: "+ lordo);
			//System.out.println("Quantita: "+quantita);
			//System.out.println("Prezzo unitario: "+ prezzoUnitario);
			//System.out.println("Prezzo totale: "+prezzoTotale);
			
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
		
	//}
	private static List getStringAttr(String attr, Document doc) {
		NodeList nodeList =doc.getElementsByTagName(attr);
		List<String> lista=new ArrayList<>();
		int j=nodeList.getLength();
		for(int i=0;i<j;i++) {
			Node nodo=nodeList.item(i);
			if(nodo.getNodeType()==Node.ELEMENT_NODE) {
				Element dataElement=(Element) nodo;
				lista.add(dataElement.getTextContent());
				System.out.println(dataElement.getNodeName());
				System.out.println(dataElement.getTextContent());
				
				
			}else {
				System.out.println("Qualcosa non quadra");
			}
			
		}
		
		
		
		
		//System.out.println("numero nodi: "+j);
		
		return lista;
	}
	
	private static int getNumFatture(Document doc) {
		
		NodeList nodeList =doc.getElementsByTagName("Numero");
		int n=nodeList.getLength();
		return n;
	}
	/*
	public static int getNumArticoli(Document doc) {
		NodeList nodeList =doc.getElementsByTagName("NumeroLinea");
		int n=nodeList.getLength();
		return n;
		
	}
	*/
	
	private static List<ArticoloItem> getNumArticoli(Document doc,int posFattura) {
		//int numArticoli=0;
		List<ArticoloItem> listaArticoli=new ArrayList<>();
		int cont=0;
		
		NodeList nodeList =doc.getElementsByTagName("DatiBeniServizi");
		Node nodo=nodeList.item(posFattura);
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			NodeList nodeList2=dataElement.getChildNodes();
			for(int i=0;i<nodeList2.getLength();i++) {
				Node nodo2=nodeList2.item(i);
				if(nodo2.getNodeType()==Node.ELEMENT_NODE) {
					Element dataElement2=(Element) nodo2;
					if(dataElement2.getNodeName().contentEquals("DettaglioLinee")) {
						NodeList nodeList3=dataElement2.getChildNodes();
						ArticoloItem itemArticolo=new ArticoloItem();
						cont++;
						//System.out.println("Cont: "+cont);
						//System.out.println("Nodelist Lenght: "+nodeList3.getLength());
						
						for(int j=0;j<nodeList3.getLength();j++) {
							Node nodo3=nodeList3.item(j);
							if(nodo3.getNodeType()==Node.ELEMENT_NODE) {
								Element dataElement3=(Element) nodo3;
								//System.out.println("Elemento 3: "+dataElement3.getNodeName());
								if(dataElement3.getNodeName().contentEquals("Descrizione")) {
									//itemArticolo.add(dataElement3.getTextContent());
									itemArticolo.setDescrizione(dataElement3.getTextContent());
									//System.out.println("Dentro descrizione");
							}
								else if(dataElement3.getNodeName().contentEquals("Quantita")) {
									itemArticolo.setQuantita(dataElement3.getTextContent());
								}
								else if(dataElement3.getNodeName().contentEquals("PrezzoUnitario")) {
									itemArticolo.setPrezzoUnitario(dataElement3.getTextContent());
								}
								else if(dataElement3.getNodeName().contentEquals("PrezzoTotale")) {
									itemArticolo.setPrezzoTotale(dataElement3.getTextContent());
								}
						}
						
						
						
						}
						listaArticoli.add(itemArticolo);
					}
				}
			}
		
		
		}
		return listaArticoli;
	}
	
	private static List<String> getData(Document doc,int posFattura) {
		List<String> listaValues=new ArrayList<>();
		NodeList nodeList =doc.getElementsByTagName("DatiGeneraliDocumento");
		Node nodo=nodeList.item(posFattura);
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			NodeList nodeList2=dataElement.getChildNodes();
			for(int i=0;i<nodeList2.getLength();i++) {
				Node nodo2=nodeList2.item(i);
				if(nodo2.getNodeType()==Node.ELEMENT_NODE) {
					Element dataElement2=(Element) nodo2;
					if(dataElement2.getNodeName().contentEquals("Data")) {
						listaValues.add(dataElement2.getTextContent());
					
					}else if(dataElement2.getNodeName().contentEquals("Numero")) {
						listaValues.add(dataElement2.getTextContent());
					}else if(dataElement2.getNodeName().contentEquals("Causale")) {
						if(listaValues.size()<=2) {
							listaValues.add(dataElement2.getTextContent());
							
							}else {
								String temp=listaValues.get(2);
								String newString=temp + dataElement2.getTextContent();
								listaValues.set(2, newString);
							}
						}
				}
				
			}
		}
		
		return listaValues;
	}
	
	private static String getLordo(Document doc,int posFattura) {
		
		NodeList nodeList =doc.getElementsByTagName("ImportoPagamento");
		return XMLParser.getItem(nodeList, posFattura);
	}
	
	private static String getScadenza(Document doc,int posFattura) {
		
		NodeList nodeList =doc.getElementsByTagName("DataScadenzaPagamento");
		return XMLParser.getItem(nodeList, posFattura);
		
	}
	
	private static HashMap<String,Boolean> isFatturaCliente(Document doc,Conto conto) {
		HashMap<String,Boolean> listaBool=new HashMap<>();
		NodeList nodeList=doc.getElementsByTagName("DatiAnagrafici");
		Persona persona=null;
		boolean isUtente=false;
		
		for(int pos=0;pos<2;pos++) {
		
		Node nodo=nodeList.item(pos);
		
		
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			NodeList nodeList2=dataElement.getChildNodes();
			for(int i=0;i<nodeList2.getLength();i++) {
				Node nodo2=nodeList2.item(i);
				if(nodo2.getNodeType()==Node.ELEMENT_NODE) {
					Element dataElement2=(Element) nodo2;
					if(dataElement2.getTagName().contentEquals("CodiceFiscale")) {
						
						 persona=JPALuke.searchPersona(dataElement2.getTextContent());
						 if(persona!=null) {
							 if(persona.getId()==conto.getUtente()) {
								 if(pos==0) {
									 listaBool.put("entrata", true);
									 listaBool.put("uscita", false);
								 }else {
									 listaBool.put("uscita", true);
									 listaBool.put("entrata", false);
								 }
								// persona=null;
							 }else {
								 clienteFornitore=persona;
								 
							 }
						 }
						
					}
					
					if(dataElement2.getTagName().contentEquals("IdFiscaleIVA")){
					NodeList nodeList3=dataElement2.getChildNodes();
					for(int j=0;j<nodeList3.getLength();j++) {
						Node nodo3=nodeList3.item(j);
						if(nodo3.getNodeType()==Node.ELEMENT_NODE) {
							Element element3=(Element) nodo3;
							if(element3.getTagName().contentEquals("IdCodice")) {
								//System.out.println("dentro l' if "+pos);
								 persona=JPALuke.searchPersona(element3.getTextContent());
								 if(persona!=null) {
									 if(persona.getId()==conto.getUtente()) {
										 if(pos==0) {
											 listaBool.put("entrata", true);
											 listaBool.put("uscita", false);
										 }else {
											 listaBool.put("uscita", true);
											 listaBool.put("entrata", false);
										 }
										// persona=null;
									 }else {
										 clienteFornitore=persona;
										 
									 }
								 }
							}
						}
					}
					}
				}
			}
		}
								/*if(persona==null&&pos==0) {
									listaBool.put("entrata", false);
									listaBool.put("personaEntrata", false);
								}else if(persona==null&&pos==1) {
									listaBool.put("uscita",false);
									listaBool.put("personaUscita", false);
								}else if(persona!=null && pos==0) {
									if(persona.getId()==conto.getUtente()) {
										listaBool.put("entrata", true);
										listaBool.put("personaEntrata", false);
									}else {
										listaBool.put("entrata", false);
										listaBool.put("personaEntrata", true);
										clienteFornitore=persona;
									}
								}else if(persona!=null &&pos==1) {
									if(persona.getId()==conto.getUtente()) {
										listaBool.put("uscita",true);
										listaBool.put("personaUscita", false);
									}else {
										listaBool.put("uscita",false);
										listaBool.put("personaUscita", true);
										clienteFornitore=persona;
									}
								}
							
						*/
					
					
					
				
			
		
		if(listaBool.get("entrata")==null) {
			listaBool.put("entrata", false);
			listaBool.put("uscita", false);
		}
		
		}
		
		
		System.out.println("Entrata: "+listaBool.get("entrata"));
		System.out.println("Uscita: "+listaBool.get("uscita"));
			
		
		
		return listaBool;
	}
	
	private static HashMap<String,String> getDatiAnagrafici(Document doc, boolean fatturaCliente) {
		//String partita="";
		HashMap<String, String> dati=new HashMap<String,String>();
		int pos;
		
		//Sono qui
		
		
		NodeList nodeList=doc.getElementsByTagName("DatiAnagrafici");
		//Node nodo;
		if(fatturaCliente) {
			pos=1;
			//nodo=nodeList.item(posFattura*2);
		}else {
			pos=0;
			 //nodo=nodeList.item(posFattura*2+1);
		}
		Node nodo=nodeList.item(pos);
		
		
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			NodeList nodeList2=dataElement.getChildNodes();
			for(int i=0;i<nodeList2.getLength();i++) {
				Node nodo2=nodeList2.item(i);
				if(nodo2.getNodeType()==Node.ELEMENT_NODE) {
					Element dataElement2=(Element) nodo2;
					if(dataElement2.getTagName().contentEquals("CodiceFiscale")) {
						dati.put("codiceFiscale",dataElement2.getTextContent());
					}
					else if(dataElement2.getTagName().contentEquals("IdFiscaleIVA")){
					NodeList nodeList3=dataElement2.getChildNodes();
					for(int j=0;j<nodeList3.getLength();j++) {
						Node nodo3=nodeList3.item(j);
						if(nodo3.getNodeType()==Node.ELEMENT_NODE) {
							Element element3=(Element) nodo3;
							if(element3.getTagName().contentEquals("IdCodice")) {
								dati.put("partitaIVA", element3.getTextContent());
							}
						}
					}
						
					
					}else if(dataElement2.getTagName().contentEquals("Anagrafica")){
						NodeList nodeList3=dataElement2.getChildNodes();
						for(int j=0;j<nodeList3.getLength();j++) {
							Node nodo3=nodeList3.item(j);
							if(nodo3.getNodeType()==Node.ELEMENT_NODE) {
								Element element3=(Element) nodo3;
								if(element3.getTagName().contentEquals("Denominazione")) {
									dati.put("nomeAzienda", element3.getTextContent());
								}
							}
						}
						
					}
				}
				
			}
		}
		NodeList nListContatto;
		NodeList nListSede;
		if(!fatturaCliente) {
			 nListContatto=doc.getElementsByTagName("CedentePrestatore");
			 nListSede=doc.getElementsByTagName("CedentePrestatore");
		}else {
			 nListContatto=doc.getElementsByTagName("CessionarioCommittente");
			 nListSede=doc.getElementsByTagName("CessionarioCommittente");
		}
		
		Node nodoContatto=nListContatto.item(0);
		if(nodoContatto.getNodeType()==Node.ELEMENT_NODE) {
			Element elementContatto=(Element) nodoContatto;
			NodeList nListContatto2=elementContatto.getChildNodes();
			for(int i=0;i<nListContatto2.getLength();i++) {
				Node nodoContatto2=nListContatto2.item(i);
				if(nodoContatto2.getNodeType()==Node.ELEMENT_NODE) {
					Element elementContatto2=(Element) nodoContatto2;
					if(elementContatto2.getTagName().contentEquals("Contatti")) {
						NodeList nListContatto3=elementContatto2.getChildNodes();
						for(int j=0;j<nListContatto3.getLength();j++) {
							Node nodoContatto3=nListContatto3.item(j);
							if(nodoContatto3.getNodeType()==Node.ELEMENT_NODE) {
								Element elementContatto3=(Element) nodoContatto3;
								if(elementContatto3.getTagName().contentEquals("Telefono")) {
									dati.put("numeroTelefono",elementContatto3.getTextContent());
								}else if(elementContatto3.getTagName().contentEquals("Email")) {
									dati.put("email", elementContatto3.getTextContent());
								}
							}
						}
					}
		
		
						}
					}
				}
		
		Node nodoSede=nListSede.item(0);
		if(nodoSede.getNodeType()==Node.ELEMENT_NODE) {
			Element elementSede=(Element) nodoSede;
			NodeList nListSede2=elementSede.getChildNodes();
			for(int i=0;i<nListSede2.getLength();i++) {
				Node nodoSede2=nListSede2.item(i);
				if(nodoSede2.getNodeType()==Node.ELEMENT_NODE) {
					Element elementSede2=(Element) nodoSede2;
					if(elementSede2.getTagName().contentEquals("Sede")) {
						NodeList nListSede3=elementSede2.getChildNodes();
						for(int j=0;j<nListSede3.getLength();j++) {
							Node nodoSede3=nListSede3.item(j);
							if(nodoSede3.getNodeType()==Node.ELEMENT_NODE) {
								Element elementSede3=(Element) nodoSede3;
								if(elementSede3.getTagName().contentEquals("Indirizzo")) {
									dati.put("sede",elementSede3.getTextContent());
								}
								
							}
						}
					}
		
		
						}
					}
				}
		
		
		
		
		return dati;
	}
	
	private static String getNumeroFattura(Document doc,int posFattura) {
		NodeList nodeList =doc.getElementsByTagName("Numero");
		return XMLParser.getItem(nodeList, posFattura);
		
	}
	
	private static String getCausale(Document doc,int posFattura) {
		NodeList nodeList =doc.getElementsByTagName("Causale");
		return XMLParser.getItem(nodeList, posFattura);
		
	}
	
	private static String getQuantita(Document doc,int posFattura,int posArticolo ) {
		String quantita="";
		
		return quantita;
	}
	
	private static String getItem(NodeList nodeList,int posFattura) {
		String item="";
		Node nodo=nodeList.item(posFattura);
		if(nodo.getNodeType()==Node.ELEMENT_NODE) {
			Element dataElement=(Element) nodo;
			item=dataElement.getTextContent();
			
		}

		return item;
	}
	
	private static void toDate(String stringa,String dataScadenza,Fattura fat) {
		
		//int anno=2012;
		int anno=Integer.parseInt(stringa.substring(0, 4));
		int mese=Integer.parseInt(stringa.substring(5,7))-1;
		int giorno=Integer.parseInt(stringa.substring(8,10));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal.set(Calendar.YEAR, anno);
		cal.set(Calendar.MONTH, mese);
		cal.set(Calendar.DAY_OF_MONTH,giorno);
		Date data=cal.getTime();
		
		int annoScad=Integer.parseInt(dataScadenza.substring(0, 4));
		int meseScad=Integer.parseInt(dataScadenza.substring(5,7))-1;
		int giornoScad=Integer.parseInt(dataScadenza.substring(8,10));
		
		Calendar cal2= Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		cal2.set(Calendar.YEAR, annoScad);
		cal2.set(Calendar.MONTH, meseScad);
		cal2.set(Calendar.DAY_OF_MONTH,giornoScad);
		
		
		int giorniDiff=(int) ((cal2.getTimeInMillis()-cal.getTimeInMillis())/86400000);
		
		
		fat.setData(data);
		fat.setAnno(anno);
		fat.setScadenza(giorniDiff);
		
	}
	
	/*
	private static float floatFormat(String stringa) {
		if(stringa.contains(".")) {
			int pointPos=stringa.indexOf(".");
			if(stringa.substring(pointPos+1).length()>2) {
				stringa=stringa.substring(0,pointPos+2);
				//float result=Float.valueOf(stringa);
			}else if(stringa.substring(pointPos+1).length()<2) {
				stringa+="0";
				
			}
			
		}
		float result=Float.valueOf(stringa);
		return result;
	}
	
	*/
	
}

package business;

import java.util.ArrayList;
import java.util.List;

import model.Conto;
import model.Pagamento;
import model.Persona;
import model.Utente;
import utils.DateUtil;
import utils.JPAUtils;

public class Scadenziario {
	
	private List<Pagamento> pagamentiDaConcludere;
	
	private List<Pagamento> entrataDaConcludere;
	
	private List<Pagamento> uscitaDaConcludere;
	
	private int anticipoNotifica;
	
	
	
	
	
	
	public Scadenziario(Persona persona) {
		
		
		this.pagamentiDaConcludere=JPAUtils.selectPagamenti(persona);
		this.setAnticipoNotifica(10);
		
		this.uscitaDaConcludere=new ArrayList<Pagamento>();
		this.entrataDaConcludere=new ArrayList<Pagamento>();
		
		
	}
	
	public void addPagamento(Pagamento pagamento) {
		this.getPagamentiDaConcludere().add(pagamento);
	}
	
	public void removePagamento(Pagamento pagamento) {
		for(int i=0;i<this.getPagamentiDaConcludere().size();i++) {
			if(this.getPagamentiDaConcludere().get(i).getIdPagamento().equals(pagamento.getIdPagamento())) {
				this.getPagamentiDaConcludere().remove(i);
				break;
			}
			
		}
		
		
		
	}
	
	
	public List<Pagamento> getPagamentiDaConcludere() {
		return this.pagamentiDaConcludere;
	}

	public void setPagamentiDaConcludere(List<Pagamento> pagamentiDaConcludere) {
		this.pagamentiDaConcludere = pagamentiDaConcludere;
	} 
	
	
	public List<Pagamento> getEntrataDaConcludere() {
		return entrataDaConcludere;
	}

	public void setEntrataDaConcludere(List<Pagamento> entrataDaConcludere) {
		this.entrataDaConcludere = entrataDaConcludere;
	}

	public List<Pagamento> getUscitaDaConcludere() {
		return uscitaDaConcludere;
	}

	public void setUscitaDaConcludere(List<Pagamento> uscitaDaConcludere) {
		this.uscitaDaConcludere = uscitaDaConcludere;
	}

	public int getAnticipoNotifica() {
		return anticipoNotifica;
	}


	public void setAnticipoNotifica(int anticipoNotifica) {
		this.anticipoNotifica = anticipoNotifica;
	}
	
	public void showFullScadenziario() {
		for(Pagamento p:this.getPagamentiDaConcludere()) {
			System.out.println("Id: "+p.getIdPagamento()+"\nScadenza: "+p.getFattura().getData()+
					"\nEntrata: "+p.getFattura().isFatturaCliente()+
					"\nSaldo Dovuto: "+p.getFattura().getLordo());
		}
		
	}
	
	public void showScadenziarioMese() {
		
	}
	
	
	
	

	
	public void checkLoginNotifica() {
			
		}
		
	}




	
	



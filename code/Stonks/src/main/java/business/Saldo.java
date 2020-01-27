package business;

import java.util.ArrayList;

import models.Conto;
import models.Fattura;
import models.Pagamento;

public class Saldo {
	
	private Conto conto = new Conto();
	private ArrayList<Fattura> fatture;
	private ArrayList<Pagamento> pagamenti;
	private Integer idFatture = new Conto().getIdFatture();
	private Integer idPagamenti = new Conto().getIdPagamenti();
	
		public void calcolaSaldo(String str) {
			if (str == "utile") {
				if(idFatture != 0) {
					for(; idFatture <= fatture.size(); idFatture++ ) {
						Fattura f = fatture.get(idFatture);
						conto.setSaldoUtile(conto.getSaldoUtile() + f.getLordo());
						conto.setIdFatture(f.getIdFattura());
					}
				}else {
					for(Fattura f : fatture) {
						conto.setSaldoUtile(conto.getSaldoUtile() + f.getLordo());
						conto.setIdFatture(f.getIdFattura());
					}
				}
	
			}else if(str == "disponibile") {
					if(idPagamenti != 0) {
						for(; idPagamenti <= pagamenti.size(); idPagamenti++ ) {
							Pagamento p = pagamenti.get(idPagamenti);
							conto.setSaldoDisponibile(conto.getSaldoDisponibile() + p.getGiaPagato());
							conto.setIdPagamenti(p.getIdPagamento());
						}
					}else {
						for(Pagamento p : pagamenti) {
							conto.setSaldoDisponibile(conto.getSaldoDisponibile() + p.getGiaPagato());
							conto.setIdPagamenti(p.getIdPagamento());
						}
					}
			}else {
				System.out.println("COGLIONE HAI SBAGLIATO.");
			}
	
		}
}

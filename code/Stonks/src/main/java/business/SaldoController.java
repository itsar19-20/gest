package business;

import models.Conto;
import models.Fattura;

public class SaldoController {
	
	private Conto conto = new Conto();
	
		public void calcolaSaldo(String str) {
			if (str == "utile") {
				if(idFatture != 0) {
					for(Fattura f : fatture[idFatture]) {
						conto.getSaldoUtile = this.saldoUtile + f.getLordo();
						conto.idFatture = f.getIdFattura();
					}
				}else {
					for(Fattura f : fatture) {
						this.saldoUtile = this.saldoUtile + f.getLordo();
						this.idFatture = f.getIdFattura();
				}
			}
		}
	
	}
	
}

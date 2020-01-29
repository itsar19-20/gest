package business;

import java.util.Date;

import models.Conto;
import models.Fattura;
import models.Pagamento;

public class Saldo {

	public float saldo(Conto conto, Date data, TipoSaldo tipoSaldo) {
		float totale = 0.0F;
		switch (tipoSaldo) {
		case utile:
			for (Fattura f : conto.getFatture()) {
				if (f.getData().getTime() <= data.getTime()) {
					totale += f.getLordo();
				}
			}
			break;
		case disponibile:
			for (Pagamento p : conto.getPagamenti()) {
				if (p.getDataPagamento().getTime() <= data.getTime()) {
					totale += p.getGiaPagato();
				}
			}
			break;
		}
		return totale;
	}
}

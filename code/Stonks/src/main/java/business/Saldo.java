package business;

import java.util.Date;
import models.Conto;
import models.Fattura;
import models.Pagamento;

public class Saldo {
	
	float s=0;

	@SuppressWarnings("null")
	public static Saldo saldo(Conto conto, Date data, TipoSaldo tipoSaldo) {
		Saldo totale = null;
		switch (tipoSaldo) {
		case utile:
			for (Fattura f : conto.getFatture()) {
				if (f.getData().getTime() <= data.getTime()) {
					totale.s += f.getLordo();
				}
			}
			conto.setSaldoUtile(totale.s);
			break;
		case disponibile:
			for (Pagamento p : conto.getPagamenti()) {
				if (p.getDataPagamento().getTime() <= data.getTime()) {
					totale.s += p.getGiaPagato();
				}
			}
			conto.setSaldoDisponibile(totale.s);
			break;
		}
		return totale;
	}
}

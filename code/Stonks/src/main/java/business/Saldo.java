package business;

import java.util.Date;
import models.Conto;
import models.Fattura;
import models.Pagamento;

public class Saldo {
	


	@SuppressWarnings("null")
	public static float saldo(Conto conto, Date data, TipoSaldo tipoSaldo) {
		float s=0;
		switch (tipoSaldo) {
		case utile:
			for (Fattura f : conto.getFatture()) {
				if (f.getData().getTime() <= data.getTime()) {
					s += f.getLordo();
				}
			}
			conto.setSaldoUtile(s);
			break;
		case disponibile:
			for (Pagamento p : conto.getPagamenti()) {
				if (p.getDataPagamento().getTime() <= data.getTime()) {
					s += p.getGiaPagato();
				}
			}
			conto.setSaldoDisponibile(s);
			break;
		}
		ContoManager.update(conto);
		return s;
	}
}

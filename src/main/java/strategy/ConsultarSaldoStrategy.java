package strategy;

import models.CajeroModel;
import views.CajeroView;

/*
 * Estrategia para consultar el saldo de la cuenta actual.
 */
public class ConsultarSaldoStrategy implements OperacionStrategy {

    /*
     * Muestra el saldo disponible de la cuenta actual.
     */
    @Override
    public void ejecutar(CajeroModel model, CajeroView view) {
        double saldo = model.getCuentaActual().getSaldo();
        view.mostrarMensaje("Tu saldo actual es: $" + saldo);
    }
}

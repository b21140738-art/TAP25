package strategy;

import models.CajeroModel;
import views.CajeroView;

/*
 * Estrategia para cambiar el PIN de la cuenta actual.
 */
public class CambiarPinStrategy implements OperacionStrategy {

    /*
     * Solicita el PIN actual y el nuevo PIN, y actualiza la cuenta si los datos son correctos.
     *
     * @param model Modelo con la lógica del cajero.
     * @param view Vista para mostrar mensajes al usuario.
     */
    @Override
    public void ejecutar(CajeroModel model, CajeroView view) {
        String pinActual = view.solicitarPin();
        String nuevoPin = view.solicitarPin();

        if (model.cambiarNip(pinActual, nuevoPin)) {
            view.mostrarMensaje("PIN cambiado exitosamente");
        } else {
            view.mostrarMensaje("Error al cambiar el PIN");
        }
    }
}

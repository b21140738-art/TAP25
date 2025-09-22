package strategy;

import models.CajeroModel;
import views.CajeroView;

/**
 * Interfaz que define el contrato para todas las operaciones del cajero automatico
 * Cada estrategia implementará el método {@code ejecutar} con la lógica específica.
 */
public interface OperacionStrategy {

    /**
     * Ejecuta la operación del cajero automático.
     *
     * @param model Modelo que maneja los datos de cuentas y operaciones.
     * @param view Vista encargada de la interacción con el usuario.
     */
    void ejecutar(CajeroModel model, CajeroView view);
}

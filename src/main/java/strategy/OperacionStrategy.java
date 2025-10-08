package strategy;

import models.CajeroModel;
import views.CajeroView;

/*Interfaz que define el contrato para todas las operaciones del cajero automatico
 Cada estrategia implementará el método*/
public interface OperacionStrategy {

    /*
     * Ejecuta la operación del cajero automático.
     */
    void ejecutar(CajeroModel model, CajeroView view);
}

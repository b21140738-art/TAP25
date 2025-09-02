package views;

import controls.CajeroController;
import models.CajeroModel;

public class CajeroAutomatico {
    public static void main(String[] args) {

        CajeroModel model = new CajeroModel();
        CajeroView View = new CajeroView();
        CajeroController Controller = new CajeroController(model, View);
        Controller.iniciarSistema();

    }
}

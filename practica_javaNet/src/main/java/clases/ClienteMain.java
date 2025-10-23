package clases;


import clases.controller.ClienteController;
import clases.model.ClienteModel;
import clases.view.ClienteView;

public class ClienteMain {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PUERTO = 8080;

        try {
            ClienteModel modelo = new ClienteModel(HOST, PUERTO);
            ClienteView vista = new ClienteView();
            ClienteController controlador = new ClienteController(modelo, vista);

            controlador.iniciar();

        } catch (Exception e) {
            System.out.println("No se pudo conectar al servidor: " + e.getMessage());
        }
    }
}



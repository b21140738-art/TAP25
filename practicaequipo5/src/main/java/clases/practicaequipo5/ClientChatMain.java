package clases.practicaequipo5;

import clases.practicaequipo5.model.ClienteModel;
import clases.practicaequipo5.view.ClienteView;
import clases.practicaequipo5.controller.ClienteController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientChatMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Primero crear la vista
            ClienteView vista = new ClienteView();
            // Luego el modelo
            ClienteModel modelo = new ClienteModel();
            // Finalmente el controlador
            ClienteController controlador = new ClienteController(vista, modelo);

            Scene scene = new Scene(vista.getRoot(), 700, 500);
            primaryStage.setTitle("Cliente de Chat - Puerto 9090");
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setOnCloseRequest(e -> {
                modelo.desconectar();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
 package clases.practicaequipo5;

 import clases.practicaequipo5.controller.ClienteController;
 import clases.practicaequipo5.model.ClienteModel;
 import clases.practicaequipo5.view.ClienteView;
 import javafx.application.Application;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 public class ClientChatMain extends Application {

     @Override
     public void start(Stage primaryStage) {
         ClienteView vista = new ClienteView();
         ClienteModel modelo = new ClienteModel();
         ClienteController controlador = new ClienteController(vista, modelo);

         Scene scene = new Scene(vista.getRoot(), 600, 500);
         primaryStage.setTitle("Cliente de Chat - Puerto 9090");
         primaryStage.setScene(scene);
         primaryStage.show();

         primaryStage.setOnCloseRequest(e -> {
             modelo.desconectar();
         });
     }

     public static void main(String[] args) {
         launch(args);
     }
 }
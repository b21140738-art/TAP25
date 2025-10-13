package clases.practica_login.controller;

import clases.practica_login.model.Usuario;
import clases.practica_login.view.bienvenida;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BienvenidaController {
    private Stage stage;
    private LoginController loginController;
    private bienvenida welcomeView;

    public BienvenidaController(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
        this.welcomeView = new bienvenida();
    }

    public void showWelcomeScreen(Usuario usuario) {
        Scene scene = welcomeView.createWelcomeScene(stage, this, usuario);
        stage.setScene(scene);
        stage.setTitle("Bienvenido");
        stage.show();
    }



    public void handleLogout() {
        loginController.showLoginScreen();
    }
}
package clases.practica_login.view;

import clases.practica_login.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
        import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class login{
    private TextField txtUsernameOrEmail;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Label lblError;

    public Scene createLoginScene(Stage stage, LoginController controller) {
        // Título
        Label lblTitle = new Label("Iniciar Sesión");
        lblTitle.setFont(new Font("System Bold", 24));

        // Campos de entrada
        Label lblUsername = new Label("Usuario o Correo:");
        txtUsernameOrEmail = new TextField();
        txtUsernameOrEmail.setPromptText("Ingresa tu usuario o correo");

        Label lblPassword = new Label("Contraseña:");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Ingresa tu contraseña");

        // Botones
        btnLogin = new Button("Iniciar Sesión");
        btnLogin.setPrefWidth(200);
        btnLogin.setOnAction(e -> controller.handleLogin(
                txtUsernameOrEmail.getText(),
                txtPassword.getText()
        ));

        btnRegister = new Button("Registrarse");
        btnRegister.setPrefWidth(200);
        btnRegister.setOnAction(e -> controller.handleRegister());

        // Mensaje de error
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");
        lblError.setVisible(false);

        // Layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                lblTitle,
                lblUsername,
                txtUsernameOrEmail,
                lblPassword,
                txtPassword,
                lblError,
                btnLogin,
                btnRegister
        );

        return new Scene(root, 400, 400);
    }

    // Métodos para que el controlador interactúe con la vista
    public void showError(String message) {
        lblError.setText(message);
        lblError.setVisible(true);
    }

    public void clearError() {
        lblError.setVisible(false);
    }

    public void clearFields() {
        txtUsernameOrEmail.clear();
        txtPassword.clear();
    }
}
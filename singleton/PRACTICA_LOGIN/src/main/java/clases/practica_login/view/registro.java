package clases.practica_login.view;

import clases.practica_login.controller.RegistroController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
        import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class registro {
    private TextField txtNombreCompleto;
    private DatePicker dpFechaNacimiento;
    private TextField txtUsername;
    private TextField txtCorreo;
    private PasswordField txtPassword;
    private PasswordField txtConfirmPassword;
    private Button btnRegister;
    private Button btnCancel;
    private Label lblError;

    public Scene createRegisterScene(Stage stage, RegistroController controller) {
        // Título
        Label lblTitle = new Label("Registro de Usuario");
        lblTitle.setFont(new Font("System Bold", 24));

        // Campos del formulario
        Label lblNombre = new Label("Nombre Completo:");
        txtNombreCompleto = new TextField();
        txtNombreCompleto.setPromptText("Ingresa tu nombre completo");

        Label lblFechaNacimiento = new Label("Fecha de Nacimiento:");
        dpFechaNacimiento = new DatePicker();

        Label lblUsername = new Label("Nombre de Usuario:");
        txtUsername = new TextField();
        txtUsername.setPromptText("Elige un nombre de usuario");

        Label lblCorreo = new Label("Correo Electrónico:");
        txtCorreo = new TextField();
        txtCorreo.setPromptText("Ingresa tu correo electrónico");

        Label lblPassword = new Label("Contraseña:\n"+
                "( Mínimo 8 caracteres, al menos una mayúscula, una minúscula, \n" +
                "un número y un carácter especial)");
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Crea una contraseña segura");

        Label lblConfirmPassword = new Label("Confirmar Contraseña:");
        txtConfirmPassword = new PasswordField();
        txtConfirmPassword.setPromptText("Repite tu contraseña");

        // Barra de progreso para indicar fortaleza de contraseña
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        Label lblPasswordStrength = new Label("Fortaleza de contraseña: ");

        // Escuchar cambios en la contraseña para mostrar fortaleza
        txtPassword.textProperty().addListener((obs, oldVal, newVal) -> {
            double strength = calculatePasswordStrength(newVal);
            progressBar.setProgress(strength);

            if (strength < 0.3) {
                lblPasswordStrength.setText("Fortaleza de contraseña: Débil");
                lblPasswordStrength.setStyle("-fx-text-fill: red;");
            } else if (strength < 0.7) {
                lblPasswordStrength.setText("Fortaleza de contraseña: Media");
                lblPasswordStrength.setStyle("-fx-text-fill: orange;");
            } else {
                lblPasswordStrength.setText("Fortaleza de contraseña: Fuerte");
                lblPasswordStrength.setStyle("-fx-text-fill: green;");
            }
        });

        // Mensaje de error
        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");
        lblError.setVisible(false);

        // Botones
        btnRegister = new Button("Registrar");
        btnRegister.setPrefWidth(200);
        btnRegister.setOnAction(e -> controller.handleRegister(
                txtNombreCompleto.getText(),
                dpFechaNacimiento.getValue(),
                txtUsername.getText(),
                txtCorreo.getText(),
                txtPassword.getText(),
                txtConfirmPassword.getText()
        ));

        btnCancel = new Button("Cancelar");
        btnCancel.setPrefWidth(200);
        btnCancel.setOnAction(e -> controller.handleCancel());

        // Layout principal
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(
                lblTitle,
                lblNombre,
                txtNombreCompleto,
                lblFechaNacimiento,
                dpFechaNacimiento,
                lblUsername,
                txtUsername,
                lblCorreo,
                txtCorreo,
                lblPassword,
                txtPassword,
                lblConfirmPassword,
                txtConfirmPassword,
                lblPasswordStrength,
                progressBar,
                lblError,
                btnRegister,
                btnCancel
        );

        return new Scene(root, 450, 650);
    }

    private double calculatePasswordStrength(String password) {
        if (password == null || password.isEmpty()) return 0;

        double strength = 0;

        // Longitud mínima
        if (password.length() >= 8) strength += 0.2;

        // Mayúsculas y minúsculas
        if (password.matches(".*[A-Z].*")) strength += 0.2;
        if (password.matches(".*[a-z].*")) strength += 0.2;

        // Números
        if (password.matches(".*\\d.*")) strength += 0.2;

        // Caracteres especiales
        if (password.matches(".*[@$!%*?&].*")) strength += 0.2;

        return Math.min(strength, 1.0);
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
        txtNombreCompleto.clear();
        dpFechaNacimiento.setValue(null);
        txtUsername.clear();
        txtCorreo.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }
}

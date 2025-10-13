package clases.practica_login.controller;

import clases.practica_login.model.Usuario;
import clases.practica_login.service.autentificar;
import clases.practica_login.view.registro;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class RegistroController {
    private Stage stage;
    private LoginController loginController;
    private autentificar authService;
    private registro registerView;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public RegistroController(Stage stage, LoginController loginController) {
        this.stage = stage;
        this.loginController = loginController;
        this.authService = new autentificar();
        this.registerView = new registro();
    }

    public void showRegisterScreen() {
        Scene scene = registerView.createRegisterScene(stage, this);
        stage.setScene(scene);
        stage.setTitle("Registro de Usuario");
        stage.show();
    }

    public void handleRegister(String nombreCompleto, LocalDate fechaNacimiento,
                               String username, String correo, String password, String confirmPassword) {
        if (!validarCampos(nombreCompleto, fechaNacimiento, username, correo, password, confirmPassword)) {
            return;
        }

        try {
            Usuario usuario = new Usuario(
                    username.trim(),
                    correo.trim().toLowerCase(),
                    "", // El hash se generará en el servicio
                    nombreCompleto.trim(),
                    fechaNacimiento
            );

            boolean registrado = authService.registrarUsuario(usuario, password);

            if (registrado) {
                showAlert("Éxito", "Usuario registrado correctamente");
                handleCancel();
            } else {
                registerView.showError("Error al registrar usuario");
            }

        } catch (IllegalArgumentException e) {
            registerView.showError(e.getMessage());
        } catch (Exception e) {
            registerView.showError("Error al registrar usuario: " + e.getMessage());
        }
    }

    public void handleCancel() {
        loginController.showLoginScreen();
    }

    private boolean validarCampos(String nombreCompleto, LocalDate fechaNacimiento,
                                  String username, String correo, String password, String confirmPassword) {
        // Validar campos vacíos
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty() ||
                fechaNacimiento == null ||
                username == null || username.trim().isEmpty() ||
                correo == null || correo.trim().isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {

            registerView.showError("Todos los campos son obligatorios");
            return false;
        }

        // Validar formato de correo
        if (!Pattern.matches(EMAIL_REGEX, correo.trim())) {
            registerView.showError("Formato de correo electrónico inválido");
            return false;
        }

        // Validar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            registerView.showError("Las contraseñas no coinciden");
            return false;
        }

        // Validar si el usuario ya existe
        if (authService.existeUsername(username.trim())) {
            registerView.showError("El nombre de usuario ya está en uso");
            return false;
        }

        // Validar si el correo ya existe
        if (authService.existeCorreo(correo.trim())) {
            registerView.showError("El correo electrónico ya está registrado");
            return false;
        }

        // Validar fecha de nacimiento (edad mínima 13 años)
        LocalDate fechaMinima = LocalDate.now().minusYears(13);
        if (fechaNacimiento.isAfter(fechaMinima)) {
            registerView.showError("Debes tener al menos 13 años para registrarte");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.INFORMATION
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

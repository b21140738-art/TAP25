package clases.practica_login.view;
import clases.practica_login.controller.BienvenidaController;
import clases.practica_login.model.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class bienvenida {
    private Label lblNombreCompleto;
    private Label lblUsername;
    private Label lblCorreo;
    private Label lblFechaNacimiento;
    private Label lblFechaRegistro;
    private Button btnLogout;

    public Scene createWelcomeScene(Stage stage, BienvenidaController controller, Usuario usuario) {
        // Título de bienvenida
        Label lblTitle = new Label("¡Bienvenido!");
        lblTitle.setFont(new Font("System Bold", 28));

        // Información del usuario
        Label lblInfoTitle = new Label("Información de tu cuenta:");
        lblInfoTitle.setFont(new Font("System Bold", 16));

        lblNombreCompleto = new Label();
        lblUsername = new Label();
        lblCorreo = new Label();
        lblFechaNacimiento = new Label();
        lblFechaRegistro = new Label();

        // Actualizar información del usuario
        updateUserInfo(usuario);

        // Separador
        Separator separator = new Separator();
        separator.setPrefWidth(300);

        // Botón de cerrar sesión
        btnLogout = new Button("Cerrar Sesión");
        btnLogout.setPrefWidth(150);
        btnLogout.setOnAction(e -> controller.handleLogout());

        // Layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                lblTitle,
                lblInfoTitle,
                crearInfoRow("Nombre:", lblNombreCompleto),
                crearInfoRow("Usuario:", lblUsername),
                crearInfoRow("Correo:", lblCorreo),
                crearInfoRow("Fecha de Nacimiento:", lblFechaNacimiento),
                crearInfoRow("Fecha de Registro:", lblFechaRegistro),
                separator,
                btnLogout
        );

        return new Scene(root, 500, 500);
    }


    private VBox crearInfoRow(String titulo, Label valor) {
        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-weight: bold;");

        VBox row = new VBox(5);
        row.setAlignment(Pos.CENTER_LEFT);
        row.getChildren().addAll(lblTitulo, valor);

        return row;
    }

    private void updateUserInfo(Usuario usuario) {
        if (usuario != null) {
            lblNombreCompleto.setText(usuario.getNombreCompleto());
            lblUsername.setText("@" + usuario.getUsername());
            lblCorreo.setText(usuario.getCorreo());

            if (usuario.getFechaNacimiento() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                lblFechaNacimiento.setText(usuario.getFechaNacimiento().format(formatter));
            }

            if (usuario.getFechaRegistro() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                lblFechaRegistro.setText(usuario.getFechaRegistro().format(formatter));
            }
        }
    }

}
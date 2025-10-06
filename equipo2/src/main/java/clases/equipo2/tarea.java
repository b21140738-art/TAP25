package clases.equipo2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class tarea extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Label
        Label lblTitle = new Label("Formulario de Registro");

        // 2. TextField
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Ingresa tu nombre");

        // 3. PasswordField
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Ingresa tu contraseña");

        // 4. TextArea
        TextArea txtComentario = new TextArea();
        txtComentario.setPromptText("Comentarios adicionales...");
        txtComentario.setPrefRowCount(3);

        // 5. CheckBox
        CheckBox chkTerminos = new CheckBox("Acepto los términos y condiciones");

        // 6 y 7. RadioButton con ToggleGroup
        ToggleGroup generoGroup = new ToggleGroup();
        RadioButton rbHombre = new RadioButton("Hombre");
        RadioButton rbMujer = new RadioButton("Mujer");
        rbHombre.setToggleGroup(generoGroup);
        rbMujer.setToggleGroup(generoGroup);

        HBox generoBox = new HBox(10, rbHombre, rbMujer);

        // 8. ComboBox
        ComboBox<String> cbPais = new ComboBox<>();
        cbPais.getItems().addAll("México", "Argentina", "Colombia", "España");
        cbPais.setPromptText("Selecciona tu país");

        // 9. Slider
        Slider sldEdad = new Slider(0, 100, 18);
        sldEdad.setShowTickLabels(true);
        sldEdad.setShowTickMarks(true);

        Label lblEdad = new Label("Edad: 18 años");
        sldEdad.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblEdad.setText("Edad: " + newVal.intValue() + " años");
        });

        // 10. ProgressBar
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(200);

        // Botón (Button)
        Button btnEnviar = new Button("Enviar");
        btnEnviar.setOnAction(e -> {
            progressBar.setProgress(1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro completado");
            alert.setHeaderText(null);
            alert.setContentText("¡Datos enviados con éxito!");
            alert.showAndWait();
        });

        // Layout principal
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(
                lblTitle,
                txtNombre,
                txtPassword,
                cbPais,
                generoBox,
                lblEdad,
                sldEdad,
                chkTerminos,
                txtComentario,
                btnEnviar,
                progressBar
        );

        // Escena
        Scene scene = new Scene(root, 350, 500);
        primaryStage.setTitle("Ejemplo con 10 elementos de JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

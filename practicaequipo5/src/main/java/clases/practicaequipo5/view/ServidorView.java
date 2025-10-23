package clases.practicaequipo5.view;


import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ServidorView {
    private BorderPane root;
    private TextArea areaLog;
    private Button botonIniciar;
    private Button botonDetener;
    private Label estadoLabel;

    public ServidorView() {
        crearVista();
    }

    private void crearVista() {
        root = new BorderPane();
        root.setPadding(new Insets(10));

        // Panel superior
        VBox panelSuperior = new VBox(10);
        panelSuperior.setPadding(new Insets(10));

        estadoLabel = new Label("Servidor Detenido");
        estadoLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

        HBox panelBotones = new HBox(10);
        botonIniciar = new Button("Iniciar Servidor");
        botonDetener = new Button("Detener Servidor");
        botonDetener.setDisable(true);

        panelBotones.getChildren().addAll(botonIniciar, botonDetener);
        panelSuperior.getChildren().addAll(estadoLabel, panelBotones);

        // Área de log
        areaLog = new TextArea();
        areaLog.setEditable(false);
        areaLog.setFont(Font.font("Monospaced", 12));
        areaLog.setPrefHeight(500);

        root.setTop(panelSuperior);
        root.setCenter(areaLog);
    }

    // Getters
    public BorderPane getRoot() { return root; }
    public TextArea getAreaLog() { return areaLog; }
    public Button getBotonIniciar() { return botonIniciar; }
    public Button getBotonDetener() { return botonDetener; }
    public Label getEstadoLabel() { return estadoLabel; }

    public void agregarLog(String mensaje) {
        areaLog.appendText(mensaje + "\n");
    }

    public void setEstadoServidor(boolean activo) {
        if (activo) {
            estadoLabel.setText("Servidor Activo - Puerto 9090");
            estadoLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
            botonIniciar.setDisable(true);
            botonDetener.setDisable(false);
        } else {
            estadoLabel.setText("Servidor Detenido");
            estadoLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");
            botonIniciar.setDisable(false);
            botonDetener.setDisable(true);
        }
    }
}
package clases.practicaequipo5.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClienteView {
    private BorderPane root;
    private TextArea areaMensajes;
    private TextField campoMensaje;
    private Button botonEnviar;
    private Button botonConectar;
    private Button botonDesconectar;
    private Button botonHistorial;
    private TextField campoHost;
    private Label estadoLabel;
    private ToggleGroup grupoModo;
    private RadioButton radioNormal, radioMayus, radioMinus;
    private Label modoLabel;

    public ClienteView() {
        crearVista();
    }

    private void crearVista() {
        root = new BorderPane();
        root.setPadding(new Insets(10));

        // Panel superior para conexión
        HBox panelConexion = new HBox(10);
        panelConexion.setPadding(new Insets(10));
        panelConexion.setAlignment(Pos.CENTER_LEFT);

        campoHost = new TextField("localhost");
        campoHost.setPromptText("Host");
        campoHost.setPrefWidth(100);

        botonConectar = new Button("Conectar");
        botonDesconectar = new Button("Desconectar");
        botonDesconectar.setDisable(true);

        botonHistorial = new Button("Ver Historial");
        botonHistorial.setDisable(true);

        estadoLabel = new Label("Desconectado");
        estadoLabel.setStyle("-fx-text-fill: red;");

        panelConexion.getChildren().addAll(
                new Label("Servidor:"), campoHost,
                botonConectar, botonDesconectar, botonHistorial, estadoLabel
        );

        // Panel de selección de modo
        HBox panelModo = new HBox(10);
        panelModo.setPadding(new Insets(5, 10, 5, 10));
        panelModo.setAlignment(Pos.CENTER_LEFT);

        modoLabel = new Label("Modo: ");
        grupoModo = new ToggleGroup();

        radioNormal = new RadioButton("Normal");
        radioNormal.setToggleGroup(grupoModo);
        radioNormal.setSelected(true);
        radioNormal.setUserData("NORMAL");

        radioMayus = new RadioButton("MAYÚSCULAS");
        radioMayus.setToggleGroup(grupoModo);
        radioMayus.setUserData("MAYUS");

        radioMinus = new RadioButton("minúsculas");
        radioMinus.setToggleGroup(grupoModo);
        radioMinus.setUserData("MINUS");

        // Deshabilitar los radio buttons hasta que se conecte
        radioNormal.setDisable(true);
        radioMayus.setDisable(true);
        radioMinus.setDisable(true);

        panelModo.getChildren().addAll(
                modoLabel, radioNormal, radioMayus, radioMinus
        );

        // Área de mensajes
        areaMensajes = new TextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setPrefHeight(350);

        // Panel inferior para enviar mensajes
        HBox panelEnvio = new HBox(10);
        panelEnvio.setPadding(new Insets(10));
        panelEnvio.setAlignment(Pos.CENTER_LEFT);

        campoMensaje = new TextField();
        campoMensaje.setPromptText("Escribe tu mensaje aquí... (El modo seleccionado transformará tu texto)");
        campoMensaje.setPrefWidth(300);
        campoMensaje.setDisable(true);

        botonEnviar = new Button("Enviar");
        botonEnviar.setDisable(true);

        panelEnvio.getChildren().addAll(campoMensaje, botonEnviar);

        // Organizar layout
        VBox contenedorPrincipal = new VBox(5);
        contenedorPrincipal.getChildren().addAll(panelConexion, panelModo, areaMensajes, panelEnvio);

        root.setCenter(contenedorPrincipal);
    }

    // Getters para los componentes
    public BorderPane getRoot() { return root; }
    public TextArea getAreaMensajes() { return areaMensajes; }
    public TextField getCampoMensaje() { return campoMensaje; }
    public Button getBotonEnviar() { return botonEnviar; }
    public Button getBotonConectar() { return botonConectar; }
    public Button getBotonDesconectar() { return botonDesconectar; }
    public Button getBotonHistorial() { return botonHistorial; }
    public TextField getCampoHost() { return campoHost; }
    public Label getEstadoLabel() { return estadoLabel; }
    public ToggleGroup getGrupoModo() { return grupoModo; }
    public RadioButton getRadioNormal() { return radioNormal; }
    public RadioButton getRadioMayus() { return radioMayus; }
    public RadioButton getRadioMinus() { return radioMinus; }

    public void agregarMensaje(String mensaje) {
        areaMensajes.appendText(mensaje + "\n");
    }

    public void limpiarCampoMensaje() {
        campoMensaje.clear();
    }

    public void setEstadoConectado(boolean conectado) {
        if (conectado) {
            estadoLabel.setText("Conectado");
            estadoLabel.setStyle("-fx-text-fill: green;");
            botonConectar.setDisable(true);
            botonDesconectar.setDisable(false);
            botonHistorial.setDisable(false);
            campoMensaje.setDisable(false);
            botonEnviar.setDisable(false);
            // Habilitar los radio buttons cuando está conectado
            radioNormal.setDisable(false);
            radioMayus.setDisable(false);
            radioMinus.setDisable(false);
        } else {
            estadoLabel.setText("Desconectado");
            estadoLabel.setStyle("-fx-text-fill: red;");
            botonConectar.setDisable(false);
            botonDesconectar.setDisable(true);
            botonHistorial.setDisable(true);
            campoMensaje.setDisable(true);
            botonEnviar.setDisable(true);
            // Deshabilitar los radio buttons cuando está desconectado
            radioNormal.setDisable(true);
            radioMayus.setDisable(true);
            radioMinus.setDisable(true);
        }
    }

    public String getModoSeleccionado() {
        RadioButton seleccionado = (RadioButton) grupoModo.getSelectedToggle();
        return seleccionado != null ? seleccionado.getUserData().toString() : "NORMAL";
    }
}
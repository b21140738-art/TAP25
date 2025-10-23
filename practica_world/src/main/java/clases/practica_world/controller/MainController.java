package clases.practica_world.controller;

import clases.practica_world.model.DAO.CiudadDAO;
import clases.practica_world.model.DAO.PaisDAO;
import clases.practica_world.model.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private ComboBox<String> comboContinentes;
    @FXML private TextField txtBuscarNombre;
    @FXML private Button btnBuscar;
    @FXML private Button btnLimpiar;
    @FXML private TableView<Pais> tablaPaises;
    @FXML private TableColumn<Pais, String> colCodigo;
    @FXML private TableColumn<Pais, String> colNombre;
    @FXML private TableColumn<Pais, String> colContinente;
    @FXML private TableColumn<Pais, Integer> colPoblacion;

    @FXML private Label lblNombre;
    @FXML private Label lblContinente;
    @FXML private Label lblPoblacion;
    @FXML private Label lblRegion;
    @FXML private Label lblGobierno;
    @FXML private Label lblSuperficie;
    @FXML private Label lblAnioIndependencia;
    @FXML private Label lblJefeEstado;
    @FXML private Label lblEsperanzaVida;
    @FXML private Button btnVerCiudades;

    private PaisDAO paisDAO;
    private CiudadDAO ciudadDAO;
    private ObservableList<Pais> listaPaises;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paisDAO = new PaisDAO();
        ciudadDAO = new CiudadDAO();
        listaPaises = FXCollections.observableArrayList();

        configurarControles();
        cargarContinentes();
        cargarTodosLosPaises();
    }

    private void configurarControles() {
        // Configurar columnas de la tabla
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("code"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContinente.setCellValueFactory(new PropertyValueFactory<>("continent"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("population"));

        tablaPaises.setItems(listaPaises);

        // Configurar selección de tabla
        tablaPaises.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesPais(newValue)
        );

        // Configurar botones
        btnBuscar.setOnAction(e -> buscarPaises());
        btnLimpiar.setOnAction(e -> limpiarFiltros());
        btnVerCiudades.setOnAction(e -> mostrarCiudades());

        // Configurar búsqueda con Enter
        txtBuscarNombre.setOnAction(e -> buscarPaises());
    }

    private void cargarContinentes() {
        List<String> continentes = paisDAO.obtenerContinentes();
        comboContinentes.getItems().add("Todos los continentes");
        comboContinentes.getItems().addAll(continentes);
        comboContinentes.getSelectionModel().selectFirst();
    }

    private void cargarTodosLosPaises() {
        List<Pais> paises = paisDAO.obtenerTodosLosPaises();
        listaPaises.setAll(paises);
        actualizarEstadisticas();
    }

    private void buscarPaises() {
        String continente = comboContinentes.getValue();
        String nombre = txtBuscarNombre.getText().trim();

        // Si seleccionó "Todos los continentes", usar null
        if ("Todos los continentes".equals(continente)) {
            continente = null;
        }

        List<Pais> paises = paisDAO.buscarPaises(continente, nombre);
        listaPaises.setAll(paises);
        actualizarEstadisticas();
    }

    private void limpiarFiltros() {
        comboContinentes.getSelectionModel().selectFirst();
        txtBuscarNombre.clear();
        cargarTodosLosPaises();
    }

    private void mostrarDetallesPais(Pais pais) {
        if (pais != null) {
            lblNombre.setText(pais.getName());
            lblContinente.setText(pais.getContinent());
            lblPoblacion.setText(String.format("%,d", pais.getPopulation()));
            lblRegion.setText(pais.getRegion());
            lblGobierno.setText(pais.getGovernmentForm());
            lblSuperficie.setText(String.format("%,.2f km²", pais.getSurfaceArea()));

            if (pais.getIndepYear() != null) {
                lblAnioIndependencia.setText(pais.getIndepYear().toString());
            } else {
                lblAnioIndependencia.setText("No disponible");
            }

            if (pais.getHeadOfState() != null && !pais.getHeadOfState().isEmpty()) {
                lblJefeEstado.setText(pais.getHeadOfState());
            } else {
                lblJefeEstado.setText("No disponible");
            }

            if (pais.getLifeExpectancy() != null) {
                lblEsperanzaVida.setText(String.format("%.1f años", pais.getLifeExpectancy()));
            } else {
                lblEsperanzaVida.setText("No disponible");
            }

            btnVerCiudades.setDisable(false);
        } else {
            limpiarDetalles();
        }
    }

    private void limpiarDetalles() {
        lblNombre.setText("");
        lblContinente.setText("");
        lblPoblacion.setText("");
        lblRegion.setText("");
        lblGobierno.setText("");
        lblSuperficie.setText("");
        lblAnioIndependencia.setText("");
        lblJefeEstado.setText("");
        lblEsperanzaVida.setText("");
        btnVerCiudades.setDisable(true);
    }

    private void mostrarCiudades() {
        Pais paisSeleccionado = tablaPaises.getSelectionModel().getSelectedItem();
        if (paisSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clases/practica_world/wiew/ciudades.fxml"));
                Parent root = loader.load();

                CiudadesController controller = loader.getController();
                controller.setPais(paisSeleccionado);

                Stage stage = new Stage();
                stage.setTitle("Ciudades de " + paisSeleccionado.getName());
                stage.setScene(new Scene(root, 700, 500));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo cargar la ventana de ciudades.");
            }
        }
    }

    private void actualizarEstadisticas() {
        // Podrías agregar aquí estadísticas como el total de países mostrados, etc.
        int totalPaises = listaPaises.size();
        // Actualizar algún label con esta información si lo deseas
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

package clases.practica_world.controller;
import clases.practica_world.model.Ciudad;
import clases.practica_world.model.DAO.CiudadDAO;
import clases.practica_world.model.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CiudadesController implements Initializable {

    @FXML private Label lblPais;
    @FXML private Label lblTotalCiudades;
    @FXML private TableView<Ciudad> tablaCiudades;
    @FXML private TableColumn<Ciudad, Integer> colId;
    @FXML private TableColumn<Ciudad, String> colNombre;
    @FXML private TableColumn<Ciudad, String> colDistrito;
    @FXML private TableColumn<Ciudad, Integer> colPoblacion;

    private Pais pais;
    private CiudadDAO ciudadDAO;
    private ObservableList<Ciudad> listaCiudades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ciudadDAO = new CiudadDAO();
        listaCiudades = FXCollections.observableArrayList();
        configurarTabla();
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        actualizarVista();
        cargarCiudades();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDistrito.setCellValueFactory(new PropertyValueFactory<>("district"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("population"));

        tablaCiudades.setItems(listaCiudades);
    }

    private void actualizarVista() {
        if (pais != null) {
            lblPais.setText("Ciudades de: " + pais.getName() + " (" + pais.getCode() + ")");
        }
    }

    private void cargarCiudades() {
        if (pais != null) {
            listaCiudades.setAll(ciudadDAO.obtenerCiudadesPorPais(pais.getCode()));
            int totalCiudades = ciudadDAO.obtenerTotalCiudadesPorPais(pais.getCode());
            lblTotalCiudades.setText("Total de ciudades: " + totalCiudades);
        }
    }
}

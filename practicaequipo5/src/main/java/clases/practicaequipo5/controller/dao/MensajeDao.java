package clases.practicaequipo5.controller.dao;

import clases.practicaequipo5.model.Mensaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensajeDao {
    private final DatabaseConnection dbConnection;

    public MensajeDao() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public void guardarMensaje(Mensaje mensaje, String direccionIp) {
        String sql = "INSERT INTO mensajes (contenido, tipo, modo, direccion_ip) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mensaje.getContenido());
            pstmt.setString(2, mensaje.getTipo());
            pstmt.setString(3, mensaje.getModo());
            pstmt.setString(4, direccionIp);
            pstmt.executeUpdate();

            System.out.println("Mensaje guardado en MySQL: " + mensaje.getContenido());

        } catch (SQLException e) {
            System.err.println("Error guardando mensaje en MySQL: " + e.getMessage());
            throw new RuntimeException("Error guardando mensaje en MySQL", e);
        }
    }

    public List<Mensaje> obtenerTodosLosMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT contenido, tipo, modo, fecha FROM mensajes ORDER BY fecha ASC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                        rs.getString("contenido"),
                        rs.getString("tipo"),
                        rs.getString("modo")
                );
                mensajes.add(mensaje);
            }

            System.out.println("Obtenidos " + mensajes.size() + " mensajes desde MySQL");

        } catch (SQLException e) {
            System.err.println("Error obteniendo mensajes de MySQL: " + e.getMessage());
            throw new RuntimeException("Error obteniendo mensajes de MySQL", e);
        }
        return mensajes;
    }

    public List<Mensaje> obtenerMensajesPorIp(String direccionIp) {
        List<Mensaje> mensajes = new ArrayList<>();
        String sql = "SELECT contenido, tipo, modo, fecha FROM mensajes WHERE direccion_ip = ? ORDER BY fecha ASC";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, direccionIp);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje(
                        rs.getString("contenido"),
                        rs.getString("tipo"),
                        rs.getString("modo")
                );
                mensajes.add(mensaje);
            }

            System.out.println("Obtenidos " + mensajes.size() + " mensajes para IP: " + direccionIp);

        } catch (SQLException e) {
            System.err.println("Error obteniendo mensajes por IP desde MySQL: " + e.getMessage());
            throw new RuntimeException("Error obteniendo mensajes por IP desde MySQL", e);
        }
        return mensajes;
    }
}
package clases.practica_world.model.DAO;

import clases.practica_world.model.Ciudad;
import clases.practica_world.model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {

    public List<Ciudad> obtenerCiudadesPorPais(String codigoPais) {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM city WHERE CountryCode = ? ORDER BY Population DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigoPais);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Ciudad ciudad = new Ciudad(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                ciudades.add(ciudad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ciudades;
    }

    public int obtenerTotalCiudadesPorPais(String codigoPais) {
        String sql = "SELECT COUNT(*) as total FROM city WHERE CountryCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigoPais);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
package clases.practica_world.model.DAO;


import clases.practica_world.model.DatabaseConnection;
import clases.practica_world.model.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {

    public List<Pais> obtenerTodosLosPaises() {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT * FROM country ORDER BY Name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pais pais = mapearPais(rs);
                paises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }

    public List<Pais> buscarPaises(String continente, String nombre) {
        List<Pais> paises = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM country WHERE 1=1");
        List<String> parametros = new ArrayList<>();

        if (continente != null && !continente.isEmpty()) {
            sql.append(" AND continent = ?");
            parametros.add(continente);
        }

        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND Name LIKE ?");
            parametros.add("%" + nombre + "%");
        }

        sql.append(" ORDER BY Name");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                pstmt.setString(i + 1, parametros.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Pais pais = mapearPais(rs);
                paises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }

    public List<String> obtenerContinentes() {
        List<String> continentes = new ArrayList<>();
        String sql = "SELECT DISTINCT continent FROM country ORDER BY continent";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                continentes.add(rs.getString("continent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continentes;
    }

    public Pais obtenerPaisPorCodigo(String codigo) {
        String sql = "SELECT * FROM country WHERE Code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearPais(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pais mapearPais(ResultSet rs) throws SQLException {
        Pais pais = new Pais();
        pais.setCode(rs.getString("Code"));
        pais.setName(rs.getString("Name"));
        pais.setContinent(rs.getString("Continent"));
        pais.setRegion(rs.getString("Region"));
        pais.setSurfaceArea(rs.getDouble("SurfaceArea"));

        int indepYear = rs.getInt("IndepYear");
        pais.setIndepYear(rs.wasNull() ? null : indepYear);

        pais.setPopulation(rs.getInt("Population"));
        pais.setGovernmentForm(rs.getString("GovernmentForm"));
        pais.setHeadOfState(rs.getString("HeadOfState"));

        double lifeExpectancy = rs.getDouble("LifeExpectancy");
        pais.setLifeExpectancy(rs.wasNull() ? null : lifeExpectancy);

        return pais;
    }
}

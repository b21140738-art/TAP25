package clases.practica_login.model.dao;

import clases.practica_login.model.Usuario;
import clases.practica_login.model.factory.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class UsuarioDAO implements IUsuarioDAO {

    private final DatabaseConnection databaseConnection;

    public UsuarioDAO() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, correo, password_hash, nombre_completo, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getPasswordHash());
            stmt.setString(4, usuario.getNombreCompleto());
            stmt.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existeUsername(String username) {
        return buscarPorUsername(username).isPresent();
    }

    @Override
    public boolean existeCorreo(String correo) {
        return buscarPorCorreo(correo).isPresent();
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPasswordHash(rs.getString("password_hash"));
        usuario.setNombreCompleto(rs.getString("nombre_completo"));

        Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
        if (fechaRegistro != null) {
            usuario.setFechaRegistro(fechaRegistro.toLocalDateTime());
        }

        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        if (fechaNacimiento != null) {
            usuario.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }

        return usuario;
    }
}
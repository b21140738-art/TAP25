package clases.DAO2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para la entidad Usuario
 * con patrón Singleton.
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    // Instancia única (Singleton)
    private static UsuarioDAO instance;

    // Constructor privado para evitar instanciación directa
    private UsuarioDAO(Connection connection) {
        super(connection);
    }

    /**
     * Método estático para obtener la instancia única del DAO
     */
    public static synchronized UsuarioDAO getInstance() throws SQLException {
        if (instance == null) {
            // Usa el Singleton de DatabaseConnection
            Connection conn = DatabaseConnection.getInstance().getConnection();
            instance = new UsuarioDAO(conn);
        }
        return instance;
    }

    @Override
    public boolean save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, password, fecha_registro) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());
            stmt.setDate(4, Date.valueOf(usuario.getFechaRegistro()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getLong(1));
                } else {
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, fecha_registro = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());
            stmt.setDate(4, Date.valueOf(usuario.getFechaRegistro()));
            stmt.setLong(5, usuario.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario findById(Long id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar usuario por ID: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener todos los usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    @Override
    protected Usuario mapResultSetToEntity(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPassword(rs.getString("password"));
        usuario.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        return usuario;
    }

    /**
     * Busca usuarios por nombre (parcial o completo)
     */
    public List<Usuario> findByNombre(String nombre) {
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios por nombre: " + e.getMessage());
        }

        return usuarios;
    }
}

package clases.DAO2;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class View2 {

    public static void main(String[] args) {
        try {
            // Obtener la conexión del Singleton
            DatabaseConnection dbInstance = DatabaseConnection.getInstance();
            Connection connection = dbInstance.getConnection();

            // Crear instancia del DAO de Usuario
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Claudia Pizano");
            nuevoUsuario.setEmail("ClaudiaPino@example.com");
            nuevoUsuario.setPassword("contraseña123");
            nuevoUsuario.setFechaRegistro(LocalDate.now());

            // Guardar el usuario
            if (usuarioDAO.save(nuevoUsuario)) {
                System.out.println("Usuario guardado con éxito. ID: " + nuevoUsuario.getId());
            }

            // Mostrar todos los usuarios
            List<Usuario> usuarios = usuarioDAO.findAll();
            usuarios.forEach(System.out::println);

            // Cerrar la conexión del Singleton (opcional)
            dbInstance.closeConnection();

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}

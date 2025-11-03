package clases.practicaequipo5.controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;


    private static final String URL = "jdbc:mysql://localhost:3306/chat_cliente_servidor";
    private static final String USER = "root";
    private static final String PASSWORD = "Klaufer17";

    private DatabaseConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            crearTablaSiNoExiste();
            System.out.println("Conexión a MySQL establecida correctamente");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error inicializando la base de datos MySQL: " + e.getMessage());
            throw new RuntimeException("Error inicializando la base de datos MySQL", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo conexión MySQL", e);
        }
        return this.connection;
    }

    private void crearTablaSiNoExiste() {
        String sql = """
            CREATE TABLE IF NOT EXISTS mensajes (
                id INT PRIMARY KEY AUTO_INCREMENT,
                contenido TEXT NOT NULL,
                tipo ENUM('CLIENTE', 'SERVIDOR') NOT NULL,
                modo ENUM('NORMAL', 'MAYUS', 'MINUS') NOT NULL,
                direccion_ip VARCHAR(45),
                fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'mensajes' verificada/creada correctamente en MySQL");
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla en MySQL", e);
        }
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error cerrando conexión MySQL: " + e.getMessage());
        }
    }
}

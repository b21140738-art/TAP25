package clases.practica_login.model.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * Clase que maneja la conexión a la base de datos usando los patrones
 * Singleton y Factory para asegurar una única instancia de conexión
 * y centralizar la creación de conexiones.
 */
public class DatabaseConnection {

    // Instancia única (Singleton)
    private static DatabaseConnection instance;

    // Conexión a la base de datos
    private Connection connection;

    // Configuración de la base de datos
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final String DATABASE_NAME;
    private final String HOST;
    private final int PORT;

    // Configuración por defecto
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 3306;
    private static final String DEFAULT_DATABASE = "login_app";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "Klaufer17";

    /*
     * Constructor privado para evitar instanciación directa (Singleton)
     * Usa configuración por defecto
     */
    private DatabaseConnection() {
        this(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE, DEFAULT_USER, DEFAULT_PASSWORD);
    }

    /**
     * Constructor privado con configuración personalizada
     */
    private DatabaseConnection(String host, int port, String database, String user, String password) {
        this.HOST = host;
        this.PORT = port;
        this.DATABASE_NAME = database;
        this.USER = user;
        this.PASSWORD = password;
        this.URL = String.format("jdbc:mysql://%s:%d/%s", host, port, database);

        initializeConnection();
    }

    /*
     * Método estático para obtener la instancia única (Singleton)
     * Usa configuración por defecto
     */
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

    /*
     * Método estático para obtener instancia con configuración personalizada
     */
    public static DatabaseConnection getInstance(String host, int port, String database, String user, String password) {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection(host, port, database, user, password);
                }
            }
        }
        return instance;
    }

    /*
     * Inicializa la conexión a la base de datos
     */
    private void initializeConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver de MySQL cargado correctamente");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: No se pudo cargar el driver de MySQL", e);
        }
    }

    //Obtiene una conexión a la base de datos (Factory)

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties connectionProps = new Properties();
                connectionProps.setProperty("user", USER);
                connectionProps.setProperty("password", PASSWORD);
                connectionProps.setProperty("useSSL", "false");
                connectionProps.setProperty("serverTimezone", "UTC");
                connectionProps.setProperty("allowPublicKeyRetrieval", "true");
                connectionProps.setProperty("useUnicode", "true");
                connectionProps.setProperty("characterEncoding", "UTF-8");

                connection = DriverManager.getConnection(URL, connectionProps);
                System.out.println("Conexión establecida con: " + DATABASE_NAME);

            } catch (SQLException e) {
                System.err.println("Error al conectar con la base de datos: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    //Cierra la conexión actual

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }

    /*
     * Verifica si la conexión está activa
     */
    public boolean isConnectionActive() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }

    /*
     * Ejecuta una prueba de conexión
     */
    public boolean testConnection() {
        try (Connection testConn = getConnection()) {
            return testConn != null && !testConn.isClosed();
        } catch (SQLException e) {
            System.err.println("Error en prueba de conexión: " + e.getMessage());
            return false;
        }
    }

    /*
     * Reinicia la conexión (útil para recuperarse de errores)
     */
    public void resetConnection() {
        closeConnection();
        try {
            getConnection();
        } catch (SQLException e) {
            System.err.println("Error al resetear conexión: " + e.getMessage());
        }
    }

    // Getters para la configuración
    public String getUrl() { return URL; }
    public String getUser() { return USER; }
    public String getDatabaseName() { return DATABASE_NAME; }
    public String getHost() { return HOST; }
    public int getPort() { return PORT; }

    // Método para mostrar información de la conexión

    public void printConnectionInfo() {
        System.out.println("=== Información de Conexión ===");
        System.out.println("Host: " + HOST);
        System.out.println("Puerto: " + PORT);
        System.out.println("Base de datos: " + DATABASE_NAME);
        System.out.println("Usuario: " + USER);
        System.out.println("URL: " + URL);
        System.out.println("Estado: " + (isConnectionActive() ? "ACTIVA" : "INACTIVA"));
        System.out.println("===============================");
    }
}
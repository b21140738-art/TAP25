package clases;

public class Configuracion {
    private static Configuracion instancia;
    private String baseDatosUrl;
    private String usuario;
    private String password;

    //Constructor privado para evitar instanción externa
    private Configuracion(){
        this.baseDatosUrl = "jdbc:mysql://localhost:3306/escuela";
        this.usuario = "root";
        this.password = "12345";
    }

    //metodo estatico para obtener la unica instancia
    public static Configuracion getInstancia(){
        if (instancia == null){
            instancia = new Configuracion();
        }
        return instancia;
    }

    //metodos para obtener y modificar la configuracion

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setBaseDatosUrl(String baseDatosUrl) {
        this.baseDatosUrl = baseDatosUrl;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getBaseDatosUrl() {
        return baseDatosUrl;
    }

    public String getPassword() {
        return password;
    }

    //metodos para mostrar la configuracion actual
    public void mostrarConfiguracion(){
        System.out.println("Base de datos URL: " + baseDatosUrl);
        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);
    }
}

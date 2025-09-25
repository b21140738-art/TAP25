package tap.Model;

/**
 * Representa un cliente en el sistema.
 */
public class ClienteModel {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private double saldo;

    /**
     * Constructor de ClienteModel.
     *
     * @param id        El ID del cliente.
     * @param nombre    Nombre del cliente.
     * @param email     Correo electrónico del cliente.
     * @param telefono  Teléfono del cliente.
     * @param saldo     Saldo asociado al cliente.
     */
    public ClienteModel(int id, String nombre, String email, String telefono, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    //metodo que devuelve los beneficios del cliente.
    public String getBeneficios(){
        return "Sin beneficios especiales";
        //este metodo puede ser modificado por las subclases para personalizar en cada caso

    }
}


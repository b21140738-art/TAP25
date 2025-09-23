package tap.Factory;


import tap.Model.ClienteModel;

/**
 * Fábrica para la creación de instancias de ClienteModel.
 */
public class ClienteFactory {

    /**
     * Crea un nuevo cliente con los parámetros indicados.
     *
     * @param id        ID del cliente.
     * @param nombre    Nombre del cliente.
     * @param email     Email del cliente.
     * @param telefono  Teléfono del cliente.
     * @param saldo     Saldo del cliente.
     * @return Una nueva instancia de ClienteModel.
     */
    public static ClienteModel crearCliente(int id, String nombre, String email, String telefono, double saldo) {
        return new ClienteModel(id, nombre, email, telefono, saldo);
    }
}


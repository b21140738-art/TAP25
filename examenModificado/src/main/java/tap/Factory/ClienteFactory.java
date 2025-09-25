package tap.Factory;


import tap.Model.ClienteFrecuente;
import tap.Model.ClienteModel;
import tap.Model.ClienteVip;

/**
 * factory para la creación de instancias de ClienteModel.
 */
public class ClienteFactory {


    public static ClienteModel crearCliente(String tipo, int id, String nombre, String email, String telefono, double saldo) {
        if(tipo.equalsIgnoreCase("Frecuente")){
            return new ClienteFrecuente(id, nombre, email, telefono, saldo);
        }
        else if (tipo.equalsIgnoreCase("Vip")){
            return new ClienteVip(id, nombre, email, telefono, saldo);
        }
        return new ClienteModel(id, nombre, email, telefono, saldo);

    }
}


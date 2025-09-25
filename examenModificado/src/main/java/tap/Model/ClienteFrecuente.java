package tap.Model;

public class ClienteFrecuente extends ClienteModel{
    public ClienteFrecuente(int id, String nombre, String email, String telefono, double saldo){
        super(id, nombre, email, telefono, saldo);


    }

    public String getBeneficios(){
        return "FRECUENTE: Acumula puntos por cada compra realizada. Usables en proximas compras";
    }
}

package tap.Model;

public class ClienteVip extends ClienteModel{

    public ClienteVip(int id, String nombre, String email, String telefono, double saldo){
        super(id, nombre, email, telefono, saldo); //super sirve para llamar al constructur que existe en clase Cliente Model


    }

    public String getBeneficios(){
        return "VIP: Acceso a atención prioritaria";
    }
}

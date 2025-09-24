package clases;
import javax.swing.JApplet;
//Producto: HawaiPizzaBuilder
public class HawaiPizzaBuilder extends PizzaBuilder{

        public void  buildMasa(){
        pizza.setMasa("Suave");
    }

    public void buildSalsa() {
        pizza.setSalsa("Acida");
        }

    public void buildRelleno() {
pizza.setRelleno("chorizo+chiles");
    }
}


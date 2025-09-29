package clases;

//Producto
public class PicantePizzaBuilder extends PizzaBuilder {


    public void buildMasa() {
        pizza.setMasa("Cruch");
    }


    public void buildSalsa() {
        pizza.setSalsa("spicy");

    }


    public void buildRelleno() {
        pizza.setRelleno("chilesjalapeños+jamon+pollo+salsamaggi");

    }
}

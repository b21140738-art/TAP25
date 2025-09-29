package clases;
/// 1. el cliente crea el objeto Director y lo configura con el objeto builder deseado
/// 2. el director notifica al constructor cuando una parte del producto se debe construir
/// 3. el builder maneja los requerimientos desde el director y agrega partes al producto
/// 4. el cliente recupera el producto desde el constructor
//director:Cocina


public class Cocina {
    private PizzaBuilder pizzaBuilder;


    public void setPizzaBuilder(PizzaBuilder pb) {pizzaBuilder = pb;}
    public Pizza getPizza() {
        return pizzaBuilder.getPizza();
    }

public void construirPizza(){
     pizzaBuilder.crearNuevaPizza();
     pizzaBuilder.buildMasa();
     pizzaBuilder.buildSalsa();
     pizzaBuilder.buildRelleno();

}
}

import Models.Bebida;
import Models.Cafe;
import Models.Decoradores.Azucar;
import Models.Decoradores.Chocolate;
import Models.Decoradores.Crema;
import Models.Decoradores.Leche;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CAFETERÍA - PATRÓN DECORATOR ===\n");

        // Ejemplo 1: Café simple
        Bebida bebida1 = new Cafe();
        System.out.println("1. " + bebida1.getDescripcion());
        System.out.println("   Costo: $" + bebida1.getCosto());
        System.out.println();

        // Ejemplo 2: Café con leche
        Bebida bebida2 = new Leche(new Cafe());
        System.out.println("2. " + bebida2.getDescripcion());
        System.out.println("   Costo: $" + bebida2.getCosto());
        System.out.println();

        // Ejemplo 3: Café con leche y chocolate
        Bebida bebida3 = new Chocolate(new Leche(new Cafe()));
        System.out.println("3. " + bebida3.getDescripcion());
        System.out.println("   Costo: $" + bebida3.getCosto());
        System.out.println();

        // Ejemplo 4: Café con leche, chocolate y crema
        Bebida bebida4 = new Crema(new Chocolate(new Leche(new Cafe())));
        System.out.println("4. " + bebida4.getDescripcion());
        System.out.println("   Costo: $" + bebida4.getCosto());
        System.out.println();

        // Ejemplo 5: Café con doble chocolate (podemos repetir decoradores)
        Bebida bebida5 = new Chocolate(new Chocolate(new Cafe()));
        System.out.println("5. " + bebida5.getDescripcion());
        System.out.println("   Costo: $" + bebida5.getCosto());
        System.out.println();

        // Ejemplo 6: Café con crema y chocolate (orden diferente)
        Bebida bebida6 = new Chocolate(new Crema(new Cafe()));
        System.out.println("6. " + bebida6.getDescripcion());
        System.out.println("   Costo: $" + bebida6.getCosto());
        System.out.println();


        // Ejemplo 7: Café con azúcar
        Bebida bebida7 = new Azucar(new Cafe());
        System.out.println("7. " + bebida7.getDescripcion());
        System.out.println("   Costo: $" + bebida7.getCosto());
        System.out.println();

        // Ejemplo 8: Café con leche y azúcar
        Bebida bebida8 = new Azucar(new Leche(new Cafe()));
        System.out.println("8. " + bebida8.getDescripcion());
        System.out.println("   Costo: $" + bebida8.getCosto());
        System.out.println();

        // Ejemplo 9: Café completo con azúcar
        Bebida bebida9 = new Azucar(new Crema(new Chocolate(new Leche(new Cafe()))));
        System.out.println("9. " + bebida9.getDescripcion());
        System.out.println("   Costo: $" + bebida9.getCosto());
        System.out.println();

        // Ejemplo 10: Café con doble azúcar
        Bebida bebida10 = new Azucar(new Azucar(new Cafe()));
        System.out.println("10. " + bebida10.getDescripcion());
        System.out.println("    Costo: $" + bebida10.getCosto());
        System.out.println();


        System.out.println("=== ¡Gracias por usar nuestro sistema! ===");
    }
}
package clases;

import clases.Models.*;
import clases.Models.Decoradores.chile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PIZZERIA - PATRÓN DECORATOR ===\n");

        // Ejemplo 1: PIZZA BASICA
        PIZZA pizza1 = new Pizzabasica();
        System.out.println("1. " + pizza1.getDescripcion());
        System.out.println("   Costo: $" + pizza1.getCosto());
        System.out.println();


        // Ejemplo 2: PIZZA Gourmet
        PIZZA pizza2 = new PizzaGourmet();
        System.out.println("2. " + pizza2.getDescripcion());
        System.out.println("   Costo: $" + pizza2.getCosto());
        System.out.println();

        // Ejemplo 3: PIZZA BASICA
        PIZZA pizza3= new chile(new Pizzabasica());
        System.out.println("3. " + pizza3.getDescripcion());
        System.out.println("   Costo: $" + pizza3.getCosto());
        System.out.println();





        System.out.println("=== ¡Gracias por usar nuestro sistema! ===");
    }
}
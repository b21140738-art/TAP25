/*
*EJEMPLO COMENTARIO DE GENERICS EN JAVA
* GENERICOS EN CLASES
*
 */

/// Aqui estamos indicando que el tipo de datos V es generico
///
    public class Persona <V> {
    private String nombre;
    private String apellidos;
    private V sueldo; //sera de tipo v, lo que permitira que admita integer y double

    public Persona(String nombre, String apellidos, V sueldo) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sueldo = sueldo;


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public V getSueldo() {
        return sueldo;
    }

    public void setSueldo(V sueldo) {
        this.sueldo = sueldo;
    }

    public static void main(String[] args) {
    //Instanciamos una clase Persona con la edad en Integer
    Persona<Integer> persona1 = new Persona<Integer>("claudia", "Pizano", 32000);
    //Instanciamos una clase Persona con la edad en Double
    Persona<Double> persona2 = new Persona<Double>("alejandro", "pizano", 17000.3);
    System.out.println(persona1.getNombre()+" "+ persona1.getApellidos()+" tiene un sueldo de: " + persona1.getSueldo() + " pesos mexicanos");
    System.out.println("El atributo sueldo de persona 1 es: " + persona1.getSueldo().getClass().getName());
    System.out.println(persona2.getNombre()+" "+ persona2.getApellidos()+" tiene un sueldo de: " + persona2.getSueldo() + " pesos mexicanos");
    System.out.println("El atributo sueldo de persona 2 es: " + persona2.getSueldo().getClass().getName());
}

}



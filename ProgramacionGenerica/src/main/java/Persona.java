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
    private V edad; //sera de tipo v, lo que permitira que admita integer y double

    public Persona(String nombre, String apellidos, V edad) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
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

    public V getEdad() {
        return edad;
    }

    public void setEdad(V edad) {
        this.edad = edad;
    }

    //Instanciamos una clase Persona con la edad en Integer
    Persona<Integer> persona1 = new Persona<Integer>("claudia", "Pizano", 32);
    //Instanciamos una clase Persona con la edad en Double
    Persona<Double> persona2 = new Persona<Double>("alejandro", "pizano", 17.3);

System.out.println("La persona 1 tiene: " + persona1.getEdad() + " años");
System.out.println("El atributo edad de persona 1 es: " + persona1.getEdad().getClass().getName());
System.out.println("La persona 2 tiene: " + persona2.getEdad() + " años");
System.out.println("El atributo edad de persona 2 es: " + persona2.getEdad().getClass().getName());

}



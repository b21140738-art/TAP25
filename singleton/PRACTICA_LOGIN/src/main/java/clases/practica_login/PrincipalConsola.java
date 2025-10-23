package clases.practica_login;


import clases.practica_login.view.AplicacionConsola;

public class PrincipalConsola {
    public static void main(String[] args) {
        // Punto de entrada SOLO para consola
        System.out.println("INICIANDO MODO CONSOLA");
        System.out.println("===================================");

        try {
            AplicacionConsola aplicacionConsola = new AplicacionConsola();
            aplicacionConsola.iniciar();
        } catch (Exception e) {
            System.err.println("Error fatal en consola: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

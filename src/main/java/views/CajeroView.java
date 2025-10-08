package views;

import models.cuenta;

import java.util.Scanner;

public class CajeroView {

    private Scanner scanner;
    public CajeroView() {
        scanner = new Scanner(System.in);

    }

    class Imprimir{
        public static <T> void imprimir(T elemento){
            System.out.print(elemento);
        }
    }

    public void mostrarBienvenida() {
        Imprimir.imprimir("\n=================================================");
        Imprimir.imprimir("Bienvenido al cajero automatico del banco BBVA");
        Imprimir.imprimir("================================================");

    }

    public String solicitarnumeroCuenta(){
        Imprimir.imprimir("\nIngresa tu numero de cuenta: ");
        return scanner.nextLine();
    }
    public String solicitarPin(){
        Imprimir.imprimir("Ingresa una pin: ");
        return scanner.nextLine();
    }

    public void mostrarMenuPrincipal(String Titular, String tipoCuenta) {
        Imprimir.imprimir("\n=======================================");
        Imprimir.imprimir("Bienvenid@ "+Titular );
        Imprimir.imprimir("=========================================\n");
        System.out.println("Tipo de cuenta creada: " + tipoCuenta);
        Imprimir.imprimir("\nIngresa una opcion: ");
        Imprimir.imprimir("\n1. Consultar saldo");
        Imprimir.imprimir("\n2. Retirar");
        Imprimir.imprimir("\n3. Depositar");
        Imprimir.imprimir("\n4. Transferir");
        Imprimir.imprimir("\n5. Cambiar pin");
        Imprimir.imprimir("\n9. Salir\n");
        //definir los demas metodos de tarea
    }

    public void mostrarMenuPrincipal(String Titular, String tipoCuenta, String nipHasheado) {
        Imprimir.imprimir("\n=======================================");
        Imprimir.imprimir("Bienvenid@ " + Titular);
        Imprimir.imprimir("=========================================\n");
        System.out.println("NIP (SHA-256): " + nipHasheado);
        System.out.println("Tipo de cuenta creada: " + tipoCuenta);
        Imprimir.imprimir("\nIngresa una opcion: ");
        Imprimir.imprimir("\n1. Consultar saldo");
        Imprimir.imprimir("\n2. Retirar");
        Imprimir.imprimir("\n3. Depositar");
        Imprimir.imprimir("\n4. Transferir");
        Imprimir.imprimir("\n5. Cambiar pin");
        Imprimir.imprimir("\n9. Salir\n");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarSaldo(double saldo) {
        Imprimir.imprimir("============================================");
        Imprimir.imprimir("Tu saldo actual es: "+saldo);
        Imprimir.imprimir("============================================");
    }

    public double solicitarCantidad(String operacion){
        Imprimir.imprimir("Ingresa la cantidad a " + operacion+":");
        try{
            return Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }
    public void mostrarMensaje(String mensaje){
        Imprimir.imprimir("==="+mensaje);
    }

    public void mostrarError(String mensaje){
        Imprimir.imprimir("===========================================");
        Imprimir.imprimir("Error: "+mensaje);

    }


    public void cerrarScanner(){
        scanner.close();
    }

    // tarea personalizar mensaje de error y exito
//tarea metodo para salir cerra el scanner

}

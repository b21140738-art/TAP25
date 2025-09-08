package views;

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
        Imprimir.imprimir("=================================================");
        Imprimir.imprimir("Bienvenido al cajero automatico del banco BBVA");
        Imprimir.imprimir("================================================");

    }

    public String solicitarnumeroCuenta(){
        Imprimir.imprimir("Ingresa tu numero de cuenta: ");
        return scanner.nextLine();
    }
    public String solicitarPin(){
        Imprimir.imprimir("Ingresa una pin: ");
        return scanner.nextLine();
    }

    public void mostrarMenuPrincipal(String Titular) {
        Imprimir.imprimir("=======================================");
        Imprimir.imprimir("Bienvenid@"+Titular);
        Imprimir.imprimir("=========================================");
        Imprimir.imprimir("1. Consultar saldo");
        Imprimir.imprimir("2. Retirar");
        Imprimir.imprimir("3. Depositar");
        Imprimir.imprimir("4. Transferir");
        Imprimir.imprimir("5. Cambiar pin");
        Imprimir.imprimir("9. Salir");
        //definir los demas metodos de tarea
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

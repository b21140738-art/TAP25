package views;

import java.util.Scanner;

public class CajeroView {

    private Scanner scanner;
    public CajeroView() {
        scanner = new Scanner(System.in);

    }
    public void mostrarBienvenida() {
        System.out.println("=================================================");
        System.out.println("Bienvenido al cajero automatico del banco BBVA");
        System.out.println("================================================");

    }

    public String solicitarnumeroCuenta(){
        System.out.println("Ingresa tu numero de cuenta: ");
        return scanner.nextLine();
    }
    public String solicitarPin(){
        System.out.println("Ingresa una pin: ");
        return scanner.nextLine();
    }

    public void mostrarMenuPrincipal(String Titular) {
        System.out.println("=======================================");
        System.out.println("Bienvenid@"+Titular);
        System.out.println("=========================================");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar");
        System.out.println("3. Depositar");
        System.out.println("4. Transferir");
        System.out.println("5. Cambiar pin");
        System.out.println("9. Salir");
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
        System.out.println("============================================");
        System.out.println("Tu saldo actual es: "+saldo);
        System.out.println("============================================");
    }

    public double solicitarCantidad(String operacion){
        System.out.println("Ingresa la cantidad a " + operacion+":");
        try{
            return Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }
    public void mostrarMensaje(String mensaje){
        System.out.println("==="+mensaje);
    }

    public void mostrarError(String mensaje){
        System.out.println("===========================================");
        System.out.println("Error: "+mensaje);

    }

    public void salir

    // tarea personalizar mensaje de error y exito
//tarea metodo para salir cerra el scanner

}

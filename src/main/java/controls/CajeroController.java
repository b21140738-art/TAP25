package controls;

import models.CajeroModel;
import views.CajeroView;

public class CajeroController {
    private CajeroModel model;
    private CajeroView view;

    private boolean sistemaActivo;

    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    public void iniciarSistema() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if(autenticarUsuario()){
                ejecutarMenuPrincipal();
            }
            else{
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }
    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarnumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    private void ejecutarMenuPrincipal() {
        boolean seccionActiva = true;
        while (seccionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarRetiro();
                        break;
                case 3:
                    realizarDeposito();
                    break;
                case 9:

                    break;
                default:
                    break;
            }
        }
    }

    public void consultarSaldo() {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

    public void realizarRetiro() {
        double cantidad = view.solicitarCantidad("retirar");
        if (cantidad <= 0) {
             view.mostrarMensaje("Saldo insuficiente");
             return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de: " + cantidad);
        }
        else {
            view.mostrarMensaje("Saldo insuficiente");
        }
        }

        public void realizarDeposito() {
        double cantidad = view.solicitarCantidad("Deposito");
        if (cantidad <= 0) {
            view.mostrarMensaje(")Error en la cantidad");
            return;
        }
        if(model.realizarDeposito(cantidad)){
            view.mostrarMensaje("Deposito exitoso de"+cantidad);
        }
        else {
            view.mostrarMensaje("Saldo insuficiente");
        }
    }


}

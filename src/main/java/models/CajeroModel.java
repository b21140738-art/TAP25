package models;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String,cuenta> cuentas;
    private cuenta cuentaActual;
    private cuenta numeroCuentaDestino;
    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarcuentas();
    }

    private void inicializarcuentas() {
        cuentas.put("12345", new cuenta.Builder().setNumeroCuenta("12345").setPin("1111").setSaldo(5000).setTitular("Juan Perez").setTipoCuenta("ahorro").build());
        cuentas.put("67890",new cuenta.Builder().setNumeroCuenta("67890").setPin("2222").setSaldo(200).setTitular("Diana Olvera").setTipoCuenta("Corriente/normal").build());
        cuentas.put("12134",new cuenta.Builder().setNumeroCuenta("12134").setPin("3333").setSaldo(300000).setTitular("Elena Villa").setTipoCuenta("INVERSION").build());
        cuentas.put("21234", new cuenta.Builder().setNumeroCuenta("21234").setPin("4444").setSaldo(1000000).setTitular("Catalina Villa").build());
    }
    public boolean autenticar(String numeroCuenta, String pin) {
        cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarpin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }
    public cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    public double consultarSaldo() {
        return this.cuentaActual != null ? this.cuentaActual.getSaldo() : 0;
    }

    public boolean realizarRetiro(double cantidad) {
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }

    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    public boolean cuentaExiste(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);

    }

    //definir el metodo para transferir
    public boolean realizarTransferencia(double cantidad) {

        if (cuentaActual == null) {
            return false;
        }

        if (cantidad <= 0) {
            return false;
        }

        if (numeroCuentaDestino.equals(cuentaActual.getNumeroCuenta())) {
            return false;
        }

        cuenta cuentaDestino = cuentas.get(numeroCuentaDestino);
        if (cuentaDestino == null) {
            return false;
        }
        return cuentaActual.transferir(cuentaDestino, cantidad);
    }

/// Cambia NIP
///

    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (cuentaActual == null) {
            return false;
        }

        return cuentaActual.cambiarNip(pinActual, nuevoPin);
    }
}

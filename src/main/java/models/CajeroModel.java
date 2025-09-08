package models;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String,cuenta> cuentas;
    private cuenta cuentaActual;
    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarcuentas();
    }

    private void inicializarcuentas() {
        cuentas.put("12345", new cuenta("12345",
                "1111",5000,"Juan Perez"));
        cuentas.put("67890",new cuenta("67890",
                "2222",200,"Diana Olvera"));
        cuentas.put("12134", new cuenta("12134",
                "3333",300000,"Elena Villa"));
        cuentas.put("21234", new cuenta("21234",
                "4444",1000000,"Catalina Villa"));
    }
    public boolean autenticar(String numeroCuenta, String pin) {
        cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarpin(pin)) {
            this.cuentaActual = cuenta;
            return false;
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
    public boolean realizarTransferencia(String numeroCuentaDestino, double cantidad) {

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

package models;

public class cuenta{
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String Titular;

    public cuenta(String numeroCuenta, String pin, double saldoIncial, String Titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldoIncial;
        this.Titular = Titular;

    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return Titular;
    }

    //Reglas de negocio
         //validacion de pin ingresado desde consola
    public boolean validarpin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }
        //proceso de validacion para retiro
    public boolean retirar(double cantidad){
        if(cantidad>0 && cantidad <= this.saldo){
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public void depositar(double cantidad){
        if(cantidad>0){
            saldo += cantidad;
        }
    }

    //TAREA: DISEÑAR los COMPORTAMIENTO DEL CAJERO restantes

    public boolean transferir(cuenta cuentaDestino, double cantidad) {
        if (cantidad <= 0) {
            return false;
        }

        if (this.saldo < cantidad) {
            return false;
        }

        if (cuentaDestino == null) {
            return false;
        }

        if (this.retirar(cantidad)) {
            cuentaDestino.depositar(cantidad);
            return true;
        }

        return false;
    }
    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (!validarpin(pinActual)) {
            return false;
        }

        if (nuevoPin == null || nuevoPin.trim().isEmpty()) {
            return false;
        }

        if (!nuevoPin.matches("\\d{4}")) {
            return false;
        }

        this.pin = nuevoPin;
        return true;
    }
}

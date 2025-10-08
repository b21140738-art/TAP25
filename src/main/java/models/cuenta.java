package models;

public class cuenta{
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String Titular;
    private String tipoCuenta;

 private cuenta(Builder builder){
     this.numeroCuenta = builder.numerocuenta;
     this.pin = controls.Hash.generarHash(builder.pin);
     this.saldo = builder.saldo;
     this.Titular = builder.titular;
     this.tipoCuenta = builder.tipoCuenta;//AHORRO, INVERSION, CORRIENTE
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

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    //Reglas de negocio
         //validacion de pin ingresado desde consola
    public boolean validarpin(String pinIngresado){
        if (pinIngresado == null) {
            return false;
        }
        String hashIngresado = controls.Hash.generarHash(pinIngresado);
        return this.pin.equals(hashIngresado);
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
        if(tipoCuenta.equalsIgnoreCase("Ahorro") && cantidad >10000){
            return false; //limite de tranferencia
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

        this.pin = controls.Hash.generarHash(nuevoPin);
        return true;
    }

    //clase builder
    public static class Builder{
     private String numerocuenta;
     private String pin;
     private double saldo;
     private String titular;
     private String tipoCuenta="Corriente/normal";

     public Builder setNumeroCuenta(String numeroCuenta){
        this.numerocuenta = numeroCuenta;
        return this;
     }

     public Builder setPin(String pin){
         this.pin = pin;
         return this;
     }

     public Builder setSaldo(double saldo){
         this.saldo = saldo;
         return this;

     }
     public Builder setTitular(String titular){
         this.titular = titular;
         return this;
     }

        public Builder setTipoCuenta(String tipoCuenta) {
            this.tipoCuenta = tipoCuenta;
            return this;
        }

        public cuenta build(){
         return new cuenta(this);
     }
    }
}

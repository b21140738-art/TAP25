package com.mycompany.moneda;

public class Cajero {
    private int cantidad; // en euros
    private final double tipoCambio = 22; // 1 euro = 22 pesos mexicanos

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void mostrarCambioEuros() {
        int cantidadR = cantidad;
        int b1000 = 0, b500 = 0, b200 = 0, b100 = 0, b50 = 0, b20 = 0;
        int mon10 = 0, mon5 = 0, mon2 = 0, mon1 = 0;

        if (cantidad == 0) {
            System.out.println("Terminó el programa");
            return;
        }

        b1000 = cantidadR / 1000; cantidadR %= 1000;
        b500  = cantidadR / 500;  cantidadR %= 500;
        b200  = cantidadR / 200;  cantidadR %= 200;
        b100  = cantidadR / 100;  cantidadR %= 100;
        b50   = cantidadR / 50;   cantidadR %= 50;
        b20   = cantidadR / 20;   cantidadR %= 20;
        mon10 = cantidadR / 10;   cantidadR %= 10;
        mon5  = cantidadR / 5;    cantidadR %= 5;
        mon2  = cantidadR / 2;    cantidadR %= 2;
        mon1  = cantidadR / 1;    cantidadR %= 1;

       
        if (b1000 > 0) System.out.println("Billetes de 1000: " + b1000);
        if (b500 > 0)  System.out.println("Billetes de 500: " + b500);
        if (b200 > 0)  System.out.println("Billetes de 200: " + b200);
        if (b100 > 0)  System.out.println("Billetes de 100: " + b100);
        if (b50 > 0)   System.out.println("Billetes de 50: " + b50);
        if (b20 > 0)   System.out.println("Billetes de 20: " + b20);
        if (mon10 > 0) System.out.println("Monedas de 10: " + mon10);
        if (mon5 > 0)  System.out.println("Monedas de 5: " + mon5);
        if (mon2 > 0)  System.out.println("Monedas de 2: " + mon2);
        if (mon1 > 0)  System.out.println("Monedas de 1: " + mon1);
    }

    public void mostrarCambioPesos() {
        int cantidadPesos = (int) Math.round(cantidad * tipoCambio);
        int cantidadR = cantidadPesos;
        int b1000 = 0, b500 = 0, b200 = 0, b100 = 0, b50 = 0, b20 = 0;
        int mon10 = 0, mon5 = 0, mon2 = 0, mon1 = 0;

        b1000 = cantidadR / 1000; cantidadR %= 1000;
        b500  = cantidadR / 500;  cantidadR %= 500;
        b200  = cantidadR / 200;  cantidadR %= 200;
        b100  = cantidadR / 100;  cantidadR %= 100;
        b50   = cantidadR / 50;   cantidadR %= 50;
        b20   = cantidadR / 20;   cantidadR %= 20;
        mon10 = cantidadR / 10;   cantidadR %= 10;
        mon5  = cantidadR / 5;    cantidadR %= 5;
        mon2  = cantidadR / 2;    cantidadR %= 2;
        mon1  = cantidadR / 1;    cantidadR %= 1;

        System.out.println("Desglose en pesos mexicanos:");
        System.out.println(cantidad + " euros equivalen a " + cantidadPesos + " pesos mexicanos");
        if (b1000 > 0) System.out.println("Billetes de 1000: " + b1000);
        if (b500 > 0)  System.out.println("Billetes de 500: " + b500);
        if (b200 > 0)  System.out.println("Billetes de 200: " + b200);
        if (b100 > 0)  System.out.println("Billetes de 100: " + b100);
        if (b50 > 0)   System.out.println("Billetes de 50: " + b50);
        if (b20 > 0)   System.out.println("Billetes de 20: " + b20);
        if (mon10 > 0) System.out.println("Monedas de 10: " + mon10);
        if (mon5 > 0)  System.out.println("Monedas de 5: " + mon5);
        if (mon2 > 0)  System.out.println("Monedas de 2: " + mon2);
        if (mon1 > 0)  System.out.println("Monedas de 1: " + mon1);
    }
}
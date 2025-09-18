package tap.ejemplo2implementacion;

import java.awt.*;

public class FiguraFactory {
    public static Figura getFigura(String tipo){
       if(tipo.equalsIgnoreCase("Circulo")){
           return new Circulo();
       } else if(tipo.equalsIgnoreCase("Cuadrado")){
           return new Cuadrado();
       }
       return null;
    }
}

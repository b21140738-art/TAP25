package clases;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VerificadorEdadTest {

    @Test
    void esMayorDeEdad_Con18() {
        assertTrue(VerificadorEdad.esMayorDeEdad(18),"ES MAYOR DE EDAD");

    }

    @Test
    void esMayorDeEdad_Con35() {
        assertTrue(VerificadorEdad.esMayorDeEdad(35),"ES MAYOR DE EDAD");

    }
    @Test
    public void testEsMayorDeEdad_ConEdadMenor() {
        assertFalse(VerificadorEdad.esMayorDeEdad(17), "ES MENOR DE EDAD");
    }
    @Test
    public void testEsMayorDeEdad_DatosAleatorios() {
        Random rand = new Random();
        for (int i = 0; i < 150; i++) {
            int edad = rand.nextInt(100) - 10; // entre -50 y 149
            boolean esperado = edad >= 18;
            assertEquals(esperado, VerificadorEdad.esMayorDeEdad(edad), "Fallo en edad: " + edad);
        }
    }


}
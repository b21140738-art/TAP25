package clases;

import org.junit.jupiter.api.Test;

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



}
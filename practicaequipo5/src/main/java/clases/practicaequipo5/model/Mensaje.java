package clases.practicaequipo5.model;

public class Mensaje {
    private String contenido;
    private String tipo; // "CLIENTE" o "SERVIDOR"
    private String modo; // "MAYUS", "MINUS", "NORMAL"

    public Mensaje(String contenido, String tipo) {
        this.contenido = contenido;
        this.tipo = tipo;
        this.modo = "NORMAL";
    }

    public Mensaje(String contenido, String tipo, String modo) {
        this.contenido = contenido;
        this.tipo = tipo;
        this.modo = modo;
    }

    // Getters y Setters
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getModo() { return modo; }
    public void setModo(String modo) { this.modo = modo; }

    public String getContenidoTransformado() {
        switch (modo) {
            case "MAYUS":
                return contenido.toUpperCase();
            case "MINUS":
                return contenido.toLowerCase();
            case "NORMAL":
            default:
                return contenido;
        }
    }

    @Override
    public String toString() {
        return tipo + " [" + modo + "]: " + contenido;
    }
}
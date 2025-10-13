package clases.ejemplo1.decorators;
//Una interfaz común de datos que define operaciones de leer y escribir
public interface DataSource {
    void writeData(String data);

    String readData();
}

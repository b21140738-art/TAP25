package tap.models;

public class productosmodel {
    static String[] codigos = new String[100];
    static String[] nombres = new String[100];
    static double[] precios = new double[100];
    static int[] cantidades = new int[100];
    static String[] categorias = new String[100];
    static String[] fechasVencimiento = new String[100];
    static int totalProductos = 0;
    public productosmodel(String codigos,String nombre, double precios, int cantidades, String categorias, String fechasVencimiento) {
        this.codigos = new String[]{codigos};
        this.nombres = nombres;
        this.precios = new double[]{precios};
        this.cantidades = new int[cantidades];
        this.categorias = new String[]{categorias};
        this.fechasVencimiento = new String[]{fechasVencimiento};




    }

    public static String[] getCodigos() {
        return codigos;
    }

    public static String[] getNombres() {
        return nombres;
    }

    public static double[] getPrecios() {
        return precios;
    }

    public static int[] getCantidades() {
        return cantidades;
    }

    public static String[] getFechasVencimiento() {
        return fechasVencimiento;
    }

    public static String[] getCategorias() {
        return categorias;
    }

    public static int getTotalProductos() {
        return totalProductos;
    }

    public static void setCodigos(String[] codigos) {
        productosmodel.codigos = codigos;
    }

    public static void setNombres(String[] nombres) {
        productosmodel.nombres = nombres;
    }

    public static void setPrecios(double[] precios) {
        productosmodel.precios = precios;
    }

    public static void setCantidades(int[] cantidades) {
        productosmodel.cantidades = cantidades;
    }

    public static void setCategorias(String[] categorias) {
        productosmodel.categorias = categorias;
    }

    public static void setFechasVencimiento(String[] fechasVencimiento) {
        productosmodel.fechasVencimiento = fechasVencimiento;
    }

    public static void setTotalProductos(int totalProductos) {
        productosmodel.totalProductos = totalProductos;
    }
}

package tap.models;

public class ventasmodels {
    static int[] ventasProductos = new int[200];
    static int[] ventasCantidades = new int[200];
    static double[] ventasPrecios = new double[200];
    static String[] ventasFechas = new String[200];
    static int[] ventasClientes = new int[200];
    static int totalVentas = 0;

    public ventasmodels(int ventasProductos, int ventasCantidades, double ventasPrecio, int totalVentas) {
        this.totalVentas = totalVentas;
        this.ventasProductos = new int[]{ventasProductos};
        this.ventasCantidades = new int[]{ventasCantidades};
        this.ventasPrecios = new double[]{ventasPrecio};
        this.ventasFechas = new String[totalVentas];
        this.ventasClientes = new int[totalVentas];


    }

    public static int[] getVentasProductos() {
        return ventasProductos;
    }

    public static int[] getVentasCantidades() {
        return ventasCantidades;
    }

    public static double[] getVentasPrecios() {
        return ventasPrecios;
    }

    public static String[] getVentasFechas() {
        return ventasFechas;
    }

    public static int[] getVentasClientes() {
        return ventasClientes;
    }

    public static int getTotalVentas() {
        return totalVentas;
    }

    public static void setVentasProductos(int[] ventasProductos) {
        ventasmodels.ventasProductos = ventasProductos;
    }

    public static void setVentasCantidades(int[] ventasCantidades) {
        ventasmodels.ventasCantidades = ventasCantidades;
    }

    public static void setVentasPrecios(double[] ventasPrecios) {
        ventasmodels.ventasPrecios = ventasPrecios;
    }

    public static void setVentasFechas(String[] ventasFechas) {
        ventasmodels.ventasFechas = ventasFechas;
    }

    public static void setVentasClientes(int[] ventasClientes) {
        ventasmodels.ventasClientes = ventasClientes;
    }

    public static void setTotalVentas(int totalVentas) {
        ventasmodels.totalVentas = totalVentas;
    }
}

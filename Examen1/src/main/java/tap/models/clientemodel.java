package tap.models;

public class clientemodel {
     private String[] clientesNombres = new String[50];
    private String[] clientesEmails = new String[50];
    private String[] clientesTelefonos = new String[50];
    private double[] clientesSaldos = new double[50];
    private int totalClientes = 0;

    public clientemodel(String clientesNombres, String clientesEmails, String clientesTelefonos, double[] clientesSaldos) {
        this.clientesNombres = new String[]{clientesNombres};
        this.clientesEmails = new String[]{clientesEmails};
        this.clientesTelefonos = new String[]{clientesTelefonos};
        this.clientesSaldos = clientesSaldos;



    }

    public String[] getClientesNombres() {
        return clientesNombres;
    }

    public String[] getClientesEmails() {
        return clientesEmails;
    }

    public String[] getClientesTelefonos() {
        return clientesTelefonos;
    }

    public double[] getClientesSaldos() {
        return clientesSaldos;
    }

    public int getTotalClientes() {
        return totalClientes;
    }

    public void setTotalClientes(int totalClientes) {
        this.totalClientes = totalClientes;
    }

    public void setClientesSaldos(double[] clientesSaldos) {
        this.clientesSaldos = clientesSaldos;
    }

    public void setClientesTelefonos(String[] clientesTelefonos) {
        this.clientesTelefonos = clientesTelefonos;
    }

    public void setClientesEmails(String[] clientesEmails) {
        this.clientesEmails = clientesEmails;
    }

    public void setClientesNombres(String[] clientesNombres) {
        this.clientesNombres = clientesNombres;
    }
}

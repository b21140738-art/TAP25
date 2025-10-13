package ejemplo2.view;
// VistaCafeteria.java
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import ejemplo2.controller.ControladorCafeteria;
import ejemplo2.controller.Orden;
import ejemplo2.model.Bebida;
import ejemplo2.model.bebidas.Cafe;
import ejemplo2.model.bebidas.Expreso;
import ejemplo2.model.bebidas.Te;
import ejemplo2.model.decoradorCondimento.*;

public class VistaCafeteria extends JFrame {
    private ControladorCafeteria controlador;
    private JTextArea areaTextoOrden;
    private JLabel etiquetaTotal;

    public VistaCafeteria(ControladorCafeteria controlador) {
        this.controlador = controlador;
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Sistema de Cafetería - Patrón Decorator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de selección de bebidas
        JPanel panelSeleccion = crearPanelSeleccion();
        add(panelSeleccion, BorderLayout.NORTH);

        // Área de texto para mostrar la orden
        areaTextoOrden = new JTextArea(10, 30);
        areaTextoOrden.setEditable(false);
        JScrollPane panelScroll = new JScrollPane(areaTextoOrden);
        add(panelScroll, BorderLayout.CENTER);

        // Panel de control
        JPanel panelControl = crearPanelControl();
        add(panelControl, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel crearPanelSeleccion() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        // Bebidas base
        JPanel panelBase = new JPanel();
        panelBase.setBorder(BorderFactory.createTitledBorder("Bebidas Base"));

        JButton botonCafe = new JButton("Café Negro");
        JButton botonExpreso = new JButton("Expreso");
        JButton botonTe = new JButton("Té");

        botonCafe.addActionListener(e -> agregarBebidaConCondimentos(Cafe.class));
        botonExpreso.addActionListener(e -> agregarBebidaConCondimentos(Expreso.class));
        botonTe.addActionListener(e -> agregarBebidaConCondimentos(Te.class));

        panelBase.add(botonCafe);
        panelBase.add(botonExpreso);
        panelBase.add(botonTe);

        // Combinaciones predefinidas
        JPanel panelCombo = new JPanel();
        panelCombo.setBorder(BorderFactory.createTitledBorder("Especialidades"));

        JButton botonCapuchino = new JButton("Capuchino");
        JButton botonMacchiato = new JButton("Caramel Macchiato");
        JButton botonTeDulce = new JButton("Té Dulce");

        botonCapuchino.addActionListener(e -> crearCapuchino());
        botonMacchiato.addActionListener(e -> crearCaramelMacchiato());
        botonTeDulce.addActionListener(e -> crearTeDulce());

        panelCombo.add(botonCapuchino);
        panelCombo.add(botonMacchiato);
        panelCombo.add(botonTeDulce);

        panel.add(panelBase);
        panel.add(panelCombo);

        return panel;
    }

    private JPanel crearPanelControl() {
        JPanel panel = new JPanel();

        etiquetaTotal = new JLabel("Total: $0.00");
        JButton botonNuevaOrden = new JButton("Nueva Orden");
        JButton botonGuardarOrden = new JButton("Guardar Orden");
        JButton botonVerOrdenes = new JButton("Ver Órdenes");

        botonNuevaOrden.addActionListener(e -> iniciarNuevaOrden());
        botonGuardarOrden.addActionListener(e -> guardarOrden());
        botonVerOrdenes.addActionListener(e -> verTodasLasOrdenes());

        panel.add(etiquetaTotal);
        panel.add(botonNuevaOrden);
        panel.add(botonGuardarOrden);
        panel.add(botonVerOrdenes);

        return panel;
    }

    private void agregarBebidaConCondimentos(Class<? extends Bebida> claseBebida) {
        // Diálogo para seleccionar condimentos
        JCheckBox checkLeche = new JCheckBox("Leche");
        JCheckBox checkAzucar = new JCheckBox("Azúcar");
        JCheckBox checkCrema = new JCheckBox("Crema Batida");
        JCheckBox checkCaramelo = new JCheckBox("Caramelo");

        JPanel panelOpciones = new JPanel(new GridLayout(4, 1));
        panelOpciones.add(checkLeche);
        panelOpciones.add(checkAzucar);
        panelOpciones.add(checkCrema);
        panelOpciones.add(checkCaramelo);

        int resultado = JOptionPane.showConfirmDialog(this, panelOpciones,
                "Seleccione condimentos", JOptionPane.OK_CANCEL_OPTION);

        if (resultado == JOptionPane.OK_OPTION) {
            java.util.List<Class<? extends DecoradorCondimento>> condimentos = new java.util.ArrayList<>();

            if (checkLeche.isSelected()) condimentos.add(Leche.class);
            if (checkAzucar.isSelected()) condimentos.add(Azucar.class);
            if (checkCrema.isSelected()) condimentos.add(CremaBatida.class);
            if (checkCaramelo.isSelected()) condimentos.add(Caramelo.class);

            Bebida bebida = controlador.crearBebida(claseBebida, condimentos);
            controlador.agregarBebidaAOrdenActual(bebida);
            actualizarVisualizacionOrden();
        }
    }

    private void crearCapuchino() {
        Bebida capuchino = controlador.crearBebida(Expreso.class,
                Arrays.asList(Leche.class, CremaBatida.class));
        controlador.agregarBebidaAOrdenActual(capuchino);
        actualizarVisualizacionOrden();
    }

    private void crearCaramelMacchiato() {
        Bebida macchiato = controlador.crearBebida(Expreso.class,
                Arrays.asList(Leche.class, Caramelo.class, CremaBatida.class));
        controlador.agregarBebidaAOrdenActual(macchiato);
        actualizarVisualizacionOrden();
    }

    private void crearTeDulce() {
        Bebida teDulce = controlador.crearBebida(Te.class,
                Arrays.asList(Azucar.class, Leche.class));
        controlador.agregarBebidaAOrdenActual(teDulce);
        actualizarVisualizacionOrden();
    }

    private void iniciarNuevaOrden() {
        controlador.iniciarNuevaOrden();
        actualizarVisualizacionOrden();
    }

    private void guardarOrden() {
        controlador.guardarOrdenActual();
        JOptionPane.showMessageDialog(this, "Orden guardada exitosamente!");
        iniciarNuevaOrden();
    }

    private void verTodasLasOrdenes() {
        java.util.List<Orden> ordenes = controlador.obtenerTodasLasOrdenes();
        StringBuilder sb = new StringBuilder("Órdenes Guardadas:\n\n");

        for (Orden orden : ordenes) {
            sb.append(orden.getDetallesOrden()).append("\n\n");
        }

        JTextArea areaTexto = new JTextArea(sb.toString(), 15, 40);
        areaTexto.setEditable(false);
        JScrollPane panelScroll = new JScrollPane(areaTexto);
        JOptionPane.showMessageDialog(this, panelScroll, "Órdenes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarVisualizacionOrden() {
        Orden ordenActual = controlador.getOrdenActual();
        StringBuilder sb = new StringBuilder("Orden Actual:\n\n");

        for (Bebida bebida : ordenActual.getBebidas()) {
            sb.append("- ").append(bebida.getDescripcion())
                    .append(": $").append(String.format("%.2f", bebida.getCosto()))
                    .append("\n");
        }

        sb.append("\nTotal: $").append(String.format("%.2f", ordenActual.getTotal()));
        areaTextoOrden.setText(sb.toString());
        etiquetaTotal.setText(String.format("Total: $%.2f", ordenActual.getTotal()));
    }
}
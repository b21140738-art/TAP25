package controls;

import models.CajeroModel;
import strategy.*;
import views.CajeroView;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal del cajero automático.
 * Gestiona el ciclo de vida del sistema y delega operaciones
 * a las estrategias correspondientes.
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    private Map<Integer, OperacionStrategy> operaciones;

    /**
     * Constructor del controlador.
     *
     * @param model Modelo con la lógica del cajero.
     * @param view Vista para interactuar con el usuario.
     */
    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
        inicializarOperaciones();
    }

    /**
     * Inicializa el mapa de estrategias disponibles.
     */
    private void inicializarOperaciones() {
        operaciones = new HashMap<>();
        operaciones.put(1, new ConsultarSaldoStrategy());
        operaciones.put(2, new RetiroStrategy());
        operaciones.put(3, new DepositoStrategy());
        operaciones.put(4, new TransferenciaStrategy());
        operaciones.put(5, new CambiarPinStrategy());
    }

    /**
     * Inicia el sistema del cajero automático.
     * Autentica al usuario y ejecuta el menú principal.
     */
    public void iniciarSistema() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if (autenticarUsuario()) {
                ejecutarMenuPrincipal();
            } else {
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }

    /**
     * Solicita credenciales al usuario y las valida en el modelo.
     *
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarnumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    /**
     * Ejecuta el menú principal y delega las opciones
     * en la estrategia correspondiente.
     */
    private void ejecutarMenuPrincipal() {
        boolean seccionActiva = true;
        while (seccionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular(),model.getCuentaActual().getTipoCuenta());
            int opcion = view.leerOpcion();

            if (opcion == 9) {
                cerrarSistema();
                seccionActiva = false;
            } else {
                OperacionStrategy operacion = operaciones.get(opcion);
                if (operacion != null) {
                    operacion.ejecutar(model, view);
                } else {
                    view.mostrarMensaje("Opción no válida");
                }
            }
        }
    }

    /**
     * Cierra el sistema y libera recursos.
     */
    public void cerrarSistema() {
        view.cerrarScanner();
        sistemaActivo = false;
    }
}

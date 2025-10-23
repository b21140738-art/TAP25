package clases.view;

import clases.controllere.UsuarioController;
import clases.model.entidades.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsolaView {
    private UsuarioController usuarioController;
    private Scanner scanner;

    public ConsolaView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== SISTEMA DE NOTIFICACIONES ===");

        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    enviarNotificacion();
                    break;
                case 4:
                    enviarNotificacionUrgente();
                    break;
                case 5:
                    enviarNotificacionCifrada();
                    break;
                case 6:
                    enviarNotificacionGlobal();
                    break;
                case 7:
                    configurarNotificaciones();
                    break;
                case 8:
                    eliminarUsuario();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

            System.out.println("\nPresione Enter para volver al menú...");
            scanner.nextLine();
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Enviar notificación");
        System.out.println("4. Enviar notificación urgente");
        System.out.println("5. Enviar notificación cifrada");
        System.out.println("6. Enviar notificación global");
        System.out.println("7. Configurar notificaciones de usuario");
        System.out.println("8. Eliminar usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void crearUsuario() {
        System.out.println("\n--- CREAR USUARIO ---");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (usuarioController.existeUsuario(id)) {
            System.out.println("Error: Ya existe un usuario con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        if (usuarioController.crearUsuario(id, nombre, email, telefono)) {
            System.out.println("Usuario creado exitosamente.");
        } else {
            System.out.println("Error al crear el usuario.");
        }
    }

    private void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        List<Usuario> usuarios = usuarioController.obtenerTodosUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                System.out.printf("%d. %s - %s - %s%n",
                        i + 1, usuario.getNombre(), usuario.getEmail(), usuario.getTelefono());
                System.out.printf("   Notificaciones: Email: %s | SMS: %s | Push: %s%n",
                        usuario.isNotificacionesEmail() ? "✓" : "✗",
                        usuario.isNotificacionesSMS() ? "✓" : "✗",
                        usuario.isNotificacionesPush() ? "✓" : "✗");
            }
        }
    }

    private void enviarNotificacion() {
        System.out.println("\n--- ENVIAR NOTIFICACIÓN ---");
        enviarNotificacionGenerica("normal");
    }

    private void enviarNotificacionUrgente() {
        System.out.println("\n--- ENVIAR NOTIFICACIÓN URGENTE ---");
        enviarNotificacionGenerica("urgente");
    }

    private void enviarNotificacionCifrada() {
        System.out.println("\n--- ENVIAR NOTIFICACIÓN CIFRADA ---");
        enviarNotificacionGenerica("cifrada");
    }

    private void enviarNotificacionGenerica(String tipo) {
        System.out.print("ID del usuario: ");
        String usuarioId = scanner.nextLine();

        System.out.print("Mensaje: ");
        String mensaje = scanner.nextLine();

        boolean resultado = false;

        switch (tipo) {
            case "normal":
                resultado = usuarioController.enviarNotificacion(usuarioId, mensaje);
                break;
            case "urgente":
                resultado = usuarioController.enviarNotificacionUrgente(usuarioId, mensaje);
                break;
            case "cifrada":
                resultado = usuarioController.enviarNotificacionCifrada(usuarioId, mensaje);
                break;
        }

        if (resultado) {
            System.out.println("Notificación enviada exitosamente.");
        } else {
            System.out.println("Error: No se pudo enviar la notificación. Verifique el ID del usuario.");
        }
    }

    private void enviarNotificacionGlobal() {
        System.out.println("\n--- ENVIAR NOTIFICACIÓN GLOBAL ---");

        System.out.print("Mensaje: ");
        String mensaje = scanner.nextLine();

        if (usuarioController.enviarNotificacionGlobal(mensaje)) {
            System.out.println("Notificación global enviada a todos los usuarios.");
        } else {
            System.out.println("Error: No hay usuarios registrados o no se pudo enviar la notificación.");
        }
    }

    private void configurarNotificaciones() {
        System.out.println("\n--- CONFIGURAR NOTIFICACIONES ---");

        System.out.print("ID del usuario: ");
        String usuarioId = scanner.nextLine();

        Optional<Usuario> usuarioOpt = usuarioController.obtenerUsuario(usuarioId);
        if (!usuarioOpt.isPresent()) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        Usuario usuario = usuarioOpt.get();
        System.out.println("Configurando notificaciones para: " + usuario.getNombre());

        System.out.print("¿Recibir notificaciones por Email? (s/n): ");
        boolean email = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("¿Recibir notificaciones por SMS? (s/n): ");
        boolean sms = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("¿Recibir notificaciones Push? (s/n): ");
        boolean push = scanner.nextLine().equalsIgnoreCase("s");

        if (usuarioController.configurarNotificaciones(usuarioId, email, sms, push)) {
            System.out.println("Configuración actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la configuración.");
        }
    }

    private void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");

        System.out.print("ID del usuario a eliminar: ");
        String usuarioId = scanner.nextLine();

        if (usuarioController.eliminarUsuario(usuarioId)) {
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Error: No se pudo eliminar el usuario. Verifique el ID.");
        }
    }
}
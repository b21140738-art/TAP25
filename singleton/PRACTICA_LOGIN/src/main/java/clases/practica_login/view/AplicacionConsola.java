package clases.practica_login.view;

import clases.practica_login.model.Usuario;
import clases.practica_login.controller.service.autentificar;
import clases.practica_login.controller.service.password;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Optional;

public class AplicacionConsola {
    private autentificar servicioAutenticacion;
    private password servicioPassword;
    private Scanner scanner;
    private boolean ejecutando;

    public AplicacionConsola() {
        this.servicioAutenticacion = new autentificar();
        this.servicioPassword = new password();
        this.scanner = new Scanner(System.in);
        this.ejecutando = true;
    }

    public void iniciar() {
        System.out.println("BIENVENIDO AL SISTEMA DE LOGIN - MODO CONSOLA");
        System.out.println("=================================================");

        while (ejecutando) {
            mostrarMenuPrincipal();
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> iniciarSesion();
                case 2 -> registrarUsuario();
                case 3 -> {
                    System.out.println("¡Hasta pronto!");
                    ejecutando = false;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\nMENÚ PRINCIPAL:");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2.Registrar Usuario");
        System.out.println("3.Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void iniciarSesion() {
        System.out.println("\n INICIAR SESIÓN");
        System.out.println("=================");

        System.out.print("Usuario o Correo: ");
        String usuarioOCorreo = scanner.nextLine().trim();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        if (usuarioOCorreo.isEmpty() || contraseña.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios");
            return;
        }

        try {
            Optional<Usuario> usuarioOpt = servicioAutenticacion.login(usuarioOCorreo, contraseña);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                mostrarBienvenidaConsola(usuario);
            } else {
                System.out.println("Credenciales inválidas. Intente nuevamente.");
            }
        } catch (Exception e) {
            System.out.println("Error durante el login: " + e.getMessage());
        }
    }

    private void registrarUsuario() {
        System.out.println("\nREGISTRO DE USUARIO");
        System.out.println("======================");

        // Nombre completo
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine().trim();

        // Fecha de nacimiento
        LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento (dd/MM/yyyy): ");
        if (fechaNacimiento == null) return;

        // Username
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine().trim();

        // Correo electrónico
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine().trim().toLowerCase();

        // Contraseña
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.print("Confirmar contraseña: ");
        String confirmarContraseña = scanner.nextLine();

        // Validaciones
        if (!validarRegistro(nombreCompleto, fechaNacimiento, nombreUsuario, correo, contraseña, confirmarContraseña)) {
            return;
        }

        try {
            Usuario usuario = new Usuario(nombreUsuario, correo, "", nombreCompleto, fechaNacimiento);
            boolean registrado = servicioAutenticacion.registrarUsuario(usuario, contraseña);

            if (registrado) {
                System.out.println("¡Usuario registrado exitosamente!");
                System.out.println("Puede iniciar sesión con:");
                System.out.println("Usuario: " + nombreUsuario);
                System.out.println("Correo: " + correo);
            } else {
                System.out.println("Error al registrar el usuario.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    private boolean validarRegistro(String nombreCompleto, LocalDate fechaNacimiento,
                                    String nombreUsuario, String correo, String contraseña, String confirmarContraseña) {
        if (nombreCompleto.isEmpty() || nombreUsuario.isEmpty() || correo.isEmpty() ||
                contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios");
            return false;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            System.out.println("Error: Las contraseñas no coinciden");
            return false;
        }

        if (!servicioPassword.isValidPassword(contraseña)) {
            System.out.println(" Error: La contraseña debe tener:");
            System.out.println("   - Mínimo 8 caracteres");
            System.out.println("   - Al menos una mayúscula");
            System.out.println("   - Al menos una minúscula");
            System.out.println("   - Al menos un número");
            System.out.println("   - Al menos un carácter especial (@$!%*?&)");
            return false;
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.out.println("Error: Formato de correo electrónico inválido");
            return false;
        }

        // Validar edad mínima (13 años)
        LocalDate fechaMinima = LocalDate.now().minusYears(13);
        if (fechaNacimiento.isAfter(fechaMinima)) {
            System.out.println("Error: Debes tener al menos 13 años para registrarte");
            return false;
        }

        // Verificar si username ya existe
        if (servicioAutenticacion.existeUsername(nombreUsuario)) {
            System.out.println("Error: El nombre de usuario ya está en uso");
            return false;
        }

        // Verificar si correo ya existe
        if (servicioAutenticacion.existeCorreo(correo)) {
            System.out.println("Error: El correo electrónico ya está registrado");
            return false;
        }

        return true;
    }

    private void mostrarBienvenidaConsola(Usuario usuario) {
        System.out.println("\n¡BIENVENIDO " + usuario.getNombreCompleto().toUpperCase() + "!");
        System.out.println("==================================");
        System.out.println("Información de tu cuenta:");
        System.out.println("Nombre: " + usuario.getNombreCompleto());
        System.out.println("Usuario: @" + usuario.getUsername());
        System.out.println("Correo: " + usuario.getCorreo());

        if (usuario.getFechaNacimiento() != null) {
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("   Fecha Nacimiento: " + usuario.getFechaNacimiento().format(formateador));
        }

        if (usuario.getFechaRegistro() != null) {
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            System.out.println("   Fecha Registro: " + usuario.getFechaRegistro().format(formateador));
        }

        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    // Métodos auxiliares para entrada de datos
    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }

    private LocalDate leerFecha(String mensaje) {
        System.out.print(mensaje);
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(entrada, formateador);
            } catch (DateTimeParseException e) {
                System.out.print("Formato de fecha inválido. Use dd/MM/yyyy: ");
            }
        }
    }
}
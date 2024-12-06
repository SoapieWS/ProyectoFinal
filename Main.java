package com.mycompany.game;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GhostGame game;
    private static Player currentPlayer;
    private static String dificultadSeleccionada;
    private static String modoDeJuegoSeleccionado;

    public static void main(String[] args) {
        mostrarMenuInicio();
    }

    // Menú de inicio
    public static void mostrarMenuInicio() {
        while (true) {
            System.out.println("MENÚ DE INICIO");
            System.out.println("1. Login");
            System.out.println("2. Crear Player");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    crearPlayer();
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // Función para iniciar sesión
    private static void login() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        
        Player player = buscarPlayer(username, password);
        if (player != null) {
            currentPlayer = player;
            mostrarMenuPrincipal();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    // Buscar jugador por username y contraseña
    private static Player buscarPlayer(String username, String password) {
        // Aquí debería ir la lógica para buscar el jugador en la colección
        // Ejemplo:
        // for (Player p : listaDePlayers) {
        //     if (p.getNombreUsuario().equals(username) && p.getContraseña().equals(password)) {
        //         return p;
        //     }
        // }
        return null; // Retorna null si no encuentra al jugador
    }

    // Función para crear un nuevo jugador
    private static void crearPlayer() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        if (esUsernameUnico(username)) {
            System.out.print("Ingrese su contraseña (8 caracteres): ");
            String password = scanner.nextLine();
            if (password.length() == 8) {
                Player newPlayer = new Player(username, password);
                // Aquí se debería agregar el jugador a la lista
                // listaDePlayers.add(newPlayer);
                System.out.println("Jugador creado exitosamente.");
                currentPlayer = newPlayer;
                mostrarMenuPrincipal();
            } else {
                System.out.println("La contraseña debe tener exactamente 8 caracteres.");
            }
        } else {
            System.out.println("El nombre de usuario ya existe.");
        }
    }

    // Validar si el nombre de usuario es único
    private static boolean esUsernameUnico(String username) {
        // Verificar que el nombre de usuario no esté repetido
        // Ejemplo:
        // for (Player p : listaDePlayers) {
        //     if (p.getNombreUsuario().equals(username)) {
        //         return false;
        //     }
        // }
        return true; // Retorna true si es único
    }

    // Menú principal
    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("1. Jugar Ghosts");
            System.out.println("2. Configuración");
            System.out.println("3. Reportes");
            System.out.println("4. Mi Perfil");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1:
                    jugarGhosts();
                    break;
                case 2:
                    mostrarSubMenuConfiguracion();
                    break;
                case 3:
                    mostrarSubMenuReportes();
                    break;
                case 4:
                    mostrarSubMenuPerfil();
                    break;
                case 5:
                    System.out.println("Cerrando sesión...");
                    currentPlayer = null;
                    mostrarMenuInicio();
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // Función para jugar Ghosts
    private static void jugarGhosts() {
        System.out.println("Iniciando juego...");

        // Solicitar el nombre del oponente
        System.out.print("Ingrese el nombre del oponente: ");
        String oponente = scanner.nextLine();

        // Verificar si las configuraciones están definidas
        if (dificultadSeleccionada == null || modoDeJuegoSeleccionado == null) {
            System.out.println("¡ERROR! Debe configurar la dificultad y el modo de juego primero.");
            return; // Si no están configuradas, no se puede iniciar el juego
        }

        // Mostrar configuración seleccionada
        System.out.println("Configuraciones del juego:");
        System.out.println("Dificultad seleccionada: " + dificultadSeleccionada);
        System.out.println("Modo de juego seleccionado: " + modoDeJuegoSeleccionado);

        // Aquí es donde el juego realmente comienza
        iniciarJuego(oponente);
    }

    // Obtener la dificultad seleccionada
    private static String obtenerDificultad() {
        return dificultadSeleccionada;
    }

    // Obtener el modo de juego seleccionado
    private static String obtenerModoDeJuego() {
        return modoDeJuegoSeleccionado;
    }

    // Iniciar el juego
    private static void iniciarJuego(String oponente) {
        System.out.println("Comienza el juego entre " + currentPlayer.getNombreUsuario() + " y " + oponente);
        // Aquí debes agregar la lógica real del juego.
        // Puedes colocar los fantasmas en el tablero, definir las reglas del juego, etc.
    }

    // Submenú de configuración
    private static void mostrarSubMenuConfiguracion() {
        while (true) {
            System.out.println("CONFIGURACIÓN");
            System.out.println("a. Dificultad");
            System.out.println("b. Modo de Juego");
            System.out.println("c. Regresar al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "a":
                    configurarDificultad();
                    break;
                case "b":
                    configurarModoDeJuego();
                    break;
                case "c":
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // Configuración de dificultad
    private static void configurarDificultad() {
        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Normal (8 fantasmas)");
        System.out.println("2. Expert (4 fantasmas)");
        System.out.println("3. Genius (2 fantasmas)");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                dificultadSeleccionada = "Normal";
                System.out.println("Dificultad seleccionada: Normal");
                break;
            case 2:
                dificultadSeleccionada = "Expert";
                System.out.println("Dificultad seleccionada: Expert");
                break;
            case 3:
                dificultadSeleccionada = "Genius";
                System.out.println("Dificultad seleccionada: Genius");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    // Configuración de modo de juego
    private static void configurarModoDeJuego() {
        System.out.println("Seleccione el modo de juego:");
        System.out.println("1. Aleatorio");
        System.out.println("2. Manual");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                modoDeJuegoSeleccionado = "Aleatorio";
                System.out.println("Modo de juego seleccionado: Aleatorio");
                break;
            case 2:
                modoDeJuegoSeleccionado = "Manual";
                System.out.println("Modo de juego seleccionado: Manual");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    // Submenú de reportes
    private static void mostrarSubMenuReportes() {
        System.out.println("Ranking de jugadores:");
        // Aquí va la lógica para mostrar el ranking
    }

    // Submenú de perfil
    private static void mostrarSubMenuPerfil() {
        System.out.println("MI PERFIL");
        // Aquí puedes agregar la lógica para mostrar y editar el perfil del jugador
    }
}

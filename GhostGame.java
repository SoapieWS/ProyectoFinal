package com.mycompany.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GhostGame {
    private static List<Player> jugadores = new ArrayList<>();  // Colección de jugadores
    private Player jugador1;
    private Player jugador2;
    private Player jugadorActual;
    private String[][] tablero;
    private int dificultad;  // 0: Normal, 1: Expert, 2: Genius
    private String modoJuego;  // "ALEATORIO" o "MANUAL"

    // Constructor del juego
    public GhostGame(Player jugador1, Player jugador2, int dificultad, String modoJuego) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.dificultad = dificultad;
        this.modoJuego = modoJuego;
        this.tablero = new String[6][6];  // Tablero 6x6
        this.jugadorActual = jugador1;  // El jugador 1 empieza
        inicializarTablero();
    }

    // Método para mostrar las instrucciones del juego
    public static void mostrarInstrucciones() {
        System.out.println("¡Bienvenido al juego de los Fantasmas!");
        System.out.println("Instrucciones:");
        System.out.println("1. Cada jugador tendrá 8 fantasmas: 4 buenos y 4 malos.");
        System.out.println("2. Los fantasmas buenos deben ser protegidos, mientras que los malos pueden ser sacrificados.");
        System.out.println("3. El objetivo del juego es sacar un fantasma bueno por las esquinas opuestas del tablero enemigo o capturar todos los fantasmas buenos del oponente.");
        System.out.println("4. Los movimientos se realizan a casillas adyacentes (horizontal, vertical o diagonal).");
        System.out.println("5. Durante el turno, puedes mover un fantasma a una casilla vacía o capturar un fantasma enemigo en la misma casilla.");
        System.out.println("6. Gana el primer jugador en lograr su objetivo.");
        System.out.println("¡Buena suerte!");
        System.out.println();
    }

    // Método para agregar un jugador a la colección
    public static void addPlayer(Player jugador) {
        jugadores.add(jugador);
    }

    // Método para obtener un jugador por su nombre de usuario
    public static Player getPlayerByUsername(String username) {
        for (Player jugador : jugadores) {
            if (jugador.getNombreUsuario().equals(username)) {
                return jugador;
            }
        }
        return null;  // Si no se encuentra el jugador
    }

    // Inicializa el tablero (vacío o con fantasmas, según el modo de juego)
    private void inicializarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tablero[i][j] = "-";  // El tablero comienza vacío
            }
        }
        // Colocamos los fantasmas en el tablero según el modo de juego
        if (modoJuego.equals("ALEATORIO")) {
            colocarFantasmasAleatoriamente();
        } else if (modoJuego.equals("MANUAL")) {
            colocarFantasmasManual();
        }
    }

    // Coloca los fantasmas aleatoriamente en el tablero
    private void colocarFantasmasAleatoriamente() {
        // Lógica para colocar fantasmas de manera aleatoria
    }

    // Coloca los fantasmas manualmente en el tablero
    private void colocarFantasmasManual() {
        Scanner sc = new Scanner(System.in);
        // Colocación de fantasmas de manera manual para ambos jugadores
        System.out.println(jugador1.getNombreUsuario() + ", coloca tus fantasmas (bueno y malo).");
        colocarFantasmasJugador(jugador1);
        System.out.println(jugador2.getNombreUsuario() + ", coloca tus fantasmas (bueno y malo).");
        colocarFantasmasJugador(jugador2);
    }

    // Método para colocar los fantasmas de un jugador manualmente
    private void colocarFantasmasJugador(Player jugador) {
        int[] posiciones = new int[16]; // 8 buenos, 8 malos
        Scanner sc = new Scanner(System.in);
        // Alterna entre buenos y malos, y coloca los fantasmas
        for (int i = 0; i < 8; i++) {
            String tipoFantasma = (i % 2 == 0) ? "Bueno" : "Malo";
            System.out.println(jugador.getNombreUsuario() + " coloca un fantasma " + tipoFantasma);
            System.out.print("Ingresa la fila (1-2): ");
            int fila = sc.nextInt();
            System.out.print("Ingresa la columna (1-4): ");
            int columna = sc.nextInt();
            // Verifica si la posición está vacía y dentro de los límites
            if (fila >= 1 && fila <= 2 && columna >= 1 && columna <= 4 && tablero[fila - 1][columna - 1].equals("-")) {
                tablero[fila - 1][columna - 1] = tipoFantasma;
            } else {
                System.out.println("Posición inválida. Intenta de nuevo.");
                i--;
            }
        }
    }

    // Método para jugar el turno de un jugador
    public void jugarTurno() {
        Scanner sc = new Scanner(System.in);
        boolean turnoValido = false;

        while (!turnoValido) {
            System.out.println(jugadorActual.getNombreUsuario() + ", es tu turno.");
            System.out.print("Selecciona la fila de tu fantasma (0-5): ");
            int filaSeleccionada = sc.nextInt();
            System.out.print("Selecciona la columna de tu fantasma (0-5): ");
            int columnaSeleccionada = sc.nextInt();

            // Verificar que el fantasma pertenece al jugador actual
            if (tablero[filaSeleccionada][columnaSeleccionada].equals("Bueno") || tablero[filaSeleccionada][columnaSeleccionada].equals("Malo")) {
                System.out.print("A qué fila deseas mover tu fantasma? ");
                int filaDestino = sc.nextInt();
                System.out.print("A qué columna deseas mover tu fantasma? ");
                int columnaDestino = sc.nextInt();

                // Verificar que el movimiento sea válido
                if (validarMovimiento(filaSeleccionada, columnaSeleccionada, filaDestino, columnaDestino)) {
                    realizarMovimiento(filaSeleccionada, columnaSeleccionada, filaDestino, columnaDestino);
                    turnoValido = true;
                } else {
                    System.out.println("Movimiento no válido, intentalo nuevamente.");
                }
            } else {
                System.out.println("No seleccionaste un fantasma válido, intentalo nuevamente.");
            }
        }
    }

    // Valida si el movimiento es posible
    private boolean validarMovimiento(int filaSeleccionada, int columnaSeleccionada, int filaDestino, int columnaDestino) {
        if (Math.abs(filaDestino - filaSeleccionada) > 1 || Math.abs(columnaDestino - columnaSeleccionada) > 1) {
            return false;  // El movimiento debe ser a una casilla adyacente
        }
        if (!tablero[filaDestino][columnaDestino].equals("-")) {
            return false;  // No puede moverse a una casilla ocupada
        }
        return true;
    }

    // Realiza el movimiento de un fantasma
    private void realizarMovimiento(int filaSeleccionada, int columnaSeleccionada, int filaDestino, int columnaDestino) {
        // Realiza el movimiento de un fantasma en el tablero
        tablero[filaDestino][columnaDestino] = tablero[filaSeleccionada][columnaSeleccionada];
        tablero[filaSeleccionada][columnaSeleccionada] = "-"; // La casilla anterior queda vacía

        // Comprobar si se ha capturado un fantasma
        if (tablero[filaDestino][columnaDestino].equals("Bueno") || tablero[filaDestino][columnaDestino].equals("Malo")) {
            capturarFantasma(filaDestino, columnaDestino);
        }

        // Cambiar el turno al siguiente jugador
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }

    // Método para capturar un fantasma
    private void capturarFantasma(int fila, int columna) {
        String tipoFantasma = tablero[fila][columna];
        if (tipoFantasma.equals("Bueno")) {
            System.out.println(jugadorActual.getNombreUsuario() + " ha capturado un fantasma bueno!");
        } else {
            System.out.println(jugadorActual.getNombreUsuario() + " ha capturado un fantasma malo!");
        }
    }

    // Método para comprobar si hay un ganador
    public void comprobarVictoria() {
        // Comprobar si un jugador ha ganado
        // Si algún jugador ha capturado todos los fantasmas buenos o ha eliminado todos los malos, gana
        // También se debe comprobar si un jugador ha sacado un fantasma bueno por la salida del castillo
    }

    // Método para mostrar el tablero
    public void mostrarTablero() {
        System.out.println("Tablero:");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
}

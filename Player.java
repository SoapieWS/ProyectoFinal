package com.mycompany.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nombreUsuario;
    private String password;
    private int puntos;
    private List<String> ultimosJuegos; // Almacena las descripciones de los últimos 10 juegos

    // Constructor de la clase Player
    public Player(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.puntos = 0;
        this.ultimosJuegos = new ArrayList<>();
    }

    // Getter para obtener el nombre de usuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Setter para cambiar el nombre de usuario
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // Getter para obtener la contraseña
    public String getPassword() {
        return password;
    }

    // Setter para cambiar la contraseña
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter para obtener los puntos del jugador
    public int getPuntos() {
        return puntos;
    }

    // Setter para cambiar los puntos del jugador
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    // Método para agregar un juego a la lista de últimos juegos
    public void agregarJuego(String descripcionJuego) {
        if (ultimosJuegos.size() >= 10) {
            ultimosJuegos.remove(0);  // Elimina el juego más antiguo si hay más de 10
        }
        ultimosJuegos.add(descripcionJuego);  // Agrega la descripción del juego actual
    }

    // Método para obtener la lista de los últimos 10 juegos
    public List<String> getUltimosJuegos() {
        return ultimosJuegos;
    }

    // Método para mostrar los últimos 10 juegos
    public void mostrarUltimosJuegos() {
        if (ultimosJuegos.isEmpty()) {
            System.out.println("Aún no has jugado ningún juego.");
        } else {
            System.out.println("Últimos juegos de " + nombreUsuario + ":");
            for (String juego : ultimosJuegos) {
                System.out.println(juego);
            }
        }
    }

    // Método para incrementar los puntos del jugador
    public void incrementarPuntos(int puntos) {
        this.puntos += puntos;
    }

    // Método para verificar si la contraseña es correcta
    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }

    void setContraseña(String nuevaContraseña) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getContraseña() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

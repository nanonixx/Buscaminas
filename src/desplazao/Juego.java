package desplazao;

import java.util.Scanner;

public class Juego {

    private boolean valid;
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    private int minasCazadasP1 = 0;
    private int minasCazadasP2 = 0;

    public Tablero start(String coor, Tablero tablero, boolean player) {
        //valid = false;
        //para repetir turno si no se introducen bien los parámetros

        try {
            int hayMina = tablero.recorrer(coor, player);

            // 1 = mina hallada; 0 = mina no hallada; -1 input erroneo
            if (hayMina == 1 && player) minasCazadasP1++;
            if (hayMina == 1 && !player) minasCazadasP2++;
            if (hayMina != -1) valid = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        if (minasCazadasP1 + minasCazadasP2 != tablero.numMinas) gameOver = false;
        return tablero;
    }
}

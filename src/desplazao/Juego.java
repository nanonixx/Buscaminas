package desplazao;

import java.util.Scanner;

public class Juego {

    Scanner sc = new Scanner(System.in);
    private boolean valid;
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    int minasCazadasP1 = 0;
    int minasCazadasP2 = 0;

    public void start(String coor, Tablero tablero, boolean player) {
        do {
            valid = false;
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


        }while(!valid);

        if (minasCazadasP1 + minasCazadasP2 != tablero.numMinas) gameOver = false;

    }

}

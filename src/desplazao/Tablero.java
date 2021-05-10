package desplazao;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;

public class Tablero {
    // 0 -> no está tocado
    // -1 -> no hay nada de mina
    // 1 -> hay la Mina
    Casilla[][] tableroMatriz;
    int numMinas;

    //dimension del tablero DIMxDIM
    private final int DIMENSION;

    Random random;

    public Tablero(int DIM, int numMinas) {
        this.numMinas = numMinas;
        this.DIMENSION = DIM;
        this.tableroMatriz = generarTablero();
    }

    public Casilla[][] generarTablero() {
        tableroMatriz = new Casilla[DIMENSION][DIMENSION];

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                tableroMatriz[i][j] = new Casilla("?");
            }
        }

        boolean repe = false;
        int num1 = (int) (Math.random() * (DIMENSION));
        int num2 = (int) (Math.random() * (DIMENSION ));
        for (int k = 0; k < numMinas; k++) { //por cada mina en casilla random

            do{
                if (tableroMatriz[num1][num2].mina) repe = true; //para saber si es repe entonces se repite
                else repe = false;
                tableroMatriz[num1][num2].mina = true; //pone mina
                num1 = (int) (Math.random() * (DIMENSION));
                num2 = (int) (Math.random() * (DIMENSION));
            }
            while (repe); //se repite si ya se habia puesto mina
        }
        return tableroMatriz;
    }

    public void mostrarTablero() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (j==0) System.out.print(i + "   ");
                System.out.print("| " + tableroMatriz[i][j].marca + " ");
//                System.out.print("| " + tableroMatriz[i][j].mina + " ");
            }
            System.out.println("|\n");
        }
        System.out.print("      0   1   2   3   4   5   6   7");
    }

    public boolean completado(){
        boolean finish = true;

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                //TODO: hacerlo bien luego esto:
                if (tableroMatriz[i][j].marca == "?") finish = false;
            }
        }
        return finish;
    }


}

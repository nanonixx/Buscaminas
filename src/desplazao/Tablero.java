package desplazao;

import jdk.swing.interop.SwingInterOpUtils;

public class Tablero {
    // 0 -> no está tocado
    // -1 -> no hay nada de mina
    // 1 -> hay la Mina
    int[][] tableroMatriz;

    //dimension del tablero DIMxDIM
    private final int DIMENSION;

    public Tablero(int DIM) {
        this.DIMENSION = DIM;
        this.tableroMatriz = generarTablero();
    }

    public int[][] generarTablero() {
        tableroMatriz = new int[DIMENSION][DIMENSION];

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                tableroMatriz[i][j] = 0;
            }
        }
        return tableroMatriz;
    }

    public void mostrarTablero() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (j==0) System.out.print(i + "   ");
                System.out.print("| " + tableroMatriz[i][j] + " ");
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
                if (tableroMatriz[i][j] == 0) finish = false;
            }
        }
        return finish;
    }


}

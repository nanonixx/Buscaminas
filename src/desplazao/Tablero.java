package desplazao;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Random;

import java.io.Serializable;

public class Tablero implements Serializable {
    // ? -> no estÃ¡ tocado
    // -  -> No hay la mina
    // 1 -> hay la Mina de P1
	//2 -> hay mina de P2
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
                tableroMatriz[i][j] = new Casilla(i+""+j);
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
                //activar el comment de arriba para ver las minas
            }
            System.out.println("|\n");
        }
        System.out.print("       0    1    2    3    4    5    6    7");
    }
    
    //este método pone las marcas en el tablero y devuelve si ha encontrado mina o no
    public int recorrer(String coor, int player) {
    	//int player es el num. del jugador del que está en turno
    	int fila = Integer.parseInt(coor.substring(0, 1));
    	int columna = Integer.parseInt(coor.substring(1));
    	    	
    	if (tableroMatriz[fila][columna].marca.length()!=2) {
    		//si la casilla ya ha sido descubierta
    		return -1;
    		
    	}    	
    	else if (tableroMatriz[fila][columna].mina) {
        	tableroMatriz[fila][columna].marca = String.valueOf(player+" ");
        	return 1; //mina encontrada
        }
        else {
        	tableroMatriz[fila][columna].marca = "-";
        	return 0; //mina no encontrada
        }
    	
    }


}

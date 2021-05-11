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
    
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    

    public Tablero(int DIM, int numMinas) {
        this.numMinas = numMinas;
        this.DIMENSION = DIM;
        this.tableroMatriz = generarTablero();
    }

    public Casilla[][] generarTablero() {
        tableroMatriz = new Casilla[DIMENSION][DIMENSION];

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                tableroMatriz[i][j] = new Casilla(+i+""+j);
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
                System.out.print(PURPLE+"| " + ANSI_RESET + tableroMatriz[i][j].marca + " ");
//                System.out.print("| " + tableroMatriz[i][j].mina + " ");
                //activar el comment de arriba para ver las minas
            }
            System.out.println(PURPLE+"|\n"+ANSI_RESET);
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
        	if (player==1)tableroMatriz[fila][columna].marca = String.valueOf(GREEN+"M1");
        	if (player==2)tableroMatriz[fila][columna].marca = String.valueOf(YELLOW+"M1");
        	return 1; //mina encontrada
        }
        else {
        	int minasAlrededor = destape(coor);
        	tableroMatriz[fila][columna].marca = String.valueOf(CYAN+minasAlrededor + "*");
        	return 0; //mina no encontrada
        }
    	
    }
    
    //Método que pone los numeritos alrededor
    public int destape(String coor) {
    	int fila = Integer.parseInt(coor.substring(0, 1));
    	int columna = Integer.parseInt(coor.substring(1));
    	
    	int minasAlrededor = 0;
    	
    	//fila1 (3)
    	if (fila!=0 && columna!=0) if (tableroMatriz[fila-1][columna-1].mina) minasAlrededor++;
    	if (columna!=0) if (tableroMatriz[fila][columna-1].mina) minasAlrededor++;
    	if (fila!=DIMENSION && columna!=0) if (tableroMatriz[fila+1][columna-1].mina) minasAlrededor++;
    	
    	//fila2 (2)
    	
    	if (fila!=0) if (tableroMatriz[fila-1][columna].mina) minasAlrededor++;
    	if (fila!=DIMENSION) if (tableroMatriz[fila+1][columna].mina) minasAlrededor++;
    	
    	//fila3 (3)
    	

    	if (fila!=0 && columna!=DIMENSION) if (tableroMatriz[fila-1][columna+1].mina) minasAlrededor++;
    	if (columna!=DIMENSION) if (tableroMatriz[fila][columna+1].mina) minasAlrededor++;
    	if (fila!=DIMENSION && columna!=DIMENSION) if (tableroMatriz[fila+1][columna+1].mina) minasAlrededor++;
    	
    	
    	return minasAlrededor;
    }


}

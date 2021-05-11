package desplazao;

import java.util.Scanner;

//Lógica del juego EN LOCAL. Hay que adaptarlo al ThreadServidor
public class JuegoTesteo {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int minasCazadasP1 = 0;
    	int minasCazadasP2 = 0;
    	int player;
    	boolean valid;
    	//para repetir turno si no se introducen bien los parámetros

        Tablero tablero = new Tablero(8, 10);
        player = 1; //va cambiando cada turno
        
        while (minasCazadasP1 + minasCazadasP2 != tablero.numMinas) {
        	//mientras no se encuentren todas las minas
        	System.out.println("TURNO DEL JUGADOR " + player);
        	
        	do {
        		 valid = false; //boolean valids <3
        		 tablero.mostrarTablero();
     	        
     	        System.out.print("\nIntroduce coordenada (fila/columna):  ");
     	        String tirada = sc.nextLine();
     	        
     	        try {
     		        int hayMina = tablero.recorrer(tirada, player);
     		        
     		        // 1 = mina hallada; 0 = mina no hallada; -1 input erroneo
     		        if (hayMina == 1 && player == 1) minasCazadasP1++;
     		        if (hayMina == 1 && player == 2) minasCazadasP2++;
     		        if (hayMina != -1) valid = true;
     		        if (hayMina == 0) System.out.println("¡Qué pena! No has descubierto ninguna mina");
     		        	
     		        else System.err.println("Jugada no válida. Casilla ya marcada");
     	        } catch (Exception e) {
     	        	System.err.println("Input inválido. Prueba otra vez");
     	        }
     	        
        		
        	}while(!valid);
        	
	       
	        System.out.println("QUEDAN " + (tablero.numMinas - minasCazadasP1 + minasCazadasP2) + " MINAS POR DESCUBRIR");
	        //los turnos se intercambian, pero no funciona
	        if (player == 1) player = 2;
	        if (player == 2) player = 1;
	        
        }
        
        System.out.println("*- FIN DEL JUEGO - *");
        if (minasCazadasP1 > minasCazadasP2) System.out.println("¡El jugador 1 es el mejor! El jugador 2 da ASCO");
        if (minasCazadasP2 > minasCazadasP1) System.out.println("¡El jugador 2 es el mejor! El jugador 1 da ASCO");
        if (minasCazadasP1 == minasCazadasP2) System.out.println("TREMENDO EMPATE");
        
        sc.close();
        
       
    }
}

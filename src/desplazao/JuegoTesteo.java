package desplazao;

import java.util.Scanner;

//para testear que van las cosas sin depender del server
public class JuegoTesteo {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int minasCazadas = 0;
    	int player;
    	boolean valid;
    	//para repetir turno si no se introducen bien los parámetros

        Tablero tablero = new Tablero(8, 10);
        player = 1;
        
        while (minasCazadas != tablero.numMinas) {
        	System.out.println("TURNO DEL JUGADOR " + player);
        	
        	do {
        		valid = false;
        		 tablero.mostrarTablero();
     	        
     	        System.out.print("\nIntroduce coordenada (fila/columna):  ");
     	        String tirada = sc.nextLine();
     	        
     	        try {
     		        int hayMina = tablero.recorrer(tirada, player);
     		        if (hayMina == 1) minasCazadas++;
     		        if (hayMina != -1) valid = true;
     		        else System.err.println("Jugada no válida. Casilla ya marcada");
     	        } catch (Exception e) {
     	        	System.err.println("Coordenada fuera de rango. Prueba otra vez");
     	        }
     	        
        		
        	}while(!valid);
        	
	       
	        System.out.println("QUEDAN " + (tablero.numMinas - minasCazadas) + " MINAS POR DESCUBIRIR");
	        if (player == 1) player = 2;
	        if (player == 2) player = 1;
	        
        }
        
        sc.close();
        
       
    }
}

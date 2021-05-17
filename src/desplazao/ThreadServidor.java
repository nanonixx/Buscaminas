package desplazao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ThreadServidor implements Runnable{

    Socket clientSocket1 = null;
    Socket clientSocket2 = null;
    Tablero tablero;
    boolean torn = true;
    byte[] missatge;
    Juego juego;
    String jugada;

    public ThreadServidor(Socket clientSocket1, Socket clientSocket2) {
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;
        tablero = new Tablero(8, 10);
        juego = new Juego();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outToClient1 = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream inFromClient1 = new ObjectInputStream(clientSocket1.getInputStream());

            ObjectOutputStream outToClient2 = new ObjectOutputStream(clientSocket2.getOutputStream());
            ObjectInputStream inFromClient2 = new ObjectInputStream(clientSocket2.getInputStream());

            outToClient1.writeObject("Ets el jugador 1");
            outToClient1.writeObject(true);

            outToClient2.writeObject("Ets el jugador 2");
            outToClient2.writeObject(false);

            Thread.sleep(2000);

//            outToClient1.writeObject(tablero);
//            outToClient2.writeObject(tablero);

            while(!juego.isGameOver()){
                //enviar turno
                if(torn){
                    outToClient1.writeObject(true);
                    outToClient2.writeObject(true);

                    outToClient1.writeObject(tablero);
                    outToClient1.writeObject("Fes la teva jugada");
                    outToClient2.writeObject("El jugador 1 està fent la seva jugada");

                    jugada = (String) inFromClient1.readObject();

                    //AQUI LLAMAR LOS METODOS PARA PROCESAR LA JUGADA
                    tablero = juego.start(jugada, tablero, true);

                    outToClient2.writeObject(tablero);

                    torn = !torn;

                }else{
                    outToClient2.writeObject(false);
                    outToClient1.writeObject(false);

                    outToClient2.writeObject(tablero);
                    outToClient2.writeObject("Fes la teva jugada");
                    outToClient1.writeObject("El jugador 2 està fent la seva jugada");

                    jugada = (String) inFromClient2.readObject();

                    tablero = juego.start(jugada, tablero, false);

                    outToClient1.writeObject(tablero);

                    torn = !torn;
                }

                outToClient1.flush();
                outToClient2.flush();
            }
        }catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

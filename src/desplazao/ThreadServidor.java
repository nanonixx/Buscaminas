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
    boolean acabat = false;
    boolean torn = true;
    byte[] missatge;

    public ThreadServidor(Socket clientSocket1, Socket clientSocket2) {
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;
        tablero = new Tablero(8, 10);
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outToClient1 = new ObjectOutputStream(clientSocket1.getOutputStream());
            ObjectInputStream inFromClient1 = new ObjectInputStream(clientSocket1.getInputStream());

            ObjectOutputStream outToClient2 = new ObjectOutputStream(clientSocket2.getOutputStream());
            ObjectInputStream inFromClient2 = new ObjectInputStream(clientSocket2.getInputStream());

            outToClient1.writeObject("Ets el jugador 1");
            outToClient2.writeObject("Ets el jugador 2");
            Thread.sleep(2000);
            outToClient1.writeObject(tablero);
            outToClient2.writeObject(tablero);



            while(!acabat){
                //enviar turno
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

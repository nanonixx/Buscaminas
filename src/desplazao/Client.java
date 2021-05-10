package desplazao;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    String hostname;
    int port;
    Tablero tablero;
    boolean acabat = false;
    byte[] missatge;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void run(){
        Socket socket;

        try{
            socket = new Socket(InetAddress.getByName(hostname), port);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            tablero = (Tablero) inFromServer.readObject();
            tablero.mostrarTablero();
            missatge = inFromServer.readAllBytes();

            System.out.println();

            while(!acabat){
                //checkear turno, y hacer jugada
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

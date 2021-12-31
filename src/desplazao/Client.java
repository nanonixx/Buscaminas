package desplazao;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    String hostname;
    int port;
    Tablero tablero;
    boolean acabat = false;
    String missatge;
    boolean jugador, torn;
    String jugada;

    public Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void run(){
        Socket socket;
        Scanner scanner = new Scanner(System.in);

        try{
            socket = new Socket(InetAddress.getByName(hostname), port);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            missatge = (String)  inFromServer.readObject();
            System.out.println(missatge);

            jugador = (boolean) inFromServer.readObject();

            while(!acabat){
                //checkear turno, y hacer jugadaa
                torn = (boolean) inFromServer.readObject();

                if(torn == jugador){
                    tablero = (Tablero) inFromServer.readObject();
                    missatge = (String) inFromServer.readObject();

                    tablero.mostrarTablero();

                    System.out.println("\n" + missatge);
                    System.out.println("\nCoordenades: ");
                    jugada = scanner.nextLine();

                    outToServer.writeObject(jugada);

                    tablero = (Tablero) inFromServer.readObject();
                    tablero.mostrarTablero();

                    acabat = (boolean) inFromServer.readObject();

                }else{
                    missatge = (String) inFromServer.readObject();
                    System.out.println("\n"+missatge);

                    acabat = (boolean) inFromServer.readObject();
                }

            }

            missatge = (String) inFromServer.readObject();
            System.out.println("\n"+missatge);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

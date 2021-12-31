package desplazao;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    int port;

    public Servidor(int port ) {
        this.port = port;
    }

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket1 = null;
        Socket clientSocket2 = null;

        try {
            serverSocket = new ServerSocket(port);

            Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Escoltant al port 5558.....");
            Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Esperant jugadors......");

            while(true) { //esperar connexió del client i llançar thread
               clientSocket1 = serverSocket.accept();
                clientSocket2 = serverSocket.accept();
                if(clientSocket1.isConnected() && clientSocket2.isConnected()) {
                    //Llançar Thread per establir la comunicació
                    ThreadServidor FilServidor = new ThreadServidor(clientSocket1, clientSocket2);
                    Thread client = new Thread(FilServidor);
                    client.start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        Servidor srv = new Servidor(5558);
        Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Servidor en marxa.");
        srv.listen();
    }
}

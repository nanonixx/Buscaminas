package desplazao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadServidor implements Runnable{

    Socket clientSocket1 = null;
    Socket clientSocket2 = null;
    Tablero tablero;
    boolean torn = true;
    byte[] missatge;
    Juego juego;
    String jugada;
    private int minasRestantes;

    public ThreadServidor(Socket clientSocket1, Socket clientSocket2) {
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;
        Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Comença una nova partida");
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

            while(!juego.isGameOver()){
                outToClient1.reset();
                outToClient2.reset();

                //enviar turno
                if(torn){
                    outToClient1.writeObject(true);
                    outToClient2.writeObject(true);

                    outToClient1.writeObject(tablero);
                    // print(quedan X minas)
                    minasRestantes =  tablero.numMinas - (juego.getMinasCazadasP1() + juego.getMinasCazadasP2());
                    outToClient1.writeObject(minasRestantes);

                    outToClient1.writeObject("Fes la teva jugada");
                    outToClient2.writeObject("El jugador 1 està fent la seva jugada");
                    Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Torn del jugador 1");

                    jugada = (String) inFromClient1.readObject();

                    //metodo que procesa la jugada y devuelve el tablero actualizado

                    tablero = juego.start(jugada, tablero, true);
                    outToClient1.reset();

                    outToClient1.writeObject(tablero);

                    torn = !torn;

                }else{
                    outToClient2.writeObject(false);
                    outToClient1.writeObject(false);

                    outToClient2.writeObject(tablero);
                    minasRestantes = tablero.numMinas - (juego.getMinasCazadasP1() + juego.getMinasCazadasP2());
                    outToClient2.writeObject(minasRestantes);
                    outToClient2.writeObject("Fes la teva jugada");
                    outToClient1.writeObject("El jugador 2 està fent la seva jugada");
                    Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "Torn del jugador 2");

                    jugada = (String) inFromClient2.readObject();

                    tablero = juego.start(jugada, tablero, false);

                    outToClient2.reset();
                    outToClient2.writeObject(tablero);

                    torn = !torn;
                }

                outToClient1.writeObject(juego.isGameOver());
                outToClient2.writeObject(juego.isGameOver());


                outToClient1.flush();
                outToClient2.flush();
            }

            if (juego.isGameOver()){
                Logger.getLogger(Servidor.class.getName()).log(Level.INFO, "FI DEL JOC");
            }

            if(juego.getMinasCazadasP1() > juego.getMinasCazadasP2()){
                outToClient1.writeObject("Guanyes amb " + juego.getMinasCazadasP1() + " mines trobades");
                outToClient2.writeObject("Guanya el jugador 1 amb " +juego.getMinasCazadasP1() + " mines trobades\nHas trobat: " + juego.getMinasCazadasP2());
            }
            else if(juego.getMinasCazadasP1() < juego.getMinasCazadasP2()){
                outToClient2.writeObject("Guanyes amb " + juego.getMinasCazadasP2() + " mines trobades");
                outToClient1.writeObject("Guanya el jugador 2 amb " +juego.getMinasCazadasP2() + " mines trobades\nHas trobat: " + juego.getMinasCazadasP1());
            }else{
                outToClient1.writeObject("Empat");
                outToClient2.writeObject("Empat");
            }

        }catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

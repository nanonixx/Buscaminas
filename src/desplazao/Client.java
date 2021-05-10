package desplazao;

import java.io.IOException;
import java.net.*;

public class Client {

    private MulticastSocket socket;
    private InetAddress multicastIP;
    private InetSocketAddress groupMulticast;
    private NetworkInterface netIf;


    public Client(int port, String ip) throws IOException {
        this.socket = new MulticastSocket(port);
        this.multicastIP = InetAddress.getByName(ip);
        this.groupMulticast = new InetSocketAddress(multicastIP, port);
        this.netIf = NetworkInterface.getByName("wlp0s20f3");;
    }

    public void RunClient() throws IOException {
        socket.joinGroup(groupMulticast, netIf);
        DatagramPacket packet;


    }
}

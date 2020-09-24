import java.io.IOException;
import java.net.ServerSocket;
/**
 * Class Server
 *
 * Create the server and a thread that accepts clients
 *
 * @author Kenneth Castillo
 * @version 1.0
 */
public class Server{
    /**
     * Builder method
     *
     * Connects to ServerSocket
     * Starts the thread of Accept Clients
     */
    public static void main(String[] args){
        int port = 6000;
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("INIT SERVER");
            Accept_Clients a = new Accept_Clients(socket);
            Thread q = new Thread(a);
            q.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

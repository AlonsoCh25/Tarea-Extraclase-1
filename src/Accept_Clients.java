import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Class Accept Clients
 *
 * Create the server and a thread that accepts clients
 *
 * @author Kenneth Castillo
 * @version 1.0
 */
public class Accept_Clients implements Runnable {
    //Atributes
    /**
     * ServerSocket for Server
     */
    public ServerSocket socket;
    /**
     * ArrayList to store the clients
     */
    public ArrayList<Socket> Clients;
    /**
     * ArrayList to store the Threads created
     */
    public ArrayList<Thread_Clients> T_Client;

    /**
     * Builder Class
     *
     *Get the Server Socket
     * Create the ArrayList´s
     */
    public Accept_Clients(ServerSocket socket){
        this.socket = socket;
        this.Clients = new ArrayList<>();
        this.T_Client = new ArrayList<>();
    }

    /**
     * Run Method
     *
     * Assign the socket of the accepted customer
     * Create the client's thread
     * Send to the customer thread, the customers connected
     */
    @Override
    public void run() {
        int client = 0;
        while(true){
            try {
                Socket so;
                so = socket.accept();
                client += 1;
                Thread_Clients thread_clients = new Thread_Clients(so, client);
                T_Client.add(thread_clients);
                Clients.add(so);
                Thread P = new Thread(thread_clients);
                P.start();
                for(Thread_Clients A: T_Client) {
                    for (Socket C : Clients) {
                        A.addClient(C);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


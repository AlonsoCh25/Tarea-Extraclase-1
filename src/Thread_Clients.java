import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Thread Clients Class
 *
 * Keeps the client active
 *
 * @author Kenneth Castillo
 * @version 1.0
 */
public class Thread_Clients implements Runnable{
    //Atributes
    /**
     * Socket of Client
     */
    Socket SC;
    /**
     * Create the Data input and output stream
     */
    DataOutputStream OUT;
    DataInputStream IN;
    /**
     * String with the input message
     */
    String INMessage;
    /**
     * String with the name of the client
     */
    String name;
    /**
     * ArrayList with connected clients
     */
    ArrayList<Socket> Clients;
    /**
     * Integer with the num of client
     */
    int client;

    /**
     *Builder Class
     *
     * Assign the values for client, socket and ClientList
     */
    public Thread_Clients(Socket SC, int client){
        this.client = client;
        this.SC = SC;
        this.Clients = new ArrayList<>();
    }

    /**
     *Add Client Class
     *
     * Adds a new value and removes the repeated elements in ClientList
     */
    public void addClient(Socket client) {
        this.Clients.add(client);
        Set<Socket> hashSet = new HashSet<>(this.Clients);
        this.Clients.clear();
        Clients.addAll(hashSet);
    }

    /**
     * Run Class
     *
     * Keeps the socket waiting for messages
     * to be distributed to connected clients
     */
    @Override
    public void run() {
        name = Integer.toString(client);
        name += ": ";
        while (true){
            try {
                IN = new DataInputStream(SC.getInputStream());
                INMessage = IN.readUTF();
                for(Socket C: Clients){
                    OUT = new DataOutputStream(C.getOutputStream());
                    OUT.writeUTF(name + INMessage + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

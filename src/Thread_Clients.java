import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Thread_Clients implements Runnable{

    Socket SC;
    DataOutputStream OUT;
    DataInputStream IN;
    String INMessage;
    ArrayList<Socket> Clients;
    String name;
    int client;
    public Thread_Clients(Socket SC, int client){
        this.client = client;
        this.SC = SC;
        this.Clients = new ArrayList<>();
    }
    public void addClient(Socket client) {
        this.Clients.add(client);
        Set<Socket> hashSet = new HashSet<>(this.Clients);
        this.Clients.clear();
        Clients.addAll(hashSet);

    }

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

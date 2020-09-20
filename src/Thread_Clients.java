import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class Thread_Clients implements Runnable{
    Client client;
    Socket SC;
    DataOutputStream OUT;
    DataInputStream IN;
    String INMessage;
    ArrayList<Socket> Clients;
    public Thread_Clients(Socket SC){
        this.SC = SC;
        this.Clients = new ArrayList<>();
    }
    public void addClient(Socket client){
        this.Clients.add(client);
    }
    @Override
    public void run() {
        while (true){
            try {
                IN = new DataInputStream(SC.getInputStream());
                INMessage = IN.readUTF();
                for(Socket C: Clients){
                    OUT = new DataOutputStream(C.getOutputStream());
                    OUT.writeUTF(INMessage);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

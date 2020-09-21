import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Thread_Clients implements Runnable{

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
        if (Clients.size() == 0) {
            this.Clients.add(client);
        }
        else{
            for (Socket a : Clients) {
                if (a != client) {
                    this.Clients.add(client);
                }
            }
        }

    }
    @Override
    public void run() {
        System.out.println("THREAD CLIENT " + SC);
        while (true){
            try {
                IN = new DataInputStream(SC.getInputStream());
                INMessage = IN.readUTF();
                for(Socket C: Clients){
                    OUT = new DataOutputStream(C.getOutputStream());
                    OUT.writeUTF(INMessage);
                    System.out.println("MENSAJE ENVIADO");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

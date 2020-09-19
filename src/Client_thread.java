import javax.imageio.IIOException;
import java.io.IOException;
public class Client_thread extends Thread{
    private final Client client;
    private final Server server;
    public Client_thread(Client client, Server server){
        this.client = client;
        this.server = server;
    }
    public void run(){
        client.exit.println("SimpleMessenger");
        client.exit.println("Enter your name");
        String name = null;
        try {
            name = client.entry.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.exit.println("--------Chat--------");
        server.sendGeneralMessaje(name + " has connected", client);

        try{
            while(client.itsConnected() != true){
                String message = client.entry.readLine();
                if(message!=null){
                    server.sendGeneralMessaje(name + ": " + message, client);
                }
                else{
                    server.sendGeneralMessaje(name + " disconnected", client);
                    break;
                }

        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

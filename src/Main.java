//import the libraries
import java.io.IOException;
import java.net.Socket;

//Principal class of the APP
public class Main{
    public static void main(String[] args) throws IOException {
        Server server = new Server(5555);
        server.init();
        try {
            while(true){
                Socket socketClient = server.accept_connections();
                Client client = new Client(socketClient);
                server.addClient(client);
                Client_thread threadClient = new Client_thread(client, server);
                threadClient.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;

public class Server{
    private ServerSocket socket;
    private Socket SC;
    private int port;
    private ArrayList<Client> Clients;
    public Server(int port){
        this.port = port;
        Clients = new ArrayList<Client>();
    }
    public void init() throws IOException {
        System.out.println("Servidor Iniciado");
        socket = new ServerSocket(port);
    }
    public void close() throws IOException{
        socket.close();
    }
    public boolean connected(){
        return socket.isClosed();
    }
    public Socket accept_connections() throws IOException{
        return socket.accept();
    }
    public void addClient(Client client){
        Clients.add(client);
    }
    public void sendGeneralMessaje(String message){
        for(Client client : Clients){
            client.exit.println(message);
        }
    }

}
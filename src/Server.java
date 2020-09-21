import java.io.DataOutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Observable;

public class Server implements Runnable {
    private ServerSocket socket;
    public int port;
    final ArrayList<Client> Clients;
    int chat;
    public Server(int port, int chat){
        this.chat = chat;
        this.port = port;
        try {
            this.socket = new ServerSocket(port);
            System.out.println("Servidor Iniciado");
            Acept_Clients a = new Acept_Clients(socket, chat);
            Thread q = new Thread(a);
            q.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Clients = new ArrayList<>();
    }
    @Override
    public void run() {
    }
}
class Run_Server{
    public static void main(String[] args) throws IOException{
        int chats = 1;
        int port = 6000;
        Server server = new Server(port, chats);
        Thread z = new Thread(server);
        z.start();
    }
}

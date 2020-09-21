import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
    public int port;
    int chat;
    public Server(int port, int chat){
        this.chat = chat;
        this.port = port;
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Servidor Iniciado");
            Acept_Clients a = new Acept_Clients(socket, chat);
            Thread q = new Thread(a);
            q.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
    }
}
class Run_Server{
    public static void main(String[] args) {
        int chats = 1;
        int port = 6000;
        Server server = new Server(port, chats);
        Thread z = new Thread(server);
        z.start();
    }
}

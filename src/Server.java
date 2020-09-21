import java.io.DataOutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Observable;

public class Server implements Runnable {
    private ServerSocket socket;
    private Socket so;
    private final int port;
    final ArrayList<Client> Clients;
    Thread_Clients thread_clients;
    DataOutputStream OUT;
    DataInputStream IN;
    String INMessage;
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
    public void addClient(Client client){
        this.Clients.add(client);
    }
    public void sendGeneralMessaje(String message, Client clien) throws IOException {
        for(Client client : Clients){
            DataOutputStream ex = new DataOutputStream(client.socket.getOutputStream());
            ex.writeUTF(message);
            System.out.println("Enviando mensaje");
            }
        }

    @Override
    public void run() {

    }
}
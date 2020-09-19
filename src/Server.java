import java.io.DataOutputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.PrintWriter;

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
            Acept_Clients a = new Acept_Clients(socket, chat);
            Thread q = new Thread(a);
            q.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.Clients = new ArrayList<>();
    }
    public void run() {
    }
        /*while (true){
            try {
                so = new Socket();
                so = socket.accept();
                System.out.println("Cliente conectado");
                IN = new DataInputStream(so.getInputStream());
                INMessage = IN.readUTF();
                OUT = new DataOutputStream(so.getOutputStream());
                OUT.writeUTF(INMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }



            for(Client client : Clients) {
                Thread_Clients thread_clients = new Thread_Clients(client);
                Thread P = new Thread(thread_clients);
                P.start();

                try {
                    System.out.println("Recibiendo mensajes");
                    System.out.println(new DataInputStream(clients.socket.getInputStream()));
                    IN = new DataInputStream(clients.socket.getInputStream());
                    INMessage = IN.readUTF();
                    System.out.println("Enviando mensajes");
                    for(Client client : Clients){
                        try {
                            OUT = new DataOutputStream(client.socket.getOutputStream());
                            OUT.writeUTF(INMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
    public void init() throws IOException{
        socket.accept();
        System.out.println("Cliente conectado");
        //this.socket = new ServerSocket(port);
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
        this.Clients.add(client);
    }
    public void sendGeneralMessaje(String message, Client clien) throws IOException {
        for(Client client : Clients){
            DataOutputStream ex = new DataOutputStream(client.socket.getOutputStream());
            ex.writeUTF(message);
            System.out.println("Enviando mensaje");
            }
        }
}
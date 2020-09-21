
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class Acept_Clients implements Runnable {
    ServerSocket socket;
    Thread_Clients thread_clients;
    ArrayList<Socket> Clients;
    ArrayList<Thread_Clients> T_Client;
    private Socket so;
    int chat;
    public Acept_Clients(ServerSocket socket, int chat){
        this.chat = chat;
        this.socket = socket;
        this.Clients = new ArrayList<>();
        this.T_Client = new ArrayList<>();
    }
    @Override
    public void run() {
        for(int i = 1;i <= chat; i+=1){
            try {
                so = new Socket();
                so = socket.accept();
                Thread_Clients thread_clients = new Thread_Clients(so);
                T_Client.add(thread_clients);
                Clients.add(so);
                Thread P = new Thread(thread_clients);
                P.start();
                System.out.println("Cliente conectado" + so);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(Thread_Clients A: T_Client){
            for(Socket C: Clients){
                A.addClient(C);
            }
        }
    }
}

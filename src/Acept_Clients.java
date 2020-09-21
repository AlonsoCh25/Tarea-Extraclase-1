
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Acept_Clients implements Runnable {
    ServerSocket socket;
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
        while(true){
            try {
                so = new Socket();
                so = socket.accept();
                Thread_Clients thread_clients = new Thread_Clients(so);
                T_Client.add(thread_clients);
                Clients.add(so);
                Thread P = new Thread(thread_clients);
                P.start();
                for(Thread_Clients A: T_Client) {
                    for (Socket C : Clients) {
                        A.addClient(C);
                    }
                }
                System.out.println("Cliente conectado" + so);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }
    }


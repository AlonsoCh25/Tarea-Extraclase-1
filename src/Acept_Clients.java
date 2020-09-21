
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Acept_Clients implements Runnable {
    ServerSocket socket;
    ArrayList<Socket> Clients;
    ArrayList<Thread_Clients> T_Client;
    int chat;
    public Acept_Clients(ServerSocket socket, int chat){
        this.chat = chat;
        this.socket = socket;
        this.Clients = new ArrayList<>();
        this.T_Client = new ArrayList<>();
    }
    @Override
    public void run() {
        int client = 0;
        while(true){
            try {
                Socket so;
                so = socket.accept();
                client+=1;
                Thread_Clients thread_clients = new Thread_Clients(so, client);
                T_Client.add(thread_clients);
                Clients.add(so);
                Thread P = new Thread(thread_clients);
                P.start();
                for(Thread_Clients A: T_Client) {
                    for (Socket C : Clients) {
                        A.addClient(C);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }
    }


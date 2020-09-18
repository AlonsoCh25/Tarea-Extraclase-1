import java.io.IOException;

public class Client_thread extends Thread{
    private Client client;
    private Server server;
    public Client_thread(Client client, Server server){
        this.client = client;
        this.server = server;
        client.exit.println("Hola Bienvenido");
    }
    public void run(){
        try{
            while(true){
                System.out.println("While Client");
                String message = client.entry.readLine();
                server.sendGeneralMessaje(message);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

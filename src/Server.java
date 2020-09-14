import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Server extends Observable implements Runnable{
    private int puerto;

    public Server(int puerto){
        this.puerto = puerto;
    }

    public void run() {
        ServerSocket servidor;
        Socket sc;
        DataInputStream in;
        DataOutputStream out;

        try{
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
            while (true){
                sc = servidor.accept();
                System.out.println("Cliente Conectado");

                in = new DataInputStream(sc.getInputStream());

                String mensaje = in.readUTF();
                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                sc.close();
                System.out.println("Cliente Desconectado");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
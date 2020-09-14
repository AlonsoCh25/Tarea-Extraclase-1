import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Server extends Observable implements Runnable{
    int puerto;
    public Servidor(int puerto);{
        this.puerto = puerto
    }
    public void run() {
        ServerSocket servidor;
        Socket sc;
        DataInputStream in;
        DataOutputStream out;

        int PUERTO = 5000;

        try{
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");
            while (true){
                sc = servidor.accept();
                System.out.println("Cliente Conectado");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String mensaje = in.readUTF();
                System.out.println(mensaje);


                sc.close();
                System.out.println("Cliente Desconectado");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
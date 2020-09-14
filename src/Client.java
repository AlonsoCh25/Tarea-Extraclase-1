import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
    int puerto;
    String mensaje;

    public Client (int puerto, String mensaje){
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    public void run() {
        String HOST = "127.0.0.1";
        try{
            Socket sc = new Socket(HOST, puerto);
            DataInputStream in;
            DataOutputStream out;

            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF(mensaje);

            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

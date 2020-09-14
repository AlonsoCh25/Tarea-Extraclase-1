import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{
    int puerto;
    String mensaje;

    public Cliente(int puerto, String mensaje){
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    public void run() {
        String HOST = "127.0.0.1";
        int PUERTO = 5000;
        try{
            Socket sc = new Socket(HOST, PUERTO);
            DataInputStream in;
            DataOutputStream out;

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF("Hola mundo desde el cliente");

            String mensaje = in.readUTF();
            System.out.println(mensaje);

            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

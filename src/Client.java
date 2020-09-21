
import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client{
    Socket socket;
    DataOutputStream exit;
    DataInputStream entry;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        exit = new DataOutputStream(socket.getOutputStream());
        entry = new DataInputStream(socket.getInputStream());
    }
    public boolean itsConnected() throws IIOException{
        return socket.isClosed();
    }

}
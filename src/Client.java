import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client{
    private Socket socket;
    BufferedReader entry;
    PrintWriter exit;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        entry = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //The bolean lets send the message immediately
        exit = new PrintWriter(socket.getOutputStream(), true);
    }
    public boolean itsConnected() throws IIOException{
        return socket.isClosed();
    }
}
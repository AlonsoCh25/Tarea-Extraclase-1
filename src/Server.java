import java.io.IOException;
import java.net.ServerSocket;

public class Server{
    private ServerSocket socket;
    private int port;

    public Server(int port){
        this.port = port;
    }

    public void init() throws IOException {
        socket = new ServerSocket(port)
    }
    public void close() throws IOException{
        socket.close();
    }
}
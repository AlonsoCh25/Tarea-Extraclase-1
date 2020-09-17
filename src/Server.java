import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

//Create the Server class, with its characteristics
public class Server extends Observable implements Runnable{
    //Create the private varibles
    private int port;
    //Function that assigns the port value
    public Server(int port){
        this.port = port;
    }
    @Override
    //Function that creates and connects to the server
    public void run() {
        //Create the varibles
        ServerSocket server;
        Socket SC;
        DataInputStream IN;
        //Try to create the server
        try{
            //Create the server
            server = new ServerSocket(port);
            System.out.println("Started Server");
            while (true){
                //Wait for a client to connect
                SC = server.accept();
                System.out.println("Client Connected");
                //wait for information
                IN = new DataInputStream(SC.getInputStream());
                //Assign the information received to a variable
                String message = IN.readUTF();
                //Whait for any changes and notify the Observers
                this.setChanged();
                this.notifyObservers(message);
                this.clearChanged();
                //Close the server
                SC.close();
                System.out.println("Disconnected Client");
            }
        }
        //Catch method
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
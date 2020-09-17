import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

//Create the Server class, with its characteristics
public class Servidor extends Observable implements Runnable{
    //Function that creates and connects to the server
    public Servidor(){
        //Create the varibles
        int port = 5000;
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
                setChanged();
                notifyObservers(message);
                clearChanged();
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
    @Override
    public void run() {
    }
    static class play{
        public static void main(String[] args){
            Servidor c = new Servidor();
            Thread t = new Thread(c);
            t.start();
        }
    }
}
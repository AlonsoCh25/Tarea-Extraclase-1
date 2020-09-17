import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Create the class client
public class Client implements Runnable{
    //creation of private variables
    private int port;
    private String message;
    //Function that assigns the port and message values
    public Client (int port, String message){
        this.port = port;
        this.message = message;
    }
    //Function that creates and connects to the socket
    public void run() {
        //Create the constant String HOST
        final String HOST = "127.0.0.1";
        DataOutputStream out;
        //Try to connect to socket
        try{
            //Create a new socket
            Socket sc = new Socket(HOST, port);
            //Create the out data
            out = new DataOutputStream(sc.getOutputStream());
            //Write the message
            out.writeUTF(message);
            //Close the socket
            sc.close();
        }
        //Catch method
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
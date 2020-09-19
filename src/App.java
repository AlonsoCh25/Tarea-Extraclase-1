import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.*;
import java.net.Socket;

public class App extends Thread{
    private JButton button;
    private JTextArea txtChat;
    private JTextField txtMessage;
    private JPanel Panel;
    private Socket SC;
    private int port;
    private Server server;
    private Client client;
    private String message;
    private String entry_message;
    private Object String;
    DataOutputStream exit;
    DataInputStream entry;
    //private BufferedReader entry;


    public App(int port, Server server, Client client, Socket SC){
        this.server = server;
        this.port = port;
        this.client = client;
        this.SC = SC;
        this.txtChat = txtChat;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message = txtMessage.getText();
                try {
                    System.out.println(SC);
                    exit = new DataOutputStream(SC.getOutputStream());
                    exit.writeUTF(message);
                    System.out.println("Mensaje enviado");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
    public void run() {
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new App(port, server, client, SC).Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        while(true){
            frame.repaint();
            //frame.update(txtChat.getGraphics());
            try {
                DataInputStream in = new DataInputStream(client.socket.getInputStream());
                while(entry_message == null){
                    entry_message = in.readUTF();
                    this.txtChat.append(entry_message);
                    System.out.println("Mensaje recibido");
                    System.out.println(txtChat.getText());
                }
                entry_message = null;


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
class Run {
    public static void main(String[] args) throws IOException {
        int chats = 2;
        int port = 5555;
        Server server = new Server(port, chats);
        Thread z = new Thread(server);
        z.start();
        //Thread v = new Thread(server.init());
        //v.start();
        for(int i = 1; i<=chats;i+=1){
            Socket SC = new Socket("127.0.0.1",port);
            Client client = new Client(SC);
            server.addClient(client);
            App t = new App(port, server, client, SC);
            t.start();
            //Thread APP = new Thread(t);
            //APP.start();
        }
        //Socket socketClient = server.accept_connections();
    }
}
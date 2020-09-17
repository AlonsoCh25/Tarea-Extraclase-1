//import the libraries
import javax.swing.*;
import java.util.*;

//Principal class of the APP
public class Chat extends javax.swing.JFrame implements Observer , Runnable{
    int port;
    public Chat(){
        this.getRootPane().setDefaultButton(this.btnSend);
        btnSend.addActionListener(e -> {
            String message = "1: " + txtMessage.getText() + "\n";
            txtChat.append(message);
            Client c = new Client(message, port, Play.chat);
            Thread t1 = new Thread(c);
            t1.start();
        });
    }
    //Creation of private variables
    private JTextArea txtChat;
    private JButton btnSend;
    private JTextField txtMessage;
    private JPanel Panel;

    @Override
    //method that makes the observer update
    public void update(Observable o, Object arg){
        System.out.println("Observador actualizado");
        this.txtChat.append((String) arg);
        System.out.println(this.txtChat.getText());
    }

    @Override
    public void run() {
        Server s = new Server(6000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        }
    static class Play {
        private static int port_ = 0;
        private static int chat = 1;
        public static void main(String[] args){
            for(int i=1;i <= chat; i+=1){
                System.out.println("Veces ejecutadas");
                port_ += 1;
                Chat c = new Chat();
                c.port = port_+5000;
                Thread t = new Thread(c);
                t.start();
            }
        }
    }

}
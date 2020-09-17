//import the libraries
import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;
//Principal class of the APP
public class Chat extends javax.swing.JFrame implements Observer{
    public Chat() {
        this.getRootPane().setDefaultButton(this.btnSend);
        Server s = new Server(5000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        btnSend.addActionListener(e -> {
            String message = "1: " + txtMessage.getText() + "\n";
            txtChat.append(message);
            Client c = new Client(6000, message);
            Thread t1 = new Thread(c);
            t1.start();
        });
    }
    //Creation of private variables
    private JTextArea txtChat;
    private JButton btnSend;
    private JTextField txtMessage;
    private JPanel Panel;

    //Main of the class
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new Chat().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    //method that makes the observer update
    public void update(Observable o, Object arg){
        this.txtChat.append((String) arg);
    }
}

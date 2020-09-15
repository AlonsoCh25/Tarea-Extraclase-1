//import the libraries
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
//Principal class of the APP
public class AppTwo extends javax.swing.JFrame implements Observer{
    public AppTwo() {
        this.getRootPane().setDefaultButton(this.BTSEND);
        Server s = new Server(6000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        BTSEND.addActionListener(e -> {
            String message = "2: " + TXTMESSAGE.getText() + "\n";
            TXTCHAT.append(message);
            Client c = new Client(5000, message);
            Thread t1 = new Thread(c);
            t1.start();
        });
    }
    //Creation of private variables
    private JTextField TXTMESSAGE;
    private JTextArea TXTCHAT;
    private JButton BTSEND;
    private JPanel Panel1;
    //Main of the class
    public static void main(String[] args) {
        JFrame frame = new JFrame("AppTwo");
        frame.setContentPane(new AppTwo().Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    //method that makes the observer update
    public void update(Observable o, Object arg){
        this.TXTCHAT.append((String) arg);
    }
}
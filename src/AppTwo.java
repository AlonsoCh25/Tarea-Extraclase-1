import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AppTwo extends javax.swing.JFrame implements Observer{
    public AppTwo() {
        this.getRootPane().setDefaultButton(this.BTENVIAR);
        Server s = new Server(6000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        BTENVIAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = "2: " + TXTMENSAJE.getText() + "\n";
                TXTCHAT.append(mensaje);
                Client c = new Client(5000, mensaje);
                Thread t = new Thread(c);
                t.start();
            }
        });
    }

    private JTextField TXTMENSAJE;
    private JTextArea TXTCHAT;
    private JButton BTENVIAR;
    private JPanel Panel1;
    public static void main(String[] args) {
        JFrame frame = new JFrame("AppTwo");
        frame.setContentPane(new AppTwo().Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void update(Observable o, Object arg){
        this.TXTCHAT.append((String) arg);
    }
}
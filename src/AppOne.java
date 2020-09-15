import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AppOne extends javax.swing.JFrame implements Observer{
    public AppOne() {
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Server s = new Server(5000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = "1: " + txtmensaje.getText() + "\n";
                txtChat.append(mensaje);
                Client c = new Client(6000, mensaje);
                Thread t = new Thread(c);
                t.start();
            }
        });
    }

    private JTextArea txtChat;
    private JButton btnEnviar;
    private JTextField txtmensaje;
    private JPanel Panel;
    public static void main(String[] args) {
        JFrame frame = new JFrame("AppOne");
        frame.setContentPane(new AppOne().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void update(Observable o, Object arg){
        this.txtChat.append((String) arg);
    }
}

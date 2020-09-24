import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * Class Main
 *
 * contains the graphic interface of the application
 *
 * @author Kenneth Castillo
 * @version 1.0
 */
public class Main extends Application implements EventHandler<ActionEvent>{
    //Attributes
    /**
     * Elements of JavaFx
     */
    public Button button = new Button();
    public TextArea txtChat = new TextArea();
    public TextField txtMessage = new TextField();
    public MenuButton menubutton = new MenuButton();
    public Label label = new Label();
    /**
     * Output message string
     */
    public String outMessage;
    /**
     * Output of DataStream
     */
    public DataOutputStream exit;
    /**
     * Client Socket
     */
    public Socket SC;
    /**
     * Client port
     */
    public int port;

    //Public Methods
    /**
     * Builder method
     *
     * Assign the port value
     * Call the method connect_client
     */
    public Main() throws IOException{
        this.port = 6000;
        connect_client();
    }
    /**
     * Start Method
     *
     * Start of the graphic interface
     * Contains the design of the graphic interface
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("APP");
        StackPane layout = new StackPane();
        button.setText("SEND");
        button.setOnAction(this);
        txtChat.setMinSize(420,340);
        txtChat.setMaxSize(420,340);
        txtChat.setTranslateX(-70);
        txtChat.setTranslateY(5);
        txtChat.setWrapText(true);
        txtMessage.setMinSize(420,30);
        txtMessage.setMaxSize(420,30);
        txtMessage.setTranslateX(-70);
        txtMessage.setTranslateY(200);
        txtMessage.setMinSize(100,20);
        button.setTranslateX(175);
        button.setTranslateY(200);
        layout.getChildren().add(label);
        layout.getChildren().add(menubutton);
        layout.getChildren().add(button);
        layout.getChildren().add(txtChat);
        layout.getChildren().add(txtMessage);
        Scene scene = new Scene(layout, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * Send message method
     *
     * Send the message
     * Get the text from txtMessage
     * Send the message through a new DataOutputStream
     */
    public void send_message() throws IOException{
        outMessage = txtMessage.getText();
        exit = new DataOutputStream(SC.getOutputStream());
        exit.writeUTF(outMessage);
        txtMessage.clear();
    }
    /**
     * Connect client method
     *
     * Connect client to server
     * Creates the client's socket
     * Creates a thread that waits for the entry of a message
     */
    public void connect_client() throws IOException{
        this.SC = new Socket("127.0.0.1", 6000);
        Wait_message wait = new Wait_message(SC, txtChat, menubutton);
        Thread H = new Thread(wait);
        H.start();
    }
    /**
     * Handle method
     *
     * Contains the possible events
     * If the button is pressed send the message
     */
    @Override
    public void handle(ActionEvent event) {
        try {
            send_message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Main method
     * Start the arguments
     */
    public static void main(String[] args){
        launch(args);
    }
}
/**
 * Class Wait_Message
 *
 * Thread in charge of receiving messages
 *
 * @author Kenneth Castillo
 * @version 1.0
 */
class Wait_message implements Runnable{
    //Variables
    /**
     * Incoming message string
     */
    String inMessage;
    /**
     * Socket client
     */
    Socket client;
    /**
     * Elements of JavaFx
     */
    TextArea txtChat;
    MenuButton menubutton;

    //Methods
    /**
     * Builder method
     *
     * Acquire the socket and graphic elements
     * @see: Main.connect_clients()
     */
    public Wait_message(Socket client, TextArea txtChat, MenuButton menubutton){
        this.client = client;
        this.txtChat = txtChat;
        this.menubutton = menubutton;
    }
    /**
     * Run method
     *
     * Executes the functions of the Thread
     */
    @Override
    public void run() {
        while(true){
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (inMessage == null) {
                inMessage = in.readUTF();
                txtChat.appendText(inMessage);

            }
            inMessage = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
    }
}

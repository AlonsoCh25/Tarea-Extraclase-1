import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application implements EventHandler<ActionEvent>{
    public Button button = new Button();
    public TextArea txtChat = new TextArea();
    public TextField txtMessage = new TextField();
    public String outMessage;
    public DataOutputStream exit;
    public Socket SC;
    int port;

    public Main() throws IOException{
        this.port = 6000;
        connect_client();
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("APP");
        StackPane layout = new StackPane();
        button.setText("SEND");
        button.setOnAction(this);
        txtChat.setMinSize(420,340);
        txtChat.setMaxSize(420,340);
        txtChat.setTranslateX(-30);
        txtChat.setTranslateY(-15);
        txtChat.setWrapText(true);
        txtMessage.setMinSize(420,30);
        txtMessage.setMaxSize(420,30);
        txtMessage.setTranslateX(-30);
        txtMessage.setTranslateY(180);
        button.setTranslateX(215);
        button.setTranslateY(180);
        txtMessage.setMinSize(100,20);
        layout.getChildren().add(button);
        layout.getChildren().add(txtChat);
        layout.getChildren().add(txtMessage);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void send_message() throws IOException{
        outMessage = txtMessage.getText();
        exit = new DataOutputStream(SC.getOutputStream());
        exit.writeUTF(outMessage);
    }
    public void connect_client() throws IOException{
        this.SC = new Socket("127.0.0.1", 6000);
        Wait_message wait = new Wait_message(SC, txtChat);
        Thread H = new Thread(wait);
        H.start();
    }
    @Override
    public void handle(ActionEvent event) {
        try {
            send_message();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}


class Wait_message implements Runnable{
    String inMessage;
    Socket client;
    TextArea txtChat;

    public Wait_message(Socket client, TextArea txtChat){
        this.client = client;
        this.txtChat = txtChat;
    }
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

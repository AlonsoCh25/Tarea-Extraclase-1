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
    public StackPane layout = new StackPane();
    public String outMessage;
    public DataOutputStream exit;
    public Socket SC;
    int port;

    public Main() throws IOException{
        this.port = 6000;
        connect_client();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("APP");
        button.setText("SEND");
        button.setOnAction(this);
        button.setScaleY(1);
        button.setScaleX(1);
        txtChat.setMinSize(100,100);
        txtChat.setMaxSize(300,300);
        txtChat.setLayoutX(1000);
        System.out.println(txtChat.getNodeOrientation());
        txtMessage.setMinSize(100,20);
        layout.getChildren().add(txtChat);
        layout.getChildren().add(txtMessage);
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public void send_message() throws IOException{
        System.out.println(txtChat);
        outMessage = txtMessage.getText();
        System.out.println(outMessage);
        System.out.println(SC);
        exit = new DataOutputStream(SC.getOutputStream());
        exit.writeUTF(outMessage);
        System.out.println("Mensaje enviado desde:" + SC);
    }
    public void connect_client() throws IOException{
        this.SC = new Socket("127.0.0.1", 6000);
        System.out.println("SOCKET MAIN: " + this.SC);
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
    DataInputStream in;
    public Wait_message(Socket client, TextArea txtChat){
        this.client = client;
        this.txtChat = txtChat;
    }
    @Override
    public void run() {
        System.out.println("WAIT MESSAGE");
        while(true){
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (inMessage == null) {
                System.out.println("WHILE");
                inMessage = in.readUTF();
                System.out.println(inMessage);
                txtChat.appendText(inMessage);
                System.out.println("Mensaje Recibido");

            }
            inMessage = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        }
    }
}

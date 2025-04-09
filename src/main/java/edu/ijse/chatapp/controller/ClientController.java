package edu.ijse.chatapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    String msg;

    @FXML
    private TextField massgetxt;

    @FXML
    private Button sendbtn;

    @FXML
    private TextArea texthistoryarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 4000);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                texthistoryarea.appendText("Connected to server...\n");

                while (true) {
                    msg = in.readUTF();
                    texthistoryarea.appendText("Server: " + msg + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void mssagesend(ActionEvent event) {
        try {
            String message = massgetxt.getText();
            if (!message.trim().isEmpty()) {
                out.writeUTF(message);
                texthistoryarea.appendText("You: " + message + "\n");
                massgetxt.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

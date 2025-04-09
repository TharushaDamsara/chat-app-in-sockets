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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    ServerSocket serverSocket;
    DataInputStream in;
    DataOutputStream out;
    Socket socket;

    @FXML
    private TextField massgetxt;

    @FXML
    private Button sendbtn;

    @FXML
    private TextArea texthistoryarea;

    // When "Send" button is clicked
    @FXML
    void mssagesend(ActionEvent event) {
        try {
            String message = massgetxt.getText();
            if (!message.trim().isEmpty()) {
                out.writeUTF(message); // Send message to client
                texthistoryarea.appendText("You: " + message + "\n"); // Show in own chat
                massgetxt.clear(); // Clear text input
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize server on load
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(4000); // Open server port
                socket = serverSocket.accept(); // Wait for client
                texthistoryarea.appendText("Client connected...\n"); // ⚠️ UI update from background thread

                in = new DataInputStream(socket.getInputStream()); // Receive stream
                out = new DataOutputStream(socket.getOutputStream()); // Send stream

                // Infinite message reading
                while (true) {
                    String msg = in.readUTF(); // Read client message
                    texthistoryarea.appendText("Client: " + msg + "\n"); // ⚠️ UI update from background thread
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

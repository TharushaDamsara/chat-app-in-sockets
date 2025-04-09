package edu.ijse.chatapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Serverinit extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(
                new Scene(
                        FXMLLoader.load(this.getClass()
                                .getResource("/edu/ijse/chatapp/Server.fxml"))));
        stage.show();
    }

    }

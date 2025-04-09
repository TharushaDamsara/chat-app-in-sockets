package edu.ijse.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class init extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("Client.fxml"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

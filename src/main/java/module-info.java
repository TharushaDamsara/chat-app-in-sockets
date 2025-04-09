module edu.ijse.chatapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ijse.chatapp.controller to javafx.fxml;
    exports edu.ijse.chatapp;
}
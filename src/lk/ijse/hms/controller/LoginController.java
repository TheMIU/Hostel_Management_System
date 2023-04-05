package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void forgotClickOnAction(ActionEvent event) {

    }

    @FXML
    void loginClickOnAction(ActionEvent event) throws IOException {
        if(txtPassword.getText().equals(currentPassword()) && (txtUserName.getText().equals(getUser()))){
            Navigation.navigate(Routes.DASHBOARD, pane);
        }else {
            new Alert(Alert.AlertType.ERROR,"error login !").show();
            txtUserName.clear();
            txtPassword.clear();

        }
    }

    private String getUser() {
        return "admin";
    }

    private String currentPassword() {
        return "123";
    }

}

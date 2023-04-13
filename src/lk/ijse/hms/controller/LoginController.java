package lk.ijse.hms.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label shownPassword;
    @FXML
    private ToggleButton toggleButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void forgotClickOnAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION,"Please contact Developer !\n0773572070").show();
    }

    public void initialize(){
        shownPassword.setVisible(false);
    }

    @FXML
    private void passwordFieldKeyTyped(KeyEvent keyEvent) {
        shownPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
    }

    public void toggleButton(ActionEvent actionEvent) {
        if (toggleButton.isSelected()) {
            shownPassword.setVisible(true);
            shownPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
            toggleButton.setText("Hide");

        }else{
            shownPassword.setVisible(false);
            txtPassword.setVisible(true);
            toggleButton.setText("Show");
        }
    }


    @FXML
    void loginClickOnAction(ActionEvent event) throws IOException {
        Shake shakeUserName = new Shake(txtUserName);
        Shake shakePassword = new Shake(txtPassword);

        if(txtPassword.getText().equals(currentPassword()) && txtUserName.getText().equals(getUser())){
            txtUserName.setFocusColor(Paint.valueOf("BLUE"));
            Navigation.navigate(Routes.DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();

        }else if (txtPassword.getText().equals(currentPassword()) && !txtUserName.getText().equals(getUser())) {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("RED"));
            shakeUserName.play();
        } else if (!txtPassword.getText().equals(currentPassword()) && txtUserName.getText().equals(getUser())) {
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("RED"));
            shakePassword.play();
        } else{
            new Alert(Alert.AlertType.ERROR,"Try again !").show();
            txtPassword.clear();
            txtUserName.clear();
        }

    }

    private String getUser() {
        return "admin";
    }

    private String currentPassword() {
        return "123";
    }


}

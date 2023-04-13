package lk.ijse.hms.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Optional;

public class ChangePasswordController {
    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane confirmPane;

    @FXML
    private JFXPasswordField currentPassword;

    @FXML
    private JFXTextField currentUsername;

    @FXML
    private AnchorPane changePane;

    @FXML
    private JFXPasswordField newPassword;

    @FXML
    private JFXTextField newUsername;

    @FXML
    private Label shownPassword;

    @FXML
    private ToggleButton toggleButton;

    public void initialize(){
        changePane.toBack();
        confirmPane.toFront();

        shownPassword.setVisible(false);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane);
    }

    @FXML
    void confirmClickOnAction(ActionEvent event) {
        String currentUser = currentUsername.getText();
        String currentPw = currentPassword.getText();

        Shake shakeUserName = new Shake(currentUsername);
        Shake shakePassword = new Shake(currentPassword);

        if(currentUser.equals(getCurrentUser()) && currentPw.equals(getCurrentPw())){
            changePane.toFront();
            confirmPane.toBack();

            currentPassword.clear();
            currentUsername.clear();

        }else {
            if(!currentUser.equals(getCurrentUser())){
                shakeUserName.play();
                currentUsername.requestFocus();
            }
            if(!currentPw.equals(getCurrentPw())){
                shakePassword.play();
                currentPassword.requestFocus();
            }
        }
    }

    private String getCurrentPw() {
        return "123";
    }

    private String getCurrentUser() {
        return "admin";
    }


    @FXML
    void changeClickOnAction(ActionEvent event) throws IOException {
        String newUserName = newUsername.getText();
        String newPw = newPassword.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Confirm Update ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            boolean isUpdated = updateUser_Pw(newUserName,newPw);

            if(isUpdated){
                changePane.toBack();
                confirmPane.toFront();

                newUsername.clear();
                newPassword.clear();

                Navigation.navigate(Routes.DASHBOARD,pane);
                
            }else {
                new Alert(Alert.AlertType.ERROR,"Wrong Inputs \nTry again !").show();
            }
        }else {
            changePane.toFront();
            confirmPane.toBack();
        }
    }

    private boolean updateUser_Pw(String newUserName, String newPw) {
        boolean isValid = true;
        if(isValid){
            new Alert(Alert.AlertType.INFORMATION," Changes Saved !").show();
            return true;
        }else {
            return false;
        }
    }

    @FXML
    void toggleButton(ActionEvent event) {
        if (toggleButton.isSelected()) {
            shownPassword.setVisible(true);
            shownPassword.textProperty().bind(Bindings.concat(newPassword.getText()));
            toggleButton.setText("Hide");

        }else{
            shownPassword.setVisible(false);
            newPassword.setVisible(true);
            toggleButton.setText("Show");
        }
    }

    @FXML
    void passwordFieldKeyTyped(javafx.scene.input.KeyEvent keyEvent) {
        shownPassword.textProperty().bind(Bindings.concat(newPassword.getText()));
    }
}

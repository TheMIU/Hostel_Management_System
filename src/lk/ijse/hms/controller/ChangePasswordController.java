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
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.UserBO;
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

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);

    public void initialize() {
        changePane.toBack();
        confirmPane.toFront();

        shownPassword.setVisible(false);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void confirmClickOnAction(ActionEvent event) {
        Shake shakeUserName = new Shake(currentUsername);
        Shake shakePassword = new Shake(currentPassword);

        if (isCorrectUserName() && isCorrectPassword()) {
            changePane.toFront();
            confirmPane.toBack();

            currentPassword.clear();
            currentUsername.clear();

        } else {
            if (!isCorrectUserName()) {
                shakeUserName.play();
                currentUsername.requestFocus();
            }
            if (!isCorrectPassword()) {
                shakePassword.play();
                currentPassword.requestFocus();
            }
        }
    }

    private boolean isCorrectUserName() {
        String user = userBO.getUser("1");
        if (user == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return currentUsername.getText().equals(user);
    }

    private boolean isCorrectPassword() {
        String password = userBO.getPassword("1");
        if (password == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return currentPassword.getText().equals(password);
    }

    @FXML
    void changeClickOnAction(ActionEvent event) throws IOException {
        String newUserName = newUsername.getText();
        String newPw = newPassword.getText();

        if (checkValidity(newUserName,newPw)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Confirm Update ?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {

                boolean isUpdated = userBO.updateUser_Pw(newUserName, newPw);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, " Changes Saved !").show();

                    changePane.toBack();
                    confirmPane.toFront();

                    newUsername.clear();
                    newPassword.clear();

                    Navigation.navigate(Routes.DASHBOARD, pane);

                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Inputs \nTry again !").show();
                }
            } else {
                changePane.toFront();
                confirmPane.toBack();
            }
        }
    }

    private boolean checkValidity(String newUserName, String newPw) {
        return true;
    }

    @FXML
    void toggleButton(ActionEvent event) {
        if (toggleButton.isSelected()) {
            shownPassword.setVisible(true);
            shownPassword.textProperty().bind(Bindings.concat(newPassword.getText()));
            toggleButton.setText("Hide");

        } else {
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

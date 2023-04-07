package lk.ijse.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../resources/view/Login.fxml"))));
        //primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../resources/view/Student.fxml"))));
        primaryStage.show();
        primaryStage.setResizable(false);
        // primaryStage.setY(0);
        primaryStage.setTitle("24D Hostel Management System | Login");
    }
}

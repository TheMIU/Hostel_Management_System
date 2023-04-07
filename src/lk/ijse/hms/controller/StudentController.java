package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hms.bo.StudentBO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentController {
    public AnchorPane pane;

    public JFXDatePicker dateDOB;
    public JFXButton btnDelete;
    public JFXButton btnCancel;
    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbMale;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private JFXButton btnSave;


    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        makeEditableTxtField(false);
        txtID.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentData(newValue);
            makeEditableTxtField(false);
        });

        loadStudentData("");
    }

    private void loadStudentData(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = StudentBO.getStudentData();
        for (StudentDTO std : studentDTOS) {
            if (std.getId().contains(SearchID) ||
                    std.getName().contains(SearchID) ||
                    std.getAddress().contains(SearchID)) {
                StudentDTO studentDTO = new StudentDTO(std.getId(),
                        std.getName(), std.getAddress(),
                        std.getContact_no(),
                        std.getDob(),
                        std.getGender());
                list.add(studentDTO);
            }
        }

        tblStudent.setItems(list);
    }

    private void makeEditableTxtField(boolean b) {
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtContact.setEditable(b);
        txtID.setEditable(b);
    }

    private void setData(StudentDTO newValue) {
        txtID.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact_no());
        txtDOB.setText(newValue.getDob().toString());
        rbMale.setText(newValue.getGender());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String contactText = txtContact.getText();
        String idText = txtID.getText();
        String dobText = dateDOB.getValue().toString();
        RadioButton rb = (RadioButton) gender.getSelectedToggle();
        String genderText = rb.getText();

        StudentDTO studentDTO = new StudentDTO(idText, nameText, addressText, contactText, dobText, genderText);
        Boolean isAdded = StudentBO.addStudent(studentDTO);

        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, " Student Added ! ").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " Error ! ").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String idText = txtID.getText();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(idText);

        Boolean isAdded = StudentBO.deleteStudent(studentDTO);

        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, " Student Deleted ! ").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " Error ! ").show();
        }
    }


    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, pane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }
}

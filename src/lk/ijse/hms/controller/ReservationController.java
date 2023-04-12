package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hms.bo.BOFactory;
import lk.ijse.hms.bo.custom.ReservationBO;
import lk.ijse.hms.bo.custom.RoomsBO;
import lk.ijse.hms.bo.custom.StudentBO;
import lk.ijse.hms.dto.CustomDTO;
import lk.ijse.hms.dto.RoomsDTO;
import lk.ijse.hms.dto.StudentDTO;
import lk.ijse.hms.util.Navigation;
import lk.ijse.hms.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationController {

    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane reservationDetailsPane;
    @FXML
    private AnchorPane newReservationPane;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private JFXTextField txtResID;
    @FXML
    private JFXTextField txtRoomID;

    @FXML
    private JFXTextField txtSearchReservation;

    @FXML
    private TableView<CustomDTO> tblReservation;

    @FXML
    private TableColumn<?, ?> colResID;

    @FXML
    private TableColumn<?, ?> colResDate;

    @FXML
    private TableColumn<?, ?> colRoomTypeRes;

    @FXML
    private TableColumn<?, ?> colRoomTypeIDRes;

    @FXML
    private TableColumn<?, ?> colStudentIDRes;

    @FXML
    private TableColumn<?, ?> colStdNameRes;

    @FXML
    private TableColumn<?, ?> colKeyMoneyRes;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private RadioButton rbAll;

    @FXML
    private ToggleGroup FilterPayment;

    @FXML
    private RadioButton rbPending;

    @FXML
    private RadioButton rbPaid;

    @FXML
    private TableView<RoomsDTO> tblRooms;

    @FXML
    private TableColumn<?, ?> colRoomTypeID;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXTextField txtSearchRoom;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TableColumn<?, ?> colStdID;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private JFXTextField txtSearchStudent;

    @FXML
    private JFXDatePicker dateDate;

    @FXML
    private RadioButton rbPendingStatus;

    @FXML
    private ToggleGroup PaymentStatus;

    @FXML
    private RadioButton rbPaidStatus;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Type.RECEPTION);

    public void initialize() {
        newReservationPane.setDisable(true);
        reservationDetailsPane.setDisable(false);

        // room table
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        txtSearchRoom.textProperty().addListener((observable, oldValue, newValue) -> {
            loadRoomTable(newValue);
        });

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomID.setText(newValue.getRoom_type_id());
            }
        });

        // student table
        colStdID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtStudentID.setText(newValue.getId());
            }
        });

        txtSearchStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStudentTable(newValue);
        });

        // reservation table
        colResID.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("res_date"));
        colRoomTypeIDRes.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomTypeRes.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStudentIDRes.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStdNameRes.setCellValueFactory(new PropertyValueFactory<>("name"));
        colKeyMoneyRes.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtResID.setText(newValue.getRes_id());
                txtSearchRoom.setText(newValue.getRoom_type_id());
                txtRoomID.setText(newValue.getRoom_type_id());
                txtSearchStudent.setText(newValue.getId());
                txtStudentID.setText(newValue.getId());
            }
        });

        txtSearchReservation.textProperty().addListener((observable, oldValue, newValue) -> {
            loadReservationTable(newValue);
        });

        loadRoomTable("");
        loadStudentTable("");
        loadReservationTable("");
    }

    private void loadReservationTable(String SearchID) {
        ObservableList<CustomDTO> list = FXCollections.observableArrayList();

        ArrayList<CustomDTO> customDTOS = reservationBO.getReservationData();
        for (CustomDTO c : customDTOS) {
            if ( c.getRes_id().contains(SearchID) ||
                    c.getRoom_type_id().contains(SearchID) ||
                    c.getType().contains(SearchID)) {

                CustomDTO customDTO = new CustomDTO(
                        c.getRes_id(),
                        c.getRes_date(),
                        c.getRoom_type_id(),
                        c.getType(),
                        c.getId(),
                        c.getName(),
                        c.getKey_money(),
                        c.getStatus());

                list.add(customDTO);
            }
        }
        tblReservation.setItems(list);
    }

    private void loadStudentTable(String SearchID) {
        ObservableList<StudentDTO> list = FXCollections.observableArrayList();

        ArrayList<StudentDTO> studentDTOS = reservationBO.getStudentData();
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

    private void loadRoomTable(String SearchID) {
        ObservableList<RoomsDTO> list = FXCollections.observableArrayList();

        ArrayList<RoomsDTO> roomsDTOS = reservationBO.getRoomsData();
        for (RoomsDTO std : roomsDTOS) {
            if (std.getRoom_type_id().contains(SearchID) ||
                    std.getKey_money().contains(SearchID) ||
                    std.getType().contains(SearchID)) {
                RoomsDTO roomsDTO = new RoomsDTO(
                        std.getRoom_type_id(),
                        std.getType(),
                        std.getKey_money(),
                        std.getQty());
                list.add(roomsDTO);
            }
        }
        tblRooms.setItems(list);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    @FXML
    void btnNewOnAction(ActionEvent actionEvent) {
        String nextID = generateNextID(reservationBO.getCurrentID());
        txtResID.setText(nextID);
        txtSearchRoom.setText("");
        txtRoomID.setText("");
        txtSearchStudent.setText("");
        txtStudentID.setText("");

        dateDate.setValue(LocalDate.now());

        newReservationPane.setDisable(false);
        reservationDetailsPane.setDisable(true);

        btnDelete.setDisable(true);

        btnReserve.setText("Reserve");

    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("RS0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "RS0" + id;
        }
        return "RS01";
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        newReservationPane.setDisable(true);
        reservationDetailsPane.setDisable(false);

        txtResID.clear();
        ;
        txtStudentID.clear();
        txtRoomID.clear();
    }


    @FXML
    void btnEditOnAction(ActionEvent event) {
        newReservationPane.setDisable(false);
        reservationDetailsPane.setDisable(true);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }


    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }
}

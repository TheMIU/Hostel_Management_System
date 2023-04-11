package lk.ijse.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ReservationController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtSearchReservation;

    @FXML
    private TableView<?> tblReservation;

    @FXML
    private TableColumn<?, ?> colResID;

    @FXML
    private TableColumn<?, ?> colResDate;

    @FXML
    private TableColumn<?, ?> colRoomTypeRes;

    @FXML
    private TableColumn<?, ?> colRoomTypeIdRes;

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
    private TableView<?> tblRooms;

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
    private TableView<?> tblStudent;

    @FXML
    private TableColumn<?, ?> colStdID;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private JFXTextField txtSearchStudent;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXDatePicker dateDate;

    @FXML
    private RadioButton rbPendingStatus;

    @FXML
    private ToggleGroup PaymentStatus;

    @FXML
    private RadioButton rbPaidStatus;

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

}

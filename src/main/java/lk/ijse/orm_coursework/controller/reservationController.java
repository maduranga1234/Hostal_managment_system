package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.ReservationBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;
import lk.ijse.orm_coursework.dto.ReservationDto;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.entity.Student;
import lk.ijse.orm_coursework.view.tdm.ReservationTm;
import lk.ijse.orm_coursework.view.tdm.StudentTm;

public class reservationController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField resIdText;

    @FXML
    private ComboBox<String> StIdCombo;

    @FXML
    private ComboBox<String> roomIdCombo;

    @FXML
    private DatePicker datePiker;

    @FXML
    private TableColumn<?, ?> reIdColum;

    @FXML
    private TableColumn<?, ?> stIdColum;

    @FXML
    private TableColumn<?, ?> roomIdColum;

    @FXML
    private TableColumn<?, ?> dateColum;

    @FXML
    private TableColumn<?, ?> statusColum;

    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private TableView<ReservationTm> reservationTable;

    @FXML
    private Label idLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label addressLbl;

    @FXML
    private Label contactLbl;

    @FXML
    private Label BirthLbl;

    @FXML
    private Label genderLbl;

    @FXML
    private TextField searchFeald;

    @FXML
    private Label AvlRoomLbl;

    @FXML
    private TableColumn<?, ?> AvlRoomCol;


    @FXML
    private AnchorPane stDatailPane;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    ReservationBo reservationBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.RESERVATION_BO);

    @FXML
    void DeleteOnAction(ActionEvent event) {

        ReservationDto reservationDto = getReservation();
        reservationBo.deleteReservation(reservationDto);

        resIdText.setText("");
        StIdCombo.setValue(null);
        roomIdCombo.setValue(null);
        datePiker.setValue(null);
        statusCombo.setValue(null);
        getAll();

        AlertController.confirmmessage("Delete successFully");


    }

    @FXML
    void SaveOnAction(ActionEvent event) {

        if (ValidationController.reservationIdCheck(resIdText.getText())) {

            try {

                ReservationDto reservationDto = getReservation();
                reservationBo.saveReservation(reservationDto);
                AlertController.confirmmessage("Save successFully");
                getAll();

                resIdText.setText("");
                StIdCombo.setValue(null);
                roomIdCombo.setValue(null);
                datePiker.setValue(null);
                statusCombo.setValue(null);


            } catch (Exception e) {
                AlertController.errormessage("Duplicate Id");
            }

        } else {

            AlertController.errormessage("Invalied Id");
        }
    }

    @FXML
    void UpdateOnAction(ActionEvent event) {

        if (ValidationController.reservationIdCheck(resIdText.getText())) {

            ReservationDto reservationDto = getReservation();
            reservationBo.updateReservation(reservationDto);
            AlertController.confirmmessage("Update successFully");

            getAll();
            resIdText.setText("");
            StIdCombo.setValue(null);
            roomIdCombo.setValue(null);
            datePiker.setValue(null);
            statusCombo.setValue(null);


        } else {

            AlertController.errormessage("Invalied Id");
        }

    }

    public void loadStIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();

            List<String> ids = reservationBo.loadStudentIds();
            for (String id : ids) {
                obList.add(id);
            }
            StIdCombo.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("loading ids to cmbStudentId failed");
        }
    }

    public void loadRoomTypeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();

            List<String> ids = reservationBo.loadRoomTypeIds();
            for (String id : ids) {
                obList.add(id);
            }
            roomIdCombo.setItems(obList);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("loading ids to cmbStudentId failed");
        }
    }


    public ReservationDto getReservation() {

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(resIdText.getText());
        reservationDto.setDate(datePiker.getValue());
        reservationDto.setStatus(String.valueOf(statusCombo.getValue()));
        reservationDto.setRoomId(String.valueOf(roomIdCombo.getValue()));
        reservationDto.setStudentId(String.valueOf(StIdCombo.getValue()));

        return reservationDto;
    }



    private void getAll() {


        try {
            ObservableList<ReservationDto> all = reservationBo.getDetailsToTableView();
            ObservableList<ReservationTm> reservationData = FXCollections.observableArrayList();

            for (ReservationDto r : all) {
                reservationData.add(new ReservationTm(r.getReservationId(), r.getDate(), r.getStatus(), r.getStudentId(), r.getRoomId()

                ));
                reservationTable.setItems(reservationData);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private void setCellValueFactory() {

        reIdColum.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusColum.setCellValueFactory(new PropertyValueFactory<>("status"));
        stIdColum.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        roomIdColum.setCellValueFactory(new PropertyValueFactory<>("roomId"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        getAll();
        setCellValueFactory();


        loadRoomTypeIds();
        AvlRoomLbl.setVisible(false);

        statusCombo.getItems().addAll("PAID", "NOT PAID");

    }

    public void ReservationOnMouseClick(MouseEvent mouseEvent) {

        try {
            TablePosition pos = reservationTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            ObservableList<TableColumn<ReservationTm, ?>> columns = reservationTable.getColumns();
            resIdText.setText(columns.get(0).getCellData(row).toString());
            StIdCombo.setValue(columns.get(1).getCellData(row).toString());
            roomIdCombo.setValue(columns.get(2).getCellData(row).toString());
            datePiker.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
            statusCombo.setValue(columns.get(4).getCellData(row).toString());


        } catch (Exception e) {
            AlertController.errormessage("Empty Row");
        }
    }

    public void NotPaidOnAction(ActionEvent event) {

        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/notPaidStudent.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.centerOnScreen();
        stage.show();

    }

    public void searchClick(MouseEvent mouseEvent) {

     StudentDto studentDto=reservationBo.getStudentDetail(searchFeald.getText());

     idLbl.setText(studentDto.getId());
     nameLbl.setText(studentDto.getName());
     addressLbl.setText(studentDto.getAddress());
     contactLbl.setText(studentDto.getContact());
     BirthLbl.setText(String.valueOf(studentDto.getDob()));
     genderLbl.setText(studentDto.getGender());

     stDatailPane.setVisible(true);

     searchFeald.setText("");
    }

    public void CloseOnAction(MouseEvent mouseEvent) {
        stDatailPane.setVisible(false);
    }

    public void BackOnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void studentOnMouseClick(MouseEvent mouseEvent) {

        loadStIds();
    }

    public void roomVisibleAction(ActionEvent event) {



        String roomId=roomIdCombo.getValue();
        int count=reservationBo.getAvlRooms(roomId);

        if(count!=-1) {
            AvlRoomLbl.setVisible(true);
            AvlRoomLbl.setText(String.valueOf(count));

        }else {
            AvlRoomLbl.setVisible(false);
        }

    }
}

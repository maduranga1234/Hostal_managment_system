package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.ReservationBo;
import lk.ijse.orm_coursework.dto.ReservationDto;
import lk.ijse.orm_coursework.view.tdm.ReservationTm;

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
    private Stage stage;
    private Scene scene;
    private Parent root;

    ReservationBo reservationBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.RESERVATION_BO);

    @FXML
    void DeleteOnAction(ActionEvent event) {

        ReservationDto reservationDto=new ReservationDto();
        reservationDto.setReservationId(resIdText.getText());
        reservationBo.deleteReservation(reservationDto);

        getAll();

    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        ReservationDto reservationDto=getReservation();
        reservationBo.saveReservation(reservationDto);
   getAll();

    }

    @FXML
    void UpdateOnAction(ActionEvent event) {

        ReservationDto reservationDto=getReservation();
        reservationBo.updateReservation(reservationDto);

        getAll();

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


    public ReservationDto getReservation(){

        ReservationDto reservationDto=new ReservationDto();
        reservationDto.setReservationId(resIdText.getText());
        reservationDto.setDate(datePiker.getValue());
        reservationDto.setStatus(String.valueOf(statusCombo.getValue()));
        reservationDto.setRoomId(String.valueOf(roomIdCombo.getValue()));
        reservationDto.setStudentId(String.valueOf(StIdCombo.getValue()));

       return reservationDto;
    }

    public void BackOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    private void getAll(){


        try{
            ObservableList<ReservationDto> all=reservationBo.getDetailsToTableView();
            ObservableList<ReservationTm>reservationData= FXCollections.observableArrayList();

            for(ReservationDto r : all) {
                reservationData.add(new ReservationTm(r.getReservationId(),r.getDate(),r.getStatus(),r.getStudentId(),r.getRoomId()

                ));
                reservationTable.setItems(reservationData);
            }

        }catch (Exception throwables){
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

        loadStIds();
        loadRoomTypeIds();

        statusCombo.getItems().addAll("PAID", "NOT PAID");

    }
}

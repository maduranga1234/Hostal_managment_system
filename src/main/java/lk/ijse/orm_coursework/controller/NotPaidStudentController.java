package lk.ijse.orm_coursework.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.NotPaidBo;
import lk.ijse.orm_coursework.projection.CustomProjection;
import lk.ijse.orm_coursework.view.tdm.NotPaidTm;

public class NotPaidStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<NotPaidTm> notPaidTable;

    @FXML
    private TableColumn<?, ?> StIdCol;

    @FXML
    private TableColumn<?, ?> StNameCol;

    @FXML
    private TableColumn<?, ?> ContactCol;

    @FXML
    private TableColumn<?, ?> RoomIdCol;

    @FXML
    private TableColumn<?, ?> KeyMoneyCol;

    NotPaidBo notPaidBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.NOT_PAID);






    private void setCellValueFactory() {

        StIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        StNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        RoomIdCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        KeyMoneyCol.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));

    }


    public void getAll(){
        ObservableList<CustomProjection> studentKeyMoneyList = notPaidBo.getDetailsToTableView();
        ObservableList<NotPaidTm> studentKeyMoneyTmList = FXCollections.observableArrayList();
        for (CustomProjection cp : studentKeyMoneyList){
            studentKeyMoneyTmList.add(
                    new NotPaidTm(
                            cp.getStudentId(),
                            cp.getStudentName(),
                            cp.getContactNumber(),
                            cp.getRoomId(),
                            cp.getKeyMoney()


                    )
            );
        }
        notPaidTable.setItems(studentKeyMoneyTmList);

    }

    @FXML
    void initialize() {
        assert notPaidTable != null : "fx:id=\"notPaidTable\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        assert StIdCol != null : "fx:id=\"StIdCol\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        assert StNameCol != null : "fx:id=\"StNameCol\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        assert ContactCol != null : "fx:id=\"ContactCol\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        assert RoomIdCol != null : "fx:id=\"RoomIdCol\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        assert KeyMoneyCol != null : "fx:id=\"KeyMoneyCol\" was not injected: check your FXML file 'notPaidStudent.fxml'.";
        getAll();
        setCellValueFactory();
    }



}


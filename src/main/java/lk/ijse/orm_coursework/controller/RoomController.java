package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.RoomBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;
import lk.ijse.orm_coursework.dto.RoomDto;
import lk.ijse.orm_coursework.view.tdm.RoomTm;

public class RoomController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField KeyMoneyTxt;

    @FXML
    private TextField qtyTxt;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> keyMoneyCol;

    @FXML
    private TableColumn<?, ?> QtyCol;

    @FXML
    private TableView<RoomTm> roomTable;

    @FXML
    private TextField maxNumberTxt;

    @FXML
    private TableColumn<?, ?> maxNumberCol;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    RoomBo roomBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.ROOM_BO);


    @FXML
    void roomDeletOnAction(ActionEvent event) {

       RoomDto deletDto=new RoomDto();
       deletDto.setRoomId(idTxt.getText());
       roomBo.deleteRoom(deletDto);

       getAll();
    }

    @FXML
    void roomSaveOnAction(ActionEvent event) {



        if(ValidationController.roomIdCheck(idTxt.getText())) {
            if(ValidationController.customerNameValidate(typeTxt.getText())) {
                if(ValidationController.salary(KeyMoneyTxt.getText())) {
                    if(ValidationController.salary(qtyTxt.getText())) {
                        if(ValidationController.salary(maxNumberTxt.getText())) {
                            try {
                            RoomDto roomDto = getRoom();
                            roomBo.saveRoom(roomDto);

                            AlertController.confirmmessage("Save successFully");

                            getAll();

                            idTxt.setText("");
                            typeTxt.setText("");
                            KeyMoneyTxt.setText("");
                            qtyTxt.setText("");
                            maxNumberTxt.setText("");

                        }catch (Exception e){
                            AlertController.errormessage("Duplicate Id");
                        }
                        }else {

                            AlertController.errormessage("Invalied Number");
                        }

                    }else{
                        AlertController.errormessage("Invalied Qty");
                    }

                }else {
                    AlertController.errormessage("Invalied Key Money");
                }

            }else{
                AlertController.errormessage("Invalied Room Type");

            }
        }else {
            AlertController.errormessage("Invalied Id");
        }

    }

    @FXML
    void roomUpdateOnAction(ActionEvent event) {

        if(ValidationController.roomIdCheck(idTxt.getText())) {
            if(ValidationController.customerNameValidate(typeTxt.getText())) {
                if(ValidationController.salary(KeyMoneyTxt.getText())) {
                    if(ValidationController.salary(qtyTxt.getText())) {
                        if(ValidationController.salary(maxNumberTxt.getText())) {



                                RoomDto roomDto = getRoom();
                                roomBo.updateRoom(roomDto);

                            AlertController.confirmmessage("Update successFully");

                                getAll();
                                idTxt.setText("");
                                typeTxt.setText("");
                                KeyMoneyTxt.setText("");
                                qtyTxt.setText("");
                                maxNumberTxt.setText("");


                        }else {

                            AlertController.errormessage("Invalied Number");
                        }

                    }else{
                        AlertController.errormessage("Invalied Qty");
                    }

                }else {
                    AlertController.errormessage("Invalied Key Money");
                }

            }else{
                AlertController.errormessage("Invalied Room Type");

            }
        }else {
            AlertController.errormessage("Invalied Id");
        }

    }

    @FXML
    void initialize() {
        assert idTxt != null : "fx:id=\"idTxt\" was not injected: check your FXML file 'room.fxml'.";
        assert typeTxt != null : "fx:id=\"typeTxt\" was not injected: check your FXML file 'room.fxml'.";
        assert KeyMoneyTxt != null : "fx:id=\"KeyMoneyTxt\" was not injected: check your FXML file 'room.fxml'.";
        assert qtyTxt != null : "fx:id=\"qtyTxt\" was not injected: check your FXML file 'room.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'room.fxml'.";
        assert typeCol != null : "fx:id=\"typeCol\" was not injected: check your FXML file 'room.fxml'.";
        assert keyMoneyCol != null : "fx:id=\"keyMoneyCol\" was not injected: check your FXML file 'room.fxml'.";
        assert QtyCol != null : "fx:id=\"QtyCol\" was not injected: check your FXML file 'room.fxml'.";

    }

    public RoomDto getRoom(){
        RoomDto roomDto=new RoomDto();
        roomDto.setRoomId(idTxt.getText());
        roomDto.setType(typeTxt.getText());
        roomDto.setKeyMoney(KeyMoneyTxt.getText());
        roomDto.setQty(Integer.parseInt(qtyTxt.getText()));
        roomDto.setMaxNumber(Integer.parseInt(maxNumberTxt.getText()));


        return roomDto;
    }


    private void setCellValueFactory() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        keyMoneyCol.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        QtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        maxNumberCol.setCellValueFactory(new PropertyValueFactory<>("maxNumber"));

    }

    private void getAll(){


        try{
            ObservableList<RoomDto> all=roomBo.getDetailsToTableView();
            ObservableList<RoomTm>roomData= FXCollections.observableArrayList();

            for(RoomDto r : all) {
                roomData.add(new RoomTm(r.getRoomId(), r.getType(), r.getKeyMoney(), r.getQty(),r.getMaxNumber()

                ));
                roomTable.setItems(roomData);
            }

        }catch (Exception throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      setCellValueFactory();
      getAll();
    }

    public void backOnAction(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void RoomOnMouseClicked(MouseEvent mouseEvent) {

try{
        TablePosition pos = roomTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        ObservableList<TableColumn<RoomTm, ?>> columns = roomTable.getColumns();
        idTxt.setText(columns.get(0).getCellData(row).toString());
        typeTxt.setText(columns.get(1).getCellData(row).toString());
        KeyMoneyTxt.setText(columns.get(2).getCellData(row).toString());
        qtyTxt.setText(columns.get(3).getCellData(row).toString());
        maxNumberTxt.setText(columns.get(4).getCellData(row).toString());


    }catch (Exception e){
        AlertController.errormessage("Empty Row");
    }
    }
}

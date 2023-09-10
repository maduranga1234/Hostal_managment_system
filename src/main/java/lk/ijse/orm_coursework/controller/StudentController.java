package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.StudentBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;
import lk.ijse.orm_coursework.dto.StudentDto;
import lk.ijse.orm_coursework.view.tdm.RoomTm;
import lk.ijse.orm_coursework.view.tdm.StudentTm;

public class StudentController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField contactTxt;

    @FXML
    private DatePicker dobTxt;

    @FXML
    private TextField genderTxt;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> ContactCol;

    @FXML
    private TableColumn<?, ?> DobCol;

    @FXML
    private TableColumn<?, ?> GenderCol;

    @FXML
    private TableView<StudentTm> studentTable;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT_BO);

    @FXML
    void studentDeletOnAction(ActionEvent event) {
        StudentDto deletDto = new StudentDto();
        deletDto.setId(idTxt.getText());
        studentBo.deleteStudent(deletDto);

        idTxt.setText("");
        nameTxt.setText("");
        addressTxt.setText("");
        contactTxt.setText("");
        dobTxt.setValue(null);
        genderTxt.setText("");

        getAll();
        AlertController.confirmmessage("Delete successFully");




    }

    @FXML
    void studentSaveOnAction(ActionEvent event) {


        if (ValidationController.studentIdCheck(idTxt.getText())) {
            if (ValidationController.customerNameValidate(nameTxt.getText())) {
                if (ValidationController.customerNameValidate(addressTxt.getText())) {
                    if (ValidationController.contactCheck(contactTxt.getText())) {
                        if (ValidationController.DateOfBirth(String.valueOf(dobTxt.getValue()))) {

                           try{
                            StudentDto studentDto = getStudent();
                            studentBo.saveStudent(studentDto);
                            AlertController.confirmmessage("Save successFully");




                               getAll();
                            idTxt.setText("");
                            nameTxt.setText("");
                            addressTxt.setText("");
                            contactTxt.setText("");
                            dobTxt.setValue(null);
                            genderTxt.setText("");



                        }catch (Exception e){
                            AlertController.errormessage("Duplicate Id");
                        }

                        } else {

                            AlertController.errormessage("Invalied Date Of Birth");
                        }

                    } else {
                        AlertController.errormessage("Invalied Contact Number");
                    }

                } else {
                    AlertController.errormessage("Invalied Address");
                }

            } else {
                AlertController.errormessage("Invalied Name");

            }
        } else {
            AlertController.errormessage("Invalied Id");
        }


    }

    @FXML
    void studentUpdateOnAction(ActionEvent event) {

        if (ValidationController.studentIdCheck(idTxt.getText())) {
            if (ValidationController.customerNameValidate(nameTxt.getText())) {
                if (ValidationController.customerNameValidate(addressTxt.getText())) {
                    if (ValidationController.contactCheck(contactTxt.getText())) {
                        if (ValidationController.DateOfBirth(String.valueOf(dobTxt.getValue()))) {

                            StudentDto studentDto = getStudent();
                            studentBo.updateStudent(studentDto);
                            AlertController.confirmmessage("Update successFully");
                            getAll();

                            idTxt.setText("");
                            nameTxt.setText("");
                            addressTxt.setText("");
                            contactTxt.setText("");
                            dobTxt.setValue(null);
                            genderTxt.setText("");


                        } else {

                            AlertController.errormessage("Invalied Date Of Birth");
                        }

                    } else {
                        AlertController.errormessage("Invalied Contact Number");
                    }

                } else {
                    AlertController.errormessage("Invalied Address");
                }

            } else {
                AlertController.errormessage("Invalied Name");

            }
        } else {
            AlertController.errormessage("Invalied Id");
        }


    }

    private void setCellValueFactory() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        DobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void getAll() {


        try {
            ObservableList<StudentDto> all = studentBo.getDetailsToTableView();
            ObservableList<StudentTm> studentData = FXCollections.observableArrayList();

            for (StudentDto s : all) {
                studentData.add(new StudentTm(s.getId(), s.getName(), s.getAddress(), s.getContact(), s.getDob(), s.getGender()

                ));
                studentTable.setItems(studentData);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }


    public StudentDto getStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(idTxt.getText());
        studentDto.setName(nameTxt.getText());
        studentDto.setAddress(addressTxt.getText());
        studentDto.setContact(contactTxt.getText());
        studentDto.setDob(dobTxt.getValue());
        studentDto.setGender(genderTxt.getText());

        return studentDto;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();


    }



    public void StudentOnMouseClicked(MouseEvent mouseEvent) {


        try {
            TablePosition pos = studentTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            ObservableList<TableColumn<StudentTm, ?>> columns = studentTable.getColumns();
            idTxt.setText(columns.get(0).getCellData(row).toString());
            nameTxt.setText(columns.get(1).getCellData(row).toString());
            addressTxt.setText(columns.get(2).getCellData(row).toString());
            contactTxt.setText(columns.get(3).getCellData(row).toString());
            dobTxt.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
            genderTxt.setText(columns.get(5).getCellData(row).toString());


        } catch (Exception e) {
            AlertController.errormessage("Empty Row");
        }
    }

    public void BackOnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

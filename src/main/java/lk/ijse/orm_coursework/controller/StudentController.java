package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.StudentBo;
import lk.ijse.orm_coursework.dto.StudentDto;
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

    StudentBo studentBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT_BO);

    @FXML
    void studentDeletOnAction(ActionEvent event) {
        StudentDto deletDto=new StudentDto();
        deletDto.setId(idTxt.getText());
        studentBo.deleteStudent(deletDto);

       getAll();

    }

    @FXML
    void studentSaveOnAction(ActionEvent event) {
        StudentDto studentDto=getStudent();
        studentBo.saveStudent(studentDto);

        getAll();


    }

    @FXML
    void studentUpdateOnAction(ActionEvent event) {

        StudentDto studentDto=getStudent();
        studentBo.updateStudent(studentDto);

        getAll();


    }

    private void setCellValueFactory() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        DobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void getAll(){


        try{
        ObservableList<StudentDto>all=studentBo.getDetailsToTableView();
        ObservableList<StudentTm>studentData= FXCollections.observableArrayList();

        for(StudentDto s : all) {
            studentData.add(new StudentTm(s.getId(), s.getName(), s.getAddress(), s.getContact(), s.getDob(), s.getGender()

            ));
            studentTable.setItems(studentData);
        }

        }catch (Exception throwables){
            throwables.printStackTrace();
        }
    }




    public StudentDto getStudent(){
        StudentDto studentDto=new StudentDto();
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

    public void BackOnAction(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

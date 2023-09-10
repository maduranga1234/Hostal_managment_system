package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.SingUpBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;
import lk.ijse.orm_coursework.dto.UserDto;

public class SingupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    SingUpBo singUpBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.SING_UP);

    public void SingUpOnAction(ActionEvent event) {



        if(ValidationController.customerNameValidate(userNameTxt.getText())){
        if(ValidationController.Password(passwordTxt.getText())) {

            try {

                UserDto userDto = getUser();
                singUpBo.saveUser(userDto);

                AlertController.confirmmessage("Singup Successful");
                userNameTxt.setText("");
                passwordTxt.setText("");
            }catch (Exception e){
                AlertController.errormessage("Invalied Username or Password");
            }

        }else {
            AlertController.errormessage("Invalied Username");
        }
        }else {
            AlertController.errormessage("Invalied Password");

        }

    }

    @FXML
    void initialize() {
        assert userNameTxt != null : "fx:id=\"userNameTxt\" was not injected: check your FXML file 'singUp.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'singUp.fxml'.";

    }


    public UserDto getUser(){
        UserDto userDto=new UserDto();
        userDto.setUserName(userNameTxt.getText());
        userDto.setPassword(passwordTxt.getText());

        return userDto;
    }


    public void backOnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/loging.fxml"));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}

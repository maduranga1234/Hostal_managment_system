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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.PasswordChangeBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;
import lk.ijse.orm_coursework.dto.UserDto;

public class PasswordChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameTxt;

    @FXML
    private PasswordField passwordTxt;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    PasswordChangeBo passwordChangeBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.PASSWORD_CHANGE_BO);


    @FXML
    void changeOnAction(ActionEvent event) {


        if(ValidationController.customerNameValidate(userNameTxt.getText())){
            if(ValidationController.Password(passwordTxt.getText())) {


                try {


                    UserDto userDto = getUser();
                  boolean getData=passwordChangeBo.updateUser(userDto);

                  if(getData){


                      AlertController.confirmmessage("Password Change Successful");
                      userNameTxt.setText("");
                      passwordTxt.setText("");
                  }else{
                      AlertController.errormessage("Invalied Username");
                  }


                }catch (Exception e){
                    AlertController.errormessage("Invalied Detail");
                }

            }else {
                AlertController.errormessage("Invalied Username");
            }
        }else {
            AlertController.errormessage("Invalied Password");

        }

//maduranga
        //Madu2002!
    }

    @FXML
    void initialize() {
        assert userNameTxt != null : "fx:id=\"userNameTxt\" was not injected: check your FXML file 'passwordChange.fxml'.";
        assert passwordTxt != null : "fx:id=\"passwordTxt\" was not injected: check your FXML file 'passwordChange.fxml'.";

    }



    public UserDto getUser(){
        UserDto userDto=new UserDto();
        userDto.setUserName(userNameTxt.getText());
        userDto.setPassword(passwordTxt.getText());

        return userDto;
    }


    public void backOnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}

package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.LogingBo;
import lk.ijse.orm_coursework.controller.util.AlertController;
import lk.ijse.orm_coursework.controller.util.ValidationController;

public class LogingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameText;

    @FXML
    private PasswordField passWordText;


    @FXML
    private Group hideGroup;


    @FXML
    private Group showGroup;

    @FXML
    private TextField passWordShowFiled;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    LogingBo logingBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.LOGING_BO);

    @FXML
    void logingOnAction(ActionEvent event) throws IOException, SQLException {


        if (ValidationController.customerNameValidate(userNameText.getText())) {
            if (ValidationController.Password(passWordText.getText())) {

                String passWord = logingBo.searchUser(userNameText.getText());

                if(passWord!=null) {

                    if (passWord.equals(passWordText.getText())) {

                        AlertController.confirmmessage("Loging Successful");

                        Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();

                    } else {
                        AlertController.errormessage("This password does not match this Username");
                    }

                }else{
                    AlertController.errormessage("This password does not Have Database");
                }

            }else {
                AlertController.errormessage("Invalied Password");
            }
        }else {
            AlertController.errormessage("Invalied Username");

        }

            }

            @FXML
            void singUpOnAction (ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("/view/singUp.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            }

            @FXML
            void initialize () {
                assert userNameText != null : "fx:id=\"userNameText\" was not injected: check your FXML file 'loging.fxml'.";
                assert passWordText != null : "fx:id=\"passWordText\" was not injected: check your FXML file 'loging.fxml'.";

            }


            public void showEnterd (MouseEvent mouseEvent){
            }

            public void hideEnted (MouseEvent mouseEvent){
                passWordShowFiled.setText(passWordText.getText());
                showGroup.setVisible(true);
                hideGroup.setVisible(false);


            }

            public void showExit (MouseEvent mouseEvent){

                hideGroup.setVisible(true);
                showGroup.setVisible(false);
            }

            public void hideExit (MouseEvent mouseEvent){

            }
        }

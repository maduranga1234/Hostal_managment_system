package lk.ijse.orm_coursework.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.orm_coursework.bo.BoFactory;
import lk.ijse.orm_coursework.bo.custom.LogingBo;

public class LogingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameText;

    @FXML
    private TextField passWordText;


    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    LogingBo logingBo= BoFactory.getBoFactory().getBo(BoFactory.BoType.LOGING_BO);

    @FXML
    void logingOnAction(ActionEvent event) throws IOException, SQLException {



         String passWord= logingBo.searchUser(userNameText.getText());

         if(passWord.equals(passWordText.getText())) {

             Parent root = FXMLLoader.load(getClass().getResource("/view/dashBord.fxml"));
             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.centerOnScreen();
             stage.show();
         }

    }

    @FXML
    void singUpOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/singUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void initialize() {
        assert userNameText != null : "fx:id=\"userNameText\" was not injected: check your FXML file 'loging.fxml'.";
        assert passWordText != null : "fx:id=\"passWordText\" was not injected: check your FXML file 'loging.fxml'.";

    }


}

package lk.ijse.orm_coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import lk.ijse.orm_coursework.config.SessionFactoryConfig;
import org.hibernate.Session;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/loging.fxml"));
        primaryStage.setTitle("Thoga Kade");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Session session = SessionFactoryConfig.getInstance().getSession();
        launch(args);

    }
}

package dbscout;

import dbscout.controller.Controller;
import dbscout.controller.Login;
import dbscout.data.DAOUtils;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
    private Controller controller;
    private Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception {

        initiate(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loader.load();
        Login loginController = loader.getController();
        primaryStage.setTitle("DBSCOUT");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        loginController.setController(controller);

    }

    private void initiate(Stage stage) {
        connection = DAOUtils.localMySQLConnection("dbscout", "root", "");
        controller = new Controller(stage, connection);
    }

}


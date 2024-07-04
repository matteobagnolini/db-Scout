package dbscout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dbscout.data.DAOUtils;

public class App extends Application{
    /*public static void Main(String[] args) {
        // new Controller
        // start new connection with database
        // new Model
        // new View
        // view.setController()
        // controller.loginView()
    
        var connection = DAOUtils.localMySQLConnection("dbscout", "root", "");

    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Sto in start");
        Parent root = FXMLLoader.load(getClass().getResource("/TestPage.fxml"));
        primaryStage.setTitle("PROVA");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}


package dbscout.controller;

import dbscout.data.entities.Associato;

import java.sql.Connection;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Controller {

    Stage stage;
    Connection connection;
    Associato loggedAssociato;

    public Controller(Stage stage, Connection connection) {
        this.stage = stage;
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean checkUserExists(String userId) {
        //TODO: 
        return false;
    }

    public void changeScene(String url) {
        try {    
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(url));
            Parent root = loader.load();
            stage.getScene().setRoot(root);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAssociato(Associato ass) {
        this.loggedAssociato = ass;
    }

    public Associato getAssociato(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAssociato'");
    }

}

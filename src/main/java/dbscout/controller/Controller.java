package dbscout.controller;

import dbscout.Model.Model;
import dbscout.data.entities.Associato;
import dbscout.controller.FXController;

import java.sql.Connection;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Connection connection;
    private Model model;

    public Controller(Stage stage, Connection connection) {
        this.stage = stage;
        this.connection = connection;
        this.model = new Model(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    public Model getModel() {
        return model;
    }

    public void changeScene(String url) {
        try {    
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(url));
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            FXController contr = loader.getController();
            contr.setController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Scene changed");
    }

    public void setAssociato(Associato ass) {
        model.setAssociato(ass);
    }

    public Associato getAssociato(int id) {
        return this.model.getAssociato();
    }

}

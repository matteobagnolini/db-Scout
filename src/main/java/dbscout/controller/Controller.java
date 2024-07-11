package dbscout.controller;

import dbscout.data.entities.Associato;

import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller {

    Stage stage;
    Connection connection;

    public Controller(Stage stage, Connection connection) {
        this.stage = stage;
        this.connection = connection;
    }

    public boolean checkUserExists(String userId) {
        //TODO: 
        return false;
    }

    public Associato getAssociato(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAssociato'");
    }
    //per la classe lupett, ricordarsi di legare il controllere all' fxml
        @FXML
    private TextArea capiBranca;

    @FXML
    private TextArea membriSestiglia;

    @FXML
    private Label nomeAssociato;

}

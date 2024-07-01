package dbscout.controller;

import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ControllerTest {

    @FXML
    private Button BottonSquadriglia;

    @FXML
    private Label LabelBrutta;

    @FXML
    private Text ShowSquadriglia;
    
    @FXML
    void showShadriglia(ActionEvent e) {
        System.out.println("bottonePremuto");
        this.ShowSquadriglia.setText("Bigu");
    }
}

package dbscout.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    private Controller controller;

    @FXML
    private Button button;

    @FXML
    private TextField userId;

    @FXML
    private Label errorID;

    @FXML
    private Label cod_errore;

    @FXML
    void logIn(ActionEvent event) {
        checkUser(userId.getText());
    }

    private void checkUser(String id) {

        if (controller.checkUserExists(id)) {
            switch (controller.getAssociato(id).getBranca()) {
                case "Lupetti" -> {}
                case "Reparto" -> {}
                case "Noviziato" -> {}
                case "Clan" -> {}
                case "CoCa" -> {}
            }
        } else {
            cod_errore.setVisible(true);
        }
    }

    public void setController(final Controller controller) {
        this.controller = controller;
    }

}
